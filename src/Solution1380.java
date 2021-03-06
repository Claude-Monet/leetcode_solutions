import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1380 {
    public List<Integer> luckyNumbers (int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] rowMin = new int[m], colMax = new int[n];
        Arrays.fill(rowMin, Integer.MAX_VALUE);
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                rowMin[i] = Math.min(rowMin[i], matrix[i][j]);
                colMax[j] = Math.max(colMax[j], matrix[i][j]);
            }
        }

        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (matrix[i][j] == rowMin[i] && matrix[i][j] == colMax[j]) {
                    ret.add(matrix[i][j]);
                }
            }
        }
        return ret;
    }
}
