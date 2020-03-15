import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    final int M = 1000000000 + 7;

    class SE implements Comparable<SE>{
        int speed;
        int efficiency;
        public SE(int speed, int efficiency) {
            this.speed = speed;
            this.efficiency = efficiency;
        }

        @Override
        public int compareTo(SE o) {
            if (o.efficiency - this.efficiency != 0) {
                return -o.efficiency + this.efficiency;
            }
            return -o.speed + this.speed;
        }
    }
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        SE[] array = new SE[n];
        for(int i = 0; i < n; i++) {
            array[i] = new SE(speed[i],efficiency[i]);
        }
        Arrays.sort(array);
        int sum = 0;
        int minEf = 0;
        double ret = 0;
        for(int i = n - 1; i >= n - k; i--) {
            sum += speed[i];
            minEf = Math.min(minEf, array[i].efficiency);
        }
        ret = sum * minEf;
        for(int i = n - k - 1; i >= 0; i--) {
            sum = sum - array[i + k].speed + array[i].speed;
            minEf = Math.min(minEf, array[i].efficiency);
            ret = Math.min(ret, sum * minEf);
        }
        return (int)(ret % M);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] speed = new int[]{2,10,3,1,5,8}, eff = new int[]{5,4,3,9,7,2};

        System.out.println(100_001);
    }

}
