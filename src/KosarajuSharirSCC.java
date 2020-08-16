/** % src\tinyDG.txt
 5 strong connected components
 1
 0 2 3 4 5
 9 10 11 12
 6 8
 7
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

public class KosarajuSharirSCC {
    private boolean[] marked;     // marked[v] = has vertex v been visited?
    private int[] id;             // id[v] = id of strong component containing v
    private int count;            // number of strongly-connected components

    public KosarajuSharirSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        count = 0;
        DepthFirstOrder dfo = new DepthFirstOrder(G.reverse()); // reversePostOrder of reverse digraph
        // dfs of vertices in reversePostOrder
        for (int v : dfo.reversePost()) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v))
            if (!marked[w]) {
                dfs(G, w);
            }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        KosarajuSharirSCC scc = new KosarajuSharirSCC(G);

        // number of connected components
        StdOut.println(scc.count() + " strong connected components");

        // collect List of vertices in each Strong components
        LinkedList<Integer>[] components = (LinkedList<Integer>[]) new LinkedList[scc.count()];
        for (int i = 0; i < scc.count(); i++)
            components[i] = new LinkedList<>();

        for (int v = 0; v < G.V(); v++)
            components[scc.id(v)].add(v);

        // print vertices of each scc in line
        for (int i = 0; i < scc.count(); i++) {
            for (int v : components[i])
                StdOut.print(v + " ");
            StdOut.println();
        }
    }
}
