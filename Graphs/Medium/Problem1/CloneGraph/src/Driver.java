import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<Integer>> list = List.of(List.of(2,4),List.of(1,3),List.of(2,4),List.of(1,3));
        Node node = buildGraph(list);
        s.cloneGraph(node);
    }
    public static Node buildGraph(List<List<Integer>> adjList) {
        if (adjList == null || adjList.isEmpty()) return null;

        Map<Integer, Node> map = new HashMap<>();

        // Step 1: Create all nodes
        for (int i = 1; i <= adjList.size(); i++) {
            map.put(i, new Node(i));
        }

        // Step 2: Assign neighbors
        for (int i = 1; i <= adjList.size(); i++) {
            Node curr = map.get(i);
            for (int neighbor : adjList.get(i - 1)) {
                curr.neighbors.add(map.get(neighbor));
            }
        }

        return map.get(1); // start node
    }

}
