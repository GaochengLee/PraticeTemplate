package algo;

/**
 * @author 李高丞
 * @version 1.0
 */
public class Dijkstra {
    private static final int INF = Integer.MAX_VALUE - 1;

    public static void main(String[] args) {
        int[][] weight = {
                {0, 4, INF, 2, INF},
                {4, 0, 4, 1, INF},
                {INF, 4, 0, 1, 3},
                {2, 1, 1, 0, 7},
                {INF, INF, 3, 7, 0},
        };
        int[] path = dijkstra(weight, 0);
    }

    private static int[] dijkstra(int[][] weight, int start) {
        int length = weight.length;
        int[] shortPath = new int[length];
        String[] path = new String[length];
        boolean[] visited = new boolean[length];
        for (int i = 0; i < length; i++) path[i] = start + "-->" + i;

        shortPath[start] = 0;
        visited[start] = true;
        for (int count = 1; count < length; count++) {
            int k = -1;
            int dMin = Integer.MAX_VALUE;
            for (int i = 0; i < length; i++) {
                if (!visited[i] && weight[start][i] < dMin) {
                    dMin = weight[start][i];
                    k = i;
                }
            }
            shortPath[k] = dMin;
            visited[k] = true;
            for (int i = 0; i < length; i++) {
                if (!visited[i] && weight[start][k] + weight[k][i] > weight[start][i]) {
                    weight[start][i] = weight[start][k] + weight[k][i];
                    path[i] = path[k] + "-->" + i;
                }
            }
        }
        for (int i = 0; i < length; i++) System.out.println("从" + start + "出发到" + i + "的最短路径：" + path[i]);
        return shortPath;
    }
}
