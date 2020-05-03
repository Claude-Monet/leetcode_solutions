import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.stoneGameIII(new int[]{1,2,3, -9}));
    }

    public String stoneGameIII(int[] piles) {
        int[] sum = new int[piles.length];

        int s = 0;
        for (int i = piles.length - 1; i >= 0; i--) {
            s += piles[i];
            sum[i] = s;
        }

        int[] dp = new int[piles.length];
        boolean[] visited = new boolean[piles.length];

        int alex = search(piles, dp, visited, sum, 0);
        int bob = sum[0] - alex;

        System.out.println("Alice: " + alex);
        System.out.println("Bob: " + bob);
        if (alex > bob) {
            return "Alice";
        } else if (bob > alex){
            return "Bob";
        } else {
            return "Tie";
        }
    }

    private int search(int[] piles, int[] dp, boolean[] visited, int[] sum, int l) {
        if (l >= piles.length) return 0;
        if (visited[l]) {
            return dp[l];
        }
        visited[l] = true;

        int t1 = search(piles, dp, visited, sum, l+1);
        int t2 = search(piles, dp, visited, sum, l+2);
        int t3 = search(piles, dp, visited, sum, l+3);
        dp[l] = sum[l] - Math.min(Math.min(t1, t2), t3);

        return dp[l];
    }
}
