// запуск через зк

/** % tinyDG.txt
 * 13 vertices, 22 edges
 0: 1 5
 1:
 2: 3 0
 3: 2 5
 4: 2 3
 5: 4
 6: 0 8 4 9
 7: 9 6
 8: 6
 9: 10 11
 10: 12
 11: 12 4
 12: 9 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Digraph {

    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V; // number of vertices in  this graph
    private int E; // number of edges in this graph
    private List<Integer>[] adj; // adjacency lists
    private int[] indegree; // indegree[v] = indegree of vertex v

    // create an empty Digraph with V vertices
    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (List<Integer>[]) new List[V];
        for (int v = 0; v < V; v++)
            adj[v] = new LinkedList<Integer>();
    }

    /**try – определяет блок кода, в котором может произойти исключение;
     catch – определяет блок кода, в котором происходит обработка исключения; */

    // create Digrpah from input stream
    public Digraph(In in) {
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in Graph must be nonnegative");
            indegree = new int[V];
            adj = (List<Integer>[]) new List[V];
            for (int v = 0; v < V; v++)
                adj[v] = new LinkedList<Integer>();

            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("number of vertices in Graph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w);
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
        }
    }

    // add edge v -> w
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
        indegree[w]++;
    }

    // iterator for vertices pointing from v
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // number of vertices
    public int V() {
        return V;
    }

    // number of edges
    public int E() {
        return E;
    }

    // indegree of vertex v
    public int indegree(int v) {
        return indegree[v];
    }

    // outdegree of vertex v
    public int outdegree(int v) {
        return adj[v].size();
    }

    // reverse of this Digraph
    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++)
            for (int w : adj(v))
                reverse.addEdge(w, v);
        return reverse;
    }

    // string representation
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj(v)) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {

        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        StdOut.println(G);
    }
}
