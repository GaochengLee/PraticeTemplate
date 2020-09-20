package algo;
import java.util.*;

/**
 * @author 李高丞
 * @version 1.0
 */
public class UF {
    // 分量 ID
    private final int[] id;

    public UF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) id[i] = i;
    }

    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    public void union(int p, int q) {
        id[find(p)] = find(q);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        UF uf = new UF(N);
        while (sc.hasNext()) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
        }
    }
}
