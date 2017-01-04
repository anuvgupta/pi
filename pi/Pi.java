
public class Pi {
    public static void main(String[] args) {
        double pi = 0;

        // find length of circumference
        double c = 0;
        double r = 20;
        double n = 10e+7;
        for (double i = 1; i < n; i++) {
            double xi0 = 2 * r * i / n - r;
            double xi1 = 2 * r * (i + 1) / n - r;
            double fxi0 = Math.sqrt(r * r - xi0 * xi0);
            double fxi1 = Math.sqrt(r * r - xi1 * xi1);
            c += Math.sqrt(Math.pow(xi1 - xi0, 2) + Math.pow(fxi1 - fxi0, 2));
            System.out.printf("(%.5f, %.5f) | (%.5f, %.5f) %n", xi0, fxi0, xi1, fxi1);
        }
        c *= 2;
        
        // find pi from circumference
        pi = c / (2 * r);
        System.out.printf("Calc Pi: %.20f%n", pi);
        System.out.printf("Math.PI: %.20f%n", Math.PI);
    }
}