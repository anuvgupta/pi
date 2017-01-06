import java.awt.Font;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pi {
    static double precision = 5;
    static double radius = 10;
    static boolean run = false;
    public static double[] pi(double prec, double rad) {
        double pi = 0; // current calculated pi
        double sc = 0; // semicircle (half) circumference
        double r = rad; // semicircle radius
        double n = Math.pow(10, prec); // precision (# of iterations)
        double preTime = System.currentTimeMillis(); // millitime before calculation
        for (double i = 0; i < n; i++) { // sigma from i = 0 to n
            double xi0 = 2 * r * i / n - r; // current x coordinate on circle
            double xi1 = 2 * r * (i + 1) / n - r; // next x coordinate on circle
            double fxi0 = Math.sqrt(r * r - xi0 * xi0); // current y coordinate on circle
            double fxi1 = Math.sqrt(r * r - xi1 * xi1); // next y coordinate on circle
            // add distance between two above points to the semicircle circumference
            sc += Math.sqrt(Math.pow(xi1 - xi0, 2) + Math.pow(fxi1 - fxi0, 2));
            pi = sc / r; // calculate pi from reduced circumference/diameter formula
            update(i + 1, n, r, sc * 2, pi); // update window
        }
        double postTime = System.currentTimeMillis(); // millitime after calculation
        return new double[] { pi, (postTime - preTime) / 1000 };
    }
    static JLabel iterationL;
    static JLabel radiusL;
    static JLabel circumferenceL;
    static JLabel calcpiL;
    static JLabel mathpiL;
    static JProgressBar bar;
    static JLabel barL;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Approximating Pi");
        frame.setSize(500, 320);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel titleL = new JLabel("π");
        titleL.setFont(new Font("Helvetica Bold", Font.PLAIN, 25));
        titleL.setSize(100, 50);
        titleL.setLocation(245, 5);
        panel.add(titleL);

        iterationL = new JLabel("Iteration: 0 of 0");
        iterationL.setSize(300, 50);
        iterationL.setLocation(120, 60);
        panel.add(iterationL);

        radiusL = new JLabel("Radius: 0.0");
        radiusL.setSize(300, 50);
        radiusL.setLocation(120, 40);
        panel.add(radiusL);

        circumferenceL = new JLabel("Circumference: 0.0");
        circumferenceL.setSize(300, 50);
        circumferenceL.setLocation(120, 80);
        panel.add(circumferenceL);

        calcpiL = new JLabel("Calculated π: 0.0");
        calcpiL.setSize(300, 50);
        calcpiL.setLocation(120, 100);
        panel.add(calcpiL);

        mathpiL = new JLabel("Java Math.PI:  " + Math.PI);
        mathpiL.setSize(350, 50);
        mathpiL.setLocation(120, 120);
        panel.add(mathpiL);

        JLabel radiusTFL = new JLabel("Radius: ");
        radiusTFL.setSize(200, 50);
        radiusTFL.setLocation(120, 185);
        panel.add(radiusTFL);
        JTextField radiusTF = new JTextField();
        radiusTF.setText("10.0");
        radiusTF.setSize(139, 26);
        radiusTF.setLocation(168, 198);
        panel.add(radiusTF);

        JLabel precisionTFL = new JLabel("Precision: ");
        precisionTFL.setSize(200, 50);
        precisionTFL.setLocation(120, 205);
        panel.add(precisionTFL);
        JTextField precisionTF = new JTextField();
        precisionTF.setText("5.0");
        precisionTF.setSize(125, 26);
        precisionTF.setLocation(182, 218);
        panel.add(precisionTF);

        JButton start = new JButton("Find π");
        start.setSize(250, 30);
        start.setLocation(130, 250);
        start.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String radiusValText = radiusTF.getText();
                    double radiusVal = 0;
                    try {
                        radiusVal = Double.parseDouble(radiusValText);
                    } catch (NumberFormatException nfe) {
                        radiusVal = 10;
                    }
                    radiusTF.setText(Double.toString(radiusVal));

                    String precisionValText = precisionTF.getText();
                    double precisionVal = 0;
                    try {
                        precisionVal = Double.parseDouble(precisionValText);
                    } catch (NumberFormatException nfe) {
                        precisionVal = 5;
                    }
                    precisionTF.setText(Double.toString(precisionVal));

                    radius = radiusVal;
                    precision = precisionVal;
                    run = true;
                }
            });
        panel.add(start);
        
        bar = new JProgressBar(1, 1000000);
        bar.setValue(0);
        bar.setSize(230, 20);
        bar.setLocation(115, 165);
        panel.add(bar);
        barL = new JLabel("0%");
        barL.setSize(75, 50);
        barL.setLocation(355, 149);
        panel.add(barL);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        while (true) {
            System.out.println("π");
            if (run) {
                double[] result = pi(precision, radius);
                System.out.println("\n" +
                    "Radius: " + radius + "\n" +
                    "Precision: " + precision + "\n" +
                    "Pi Approximation: " + result[0] + "\n" +
                    "Seconds for Calculation: " + result[1] + "\n"
                );
                run = false;
            }
            try {
                Thread.sleep(500);
            } catch (Exception e) { }
        }
    }

    private static void update(double i, double n, double r, double c, double pi) {
        iterationL.setText("Iteration: " + String.format("%.0f", i) + " of " +String.format("%.0f", n));
        radiusL.setText("Radius: " + r);
        circumferenceL.setText("Circumference: " + c);
        calcpiL.setText("Calculated π: " + pi);
        double complete = i / n;
        bar.setValue((int) (complete * ((double) bar.getMaximum())));
        barL.setText(String.format("%.0f%%", complete * 100.0));
    }
}