import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class Pi {
    static int precision = 5;
    static double radius = 10;
    static boolean run = false;
    public static void pi(int prec, double rad) {
        double pi = 0; // current calculated pi
        double sc = 0; // semicircle (half) circumference
        double r = rad; // semicircle radius
        double n = Math.pow(10, prec); // precision (# of iterations)
        for (double i = 1; i < n; i++) { // sigma from i = 1 to n
            double xi0 = 2 * r * i / n - r; // current x coordinate on circle
            double xi1 = 2 * r * (i + 1) / n - r; // next x coordinate on circle
            double fxi0 = Math.sqrt(r * r - xi0 * xi0); // current y coordinate on circle
            double fxi1 = Math.sqrt(r * r - xi1 * xi1); // next y coordinate on circle
            // add distance between two above points to the semicircle circumference
            sc += Math.sqrt(Math.pow(xi1 - xi0, 2) + Math.pow(fxi1 - fxi0, 2));
            pi = sc / r; // calculate pi from reduced circumference/diameter formula
            update(i + 1, n, r, sc * 2, pi); // update window
        }
    }

    private static void bg() {
        while (true) {
            System.out.println("π");
            if (run) {
                pi(precision, radius);
                run = false;
            }
            try {
                Thread.sleep(500);
            } catch (Exception e) { }
        }
    }

    static JLabel iterationL;
    static JLabel radiusL;
    static JLabel circumferenceL;
    static JLabel calcpiL;
    static JLabel mathpiL;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Finding Pi");
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
        radiusTFL.setLocation(120, 150);
        panel.add(radiusTFL);
        JTextField radiusTF = new JTextField();
        radiusTF.setText("10.0");
        radiusTF.setSize(139, 26);
        radiusTF.setLocation(168, 163);
        panel.add(radiusTF);

        JLabel precisionTFL = new JLabel("Precision: ");
        precisionTFL.setSize(200, 50);
        precisionTFL.setLocation(120, 170);
        panel.add(precisionTFL);
        JTextField precisionTF = new JTextField();
        precisionTF.setText("5");
        precisionTF.setSize(125, 26);
        precisionTF.setLocation(182, 183);
        panel.add(precisionTF);

        JButton start = new JButton("Find π");
        start.setSize(250, 30);
        start.setLocation(130, 220);
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
                    int precisionVal = 0;
                    try {
                        precisionVal = Integer.parseInt(precisionValText);
                    } catch (NumberFormatException nfe) {
                        precisionVal = 5;
                    }
                    precisionTF.setText(Integer.toString(precisionVal));

                    radius = radiusVal;
                    precision = precisionVal;
                    run = true;
                }
            });
        panel.add(start);

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        bg();
    }

    private static void update(double i, double n, double r, double c, double pi) {
        iterationL.setText("Iteration: " + ((int) i) + " of " + ((int) n));
        radiusL.setText("Radius: " + r);
        circumferenceL.setText("Circumference: " + c);
        calcpiL.setText("Calculated π: " + pi);
    }
}