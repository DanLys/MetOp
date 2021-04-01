package method.optimisation;

import org.springframework.data.util.Pair;

public class MethodGoldenRatio {
    private final static double EPSILON = 1E-9;

    private static double f(double x) {
        return 0.2 * x * Math.log10(x) + Math.pow((x - 2.3), 2);
    }

    // Return x : f(x) = min
    public static Pair<Double, Double> goldenRatio(double a, double b, int iterations) {
        // Step 1
        double x1 = a + (3 - Math.sqrt(5)) / 2 * (b - a);
        double x2 = a + (Math.sqrt(5) - 1) / 2 * (b - a);
        double f1 = f(x1);
        double f2 = f(x2);
        final double t = (Math.sqrt(5) - 1) / 2;
        double eps = (b - a) / 2;
        int i = 0;
        // Step 2
        while (iterations > 0) {
            // Step 3
            if (f1 - f2 <= EPSILON) {
                b = x2;
                x2 = x1;
                f2 = f1;
                x1 = b - t * (b - a);
                f1 = f(x1);
            } else {
                a = x1;
                x1 = x2;
                f1 = f2;
                x2 = a + t * (b - a);
                f2 = f(x2);
            }
            eps *= t;
            i++;
            iterations--;
        }
        // Step 4
        return Pair.of(a, b);
    }
}
