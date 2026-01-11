import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] findOrder(int n, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int[] p : prerequisites)
            graph.get(p[0]).add(p[1]);

        int[] state = new int[n]; // 0=unvisited,1=visiting,2=visited
        List<Integer> order = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (state[i] == 0 && hasCycle(i, graph, state, order))
                return new int[] {};
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++)
            res[i] = order.get(i);
        return res;
    }

    private boolean hasCycle(int node, List<List<Integer>> graph, int[] state, List<Integer> order) {
        if (state[node] == 1) return true;  // cycle
        if (state[node] == 2) return false; // already processed

        state[node] = 1;
        for (int nei : graph.get(node))
            if (hasCycle(nei, graph, state, order))
                return true;

        state[node] = 2;
        order.add(node);
        return false;
    }
}
