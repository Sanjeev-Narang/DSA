import org.jetbrains.annotations.NotNull;

import java.util.*;
public class Main {
        public static class Node {
            int data;
            ArrayList<Node> children;

            public Node() {
                children = new ArrayList<Node>();
            }
            public Node(int data) {
                this.data = data;
            }
        }
    public static void display(@NotNull Node t) {
        String str = t.data + "-> ";
        for(Node child : t.children) {
            str = str + child.data + ", ";
        }
        str = str + ".";
        System.out.println(str);
        for(Node child : t.children) {
            display(child);
        }
    }
    public static int size(Node t) {
            int count = 1;
            for(Node child: t.children) {
                int cs = size(child);
                count = count + cs;
            }
            return count;
    }
    public static int maax(Node t) {
            int max = t.data;
            for(Node child: t.children) {
                int cmax = maax(child);
                max = Math.max(max, cmax);
            }
            return max;
    }
    public static int height(Node t) { //height in terms of edges
            int h = -1;
            for(Node child: t.children) {
                int ch = height(child);
                h = Math.max(ch, h);
            }
            h = h+1;
            return h;
    }
    public static void preTrav(Node t) {
            System.out.print(t.data +" ");
            for(Node child : t.children) {
                System.out.println("Pre Edge: "+t.data+"--"+child.data);
                preTrav(child);
            }
    }
    public static void lvTrav(Node t) {
            Queue<Node> qr = new ArrayDeque<>();
            qr.add(t);
            while(qr.size() != 0) {
               t = qr.remove();
                System.out.println(t.data);
                for(Node child: t.children) {
                    qr.add(child);
                }
            }
    }
    public static void nwlvTrav(Node t) {
            Queue<Node> nq = new ArrayDeque<>();
            Queue<Node> oq = new ArrayDeque<>();
            oq.add(t);
            while(oq.size()!=0 || nq.size()!=0) {
                t = oq.remove();
                System.out.print(t.data + " ");
                for (Node child : t.children) {
                    nq.add(child);
                }
                if (oq.isEmpty()) {
                    oq = nq;
                    nq = new ArrayDeque<>();
                    System.out.println();
                }
            }
    }
    public static void zigzag(Node t) {
            Stack<Node> mst = new Stack<>();
            Stack<Node> cst = new Stack<>();
            int lvl = 0;
            mst.push(t);
            while(mst.size()!=0 || cst.size()!=0) {
                t = mst.pop();
                System.out.println(t.data);
                if (lvl % 2 == 1) {
                    for (Node child : t.children) {
                        cst.push(child);
                    }
                } else {
                    for (int ct = t.children.size()-1; ct >= 0; ct--) {
                        cst.push(t.children.get(ct));
                    }
                }
                if(mst.isEmpty()) {
                    mst = cst;
                    cst = new Stack<>();
                    lvl++;
                }
            }
    }
    public static void sqlvl(Node t) {
            Queue<Node> q = new ArrayDeque<>();
            q.add(t);
            q.add(new Node(-1));
            while(q.size()!= 0) {
                t = q.remove();
                if(t.data != -1) {
                    System.out.print(t.data + " ");
                    for(Node child: t.children) {
                        q.add(child);
                    }
                } else {
                    if(q.size()>0) {
                        q.add(t);
                        System.out.println();
                    }
                }
            }
    }
     public static void counttvs(Node t) {
            Queue<Node> q = new ArrayDeque<>();
            q.add(t);
            while(q.size() != 0) {
                int count = q.size();
                for(int i=0; i<count; i++) {
                    t = q.remove();
                    System.out.print(t.data +" ");
                    for(Node child: t.children) {
                        q.add(child);
                    }
                }
                System.out.println();
            }
     }
     public static void removeLeaf(Node t) {
            for(int i = t.children.size()-1; i>=0; i--) {
                Node ch = t.children.get(i);
                if(ch.children.size() == 0) {
                    t.children.remove(ch);
                }
            }
            for(Node child : t.children) {
                removeLeaf(child);
            }
     }
     public static Node linearize(Node t) {
            if(t.children.size() == 0) {
                return t;
            }
            Node lkt = linearize(t.children.get(t.children.size()-1));
            while(t.children.size()>1) {
                Node lt = t.children.remove(t.children.size()-1);
                Node skt = linearize(t.children.get(t.children.size()-1));
                skt.children.add(lt);
            }
            return lkt;
     }
        // Driver code
    public static void main(String[] args) {
    int arrl[] = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120,-1, -1, 90, -1, -1, 40, 100, -1, -1};
    Stack<Node> st = new Stack<Node>();
    Node root = null;
    for(int i=0; i<arrl.length; i++) {
         if(arrl[i] == -1) {
             st.pop();
         }else {
             Node temp = new Node();
             temp.data = arrl[i];
             if(st.isEmpty()) {
                 root = temp;
                 st.push(temp);
             }else {
                 st.peek().children.add(temp);
                 st.push(temp);
             }
         }
     }
        System.out.println(size(root));
        System.out.println(maax(root));
        System.out.println(height(root ));
        linearize(root);
        display(root);

    }
}