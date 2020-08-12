//зк

/** % tinyDAG.txt
 * Preorder: 0 6 4 9 12 10 11 1 5 2 3 7 8
 Topological order: 8 7 2 3 0 5 1 6 9 11 10 12 4
 */

/**
 * Topological order for DAG
 * DAG - Directed Acyclic Graph*/

/***/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DepthFirstOrder {

    private boolean[] marked;
    private Deque<Integer> reversePost; // Topological order (Deque = Stack iterator)
    private Queue<Integer> preorder;

    public DepthFirstOrder(Digraph G) {
        marked = new boolean[G.V()];
        reversePost = new ArrayDeque<>();
        preorder = new LinkedList<>();

        for (int v = 0; v < G.V(); v++)
            if (!marked[v])
                dfs(G, v);

    }

    // recursive search of connected vertices to v
    // + preorder
    // + reverse postorder
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        preorder.add(v);

        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
        reversePost.push(v);
    }

    // returns all vertices in preorder
    public Iterable<Integer> preorder() {
        return preorder;
    }

    // returns all vertices in reverse post order (Topological order)
    public Iterable<Integer> reversePost() {
        return  reversePost;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        DepthFirstOrder dfo = new DepthFirstOrder(G);

        // print preorder
        StdOut.print("Preorder: ");
        for (int v : dfo.preorder())
            StdOut.print(v + " ");
        StdOut.println();

        // print reverse postorder (Topological order for DAG)
        StdOut.print("Topological order: ");
        for (int v : dfo.reversePost())
            StdOut.print(v + " ");
        StdOut.println();
    }
}
