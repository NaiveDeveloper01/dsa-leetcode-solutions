import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int [] degrees = new int [numCourses+1];
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        Deque<Integer> zeroDegree = new ArrayDeque<>();
        // creating the adjacency list and updating the in degrees.
        for(int [] course: prerequisites){
            List<Integer> neighbours = adjacencyList.getOrDefault(course[0], new ArrayList<>());
            neighbours.add(course[1]);
            degrees[course[1]]++;
            adjacencyList.put(course[0],neighbours);
        }

        // checking nodes with 0 degree.
        for(int i = 0 ; i<degrees.length; i++){
            if(degrees[i] == 0)
                zeroDegree.add(i);
        }

        // processing nodes with 0 degree
        while(!zeroDegree.isEmpty()){
            int node = zeroDegree.remove();
            for(int neigh: adjacencyList.getOrDefault(node, new ArrayList<>())){
                degrees[neigh]--;
                if(degrees[neigh] == 0){
                    zeroDegree.add(neigh);
                }
            }
        }

        // if any node does not have any degree less than 0 then its a cycle.
        for(int i : degrees){
            if(i > 0)
                return false;
        }
        return true;

    }

}