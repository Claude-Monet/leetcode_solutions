import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k < 0 || nums == null || nums.length == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < nums.length; i++) {
            while(!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offer(i);
            if(i >= k - 1) {
                res[i - k + 1] = nums[deque.peek()];
            }
        }
        return res;
    }

    public int constrainedSubsetSum(int[] nums, int k) {
        Deque<Integer> d = new ArrayDeque<>();
        int[] rst = new int[nums.length];
        int ret = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            while(!d.isEmpty() && d.peek() < i - k) {
                d.poll();
            }
            rst[i] = nums[i] + (d.isEmpty() ? 0 : (Math.max(rst[d.peek()], 0)));
            while(!d.isEmpty() && rst[d.peekLast()] < rst[i]) d.pollLast();
            d.offer(i);
            ret = Math.max(rst[i], ret);
        }
        return ret;
    }

    public int constrainedSubsetSumDSF_TLE(int[] nums, int k) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MIN_VALUE);
        int ret = dfs(dp, nums, k, 0);
        for(int i = 1; i < dp.length; i++) ret = Math.max(ret, dp[i]);

        return ret;
    }

    private int dfs(int[] dp, int[] nums, int k, int start) {
        if(start == nums.length - 1) {
            dp[start] = nums[nums.length - 1];
            return nums[nums.length - 1];
        }
        if(dp[start] != Integer.MIN_VALUE) return dp[start];

        int ret = nums[start];
        for(int i = start + 1; i <= start + k && i < nums.length; i++) {
            int sum = nums[start] + dfs(dp, nums, k, i);
            ret = Math.max(ret, sum);
        }
        dp[start] = ret;
        return ret;
    }

    private long cbn(long n, long r) {
        if (r == 0 || r == n) return 1;
        if (r > n) return 0;
        long rst =  1;
        for(long i = n; i > n -r; i--) {
            rst = rst * i;
        }
        for(long i = 2; i <= r; i++) {
            rst = rst / i;
        }
        return rst;
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
