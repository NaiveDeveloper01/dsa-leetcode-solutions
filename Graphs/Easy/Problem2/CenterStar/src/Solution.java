import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findCenter(int[][] edges) {
        Map<Integer, Integer> degree = new HashMap<>();

        for (int[] edge : edges) {
            int ui = degree.getOrDefault(edge[0], 0);
            int vi = degree.getOrDefault(edge[1], 0);
            degree.put(edge[0], ui + 1);
            degree.put(edge[1], vi + 1);
        }
        for (Integer key : degree.keySet()) {
            if (degree.get(key).equals(degree.size() - 1))
                return key;
        }
        return 0;
    }
}
