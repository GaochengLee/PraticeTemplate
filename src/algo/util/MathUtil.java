package algo.util;

public class MathUtil {
    public static double sqrt(int num) {
        int i = 0;
        for (; i < num; i++) {
            if (i * i == num) return i;
            if (i * i > num) break;
        }

        double low = i - 1, high = i, precise = 1e-7;
        double mid, squre;
        while (high - low > precise) {
            mid = (high - low) / 2;
            squre = mid * mid;
            if (squre > num) high = mid;
            else low = mid;
        }
        return (high + low) / 2;
    }
}