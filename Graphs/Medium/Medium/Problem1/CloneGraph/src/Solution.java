import java.util.*;

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        return bfs(node);
    }

    private Node bfs(Node node){
        if(node == null)
            return node;
        Set<Node> visited = new HashSet<>();
        Deque<Node> dq = new ArrayDeque<>();
        Map<Node,Node> oldToNewMap = new HashMap<>();
        dq.add(node);

        while(!dq.isEmpty()){
            Node nodeRemmoved = dq.remove();
            if(!visited.contains(nodeRemmoved)){
                Node newNode = new Node(nodeRemmoved.val);
                oldToNewMap.put(nodeRemmoved,newNode);
                visited.add(nodeRemmoved);
                dq.addAll(nodeRemmoved.neighbors);
            }
        }
        visited.clear();

        dq.add(node);
        while(!dq.isEmpty()){
            Node nodeRemmoved = dq.remove();
            if(nodeRemmoved != null&& !visited.contains(nodeRemmoved)){
                Node newN = oldToNewMap.get(nodeRemmoved);
                if(newN != null){
                    List<Node> neighborsList = newN.neighbors;
                    for(Node neigh: nodeRemmoved.neighbors){
                        neighborsList.add(oldToNewMap.get(neigh));
                    }
                    visited.add(nodeRemmoved);
                    dq.addAll(nodeRemmoved.neighbors);
                }
            }
        }
        return oldToNewMap.get(node);
    }
}