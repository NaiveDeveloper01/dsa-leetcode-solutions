import java.util.*;

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for(int [] edge : edges){
            List<Integer> list1 = adjacencyList.getOrDefault(edge[0], new ArrayList<>());
            list1.add(edge[1]);
            adjacencyList.put(edge[0],list1);

            List<Integer> list2 = adjacencyList.getOrDefault(edge[1], new ArrayList<>());
            list2.add(edge[0]);
            adjacencyList.put(edge[1],list2);
        }

        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(source);
        HashSet<Integer> visited = new HashSet<>();
        while(!deque.isEmpty()){
            int node = deque.remove();
            if(node == destination)
                return true;
            if(!visited.contains(node)){
                List<Integer> children = adjacencyList.getOrDefault(node, new ArrayList<>());
                children.forEach(child->{
                    if(!visited.contains(child)){
                        deque.add(child);
                    }
                });
                visited.add(node);
            }
        }
        return false;
    }
}