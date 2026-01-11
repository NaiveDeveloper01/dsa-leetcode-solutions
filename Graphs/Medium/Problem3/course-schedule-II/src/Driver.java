public class Driver {
    public static void main(String[] args) {
        Solution s = new Solution();

        int [] [] courses = new int[][]{
                {0,1},
                {1,0}
        };
        s.findOrder(2,courses);
    }
}
