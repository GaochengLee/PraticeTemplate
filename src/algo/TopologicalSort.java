package algo;

import java.util.*;

/**
 * @author 李高丞
 * @version 1.0
 */
public class TopologicalSort {
    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        g.topologicalSort();
    }

    static class Graph {
        private final int V;
        private final LinkedList<Integer>[] adj;

        public Graph(int v) {
            this.V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++) adj[i] = new LinkedList<>();
        }

        public void addEdge(int v, int w) {
            adj[v].add(w);
        }

        public void topologicalSort(int v, boolean[] visited, Deque<Integer> stack) {
            visited[v] = true;
            Integer i;
            for (Integer integer : adj[v]) {
                i = integer;
                if (!visited[i]) topologicalSort(i, visited, stack);
            }
            stack.offerLast(v);
        }

        public void topologicalSort() {
            Deque<Integer> stack = new ArrayDeque<>();
            boolean[] visited = new boolean[V];
            Arrays.fill(visited, false);

            for (int i = 0; i < V; i++) {
                if (!visited[i]) topologicalSort(i, visited, stack);
            }
            while (!stack.isEmpty()) {
                System.out.print(stack.pollLast() + " ");
            }
        }
    }
}
