import java.util.*;

public class Solution {

    int min = Integer.MAX_VALUE;
    public int assignBikes(int[][] workers, int[][] bikes) {
        int m = workers.length, n = bikes.length;
        boolean[] visited = new boolean[n];
        assign(workers, bikes, visited, 0, 0);
        return min;
    }

    private int dis(int[] worker, int[] bikes) {
        return Math.abs(worker[0] - bikes[0]) + Math.abs(worker[1] - bikes[1]);
    }

    private void assign(int[][] workers, int[][] bikes, boolean[] visited, int idx, int sum) {
        if (idx == workers.length) {
            min = Math.min(min, sum);
            return;
        }

        for(int i = 0; i < bikes.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                assign(workers, bikes, visited, idx + 1, sum + dis(workers[idx], bikes[i]));
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

    }

    public int numPoints(int[][] points, int r) {
        int l = points.length;
        Map<String, Integer> m = new HashMap<>();

        for(int i = 0; i < l; i++) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < l; j++) {
                int x2 = points[j][0], y2 = points[j][1];
                for (int k = j + 1; k < l; k++) {
                    int x3 = points[k][0], y3 = points[k][1];
                    int a = getA(x1, x2, x3, y1, y2, y3), b = getB(x1, x2, x3, y1, y2, y3), c = getC(x1, x2, x3, y1, y2, y3);
                    double x = -(double)b/(2 * a), y = -(double)c/(2 * a);
                    String key = String.format("%.2f", x) + " " + String.format("%.2f", y);
                    m.put(key, m.getOrDefault(key, 0) + 1);
                }
            }
        }

        int max = 0;
        for(Map.Entry<String, Integer> v : m.entrySet()) {
            max = Math.max(max, v.getValue());
        }
        return max;
    }

    int getA(int x1, int x2, int x3, int y1, int y2, int y3) {
        return x1 * (y2 - y3) - y1 * (x2 - x3) + x2 * y3 - x3 * y2;
    }

    int getB(int x1, int x2, int x3, int y1, int y2, int y3) {
        return (x1 * x1 + y1 * y1) * (y3 - y2) + (x2 * x2 + y2 * y2) * (y1 - y3) + (x3 ^ x3 + y3 ^ y3) * (y2 - y1);
    }

    int getC(int x1, int x2, int x3, int y1, int y2, int y3) {
        return (x1 * x1 + y1 * y1) * (x2 - x3) + (x2 * x2 + y2 * y2) * (x3 - x1) + (x3 ^ x3 + y3 ^ y3) * (x1 - x2);
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
