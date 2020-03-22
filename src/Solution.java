import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(100_001);
    }

    public boolean hasValidPath(int[][] grid) {
        Queue<int[]> q = new LinkedList<>(); q.add(new int[]{0, 0});
        Set<String> visited = new HashSet<>(); visited.add(0 + " " + 0);
        while (!q.isEmpty()) {
            int[] c = q.poll();
            int street = grid[c[0]][c[1]];

        }
        return false;
    }
}
