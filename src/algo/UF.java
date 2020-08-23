package algo;
import java.util.*;

/**
 * @author 李高丞
 * @version 1.0
 */
public class UF {
    // 分量 ID
    private int[] id;
    // 分量数目
    private int count;

    public UF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    public int count() {return count;}
    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
        /* quick-find
        return id[p];
        */
    }
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
        /* quick-find method
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) return;
        for (int i = 0; i < id.length; i++)
            if (id[i] == pID) id[i] = qID;
        count--;
        */
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
        System.out.println(uf.count() + "components");
    }
}
