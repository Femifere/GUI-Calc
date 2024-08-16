package Calculator;

import javax.swing.*;
import java.awt.*;
public class Calculator extends JFrame {

    public static RoundedTextField display; //Created Custom Text field
    private JPanel calcPanel; //New Panel
    private ButtonHandler buttonHandler; // Created Custom Button Action eventLister Object

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //Center Window

        // Set application icon
        ImageIcon icon = new ImageIcon("C:\\Users\\fresh\\IdeaProjects\\SPCalc\\src\\Calculator\\calculator.png");
        setIconImage(icon.getImage());

        display = new RoundedTextField();
        display.setEditable(false);
        display.setBackground(Color.GRAY); // Changed background color
        display.setHorizontalAlignment(JTextField.RIGHT); // Aligned Display text to right
        display.setFont(new Font("Origin Tech Demo", Font.PLAIN, 40)); // Increased font size

        buttonHandler = new ButtonHandler(display); //New object of ButtonHandler Class

        calcPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Remove Layout grid lines
                ((Graphics2D) g).setStroke(new BasicStroke(0));
            }
        };
        // Set items Spacing
        calcPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // Set Panel Background
        calcPanel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span the whole width
        gbc.gridheight = 1; // Adjusted grid height for display text field
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(1, 1, 1, 1); // Add insets for spacing

        // Add display field to grid
        calcPanel.add(display, gbc);

        gbc.gridy = 1; // Move to the next row
        gbc.gridwidth = 1; // Reset grid width
        gbc.gridheight = 1; // Reset grid height

        String[][] buttonLabels = {
                {"/", "*", "-", "+"},
                {"7", "8", "9", "⌫"},
                {"4", "5", "6", "C"},
                {"1", "2", "3", "="},
                {"0", ".", "()", ""}
        };

        for (int i = 0; i < buttonLabels.length; i++) {
            for (int j = 0; j < buttonLabels[i].length; j++) {
                RoundedButton button = new RoundedButton(buttonLabels[i][j]);
                button.setFont(new Font("Arial Unicode MS", Font.BOLD, 18));
                button.setFocusPainted(false);
                button.setMargin(new Insets(0, 0, 0, 0));
                button.addActionListener(buttonHandler);

                if (buttonLabels[i][j].matches("[/*\\-+=]")) {
                    button.setBackground(new Color(0, 0, 139)); // Deep Blue
                    button.setForeground(Color.WHITE);
                } else if (buttonLabels[i][j].equals("C") || buttonLabels[i][j].equals("⌫") || buttonLabels[i][j].equals(".") || buttonLabels[i][j].equals("()")) {
                    button.setBackground(Color.DARK_GRAY);
                    button.setForeground(Color.WHITE);
                } else {
                    button.setBackground(new Color(0x1E1E1E));
                    button.setForeground(Color.WHITE);
                }

                gbc.gridx = j;

                // Span '=' button for 2 rows
                if (buttonLabels[i][j].equals("=")) {
                    gbc.gridheight = 2;
                } else {
                    gbc.gridheight = 1;
                }

                calcPanel.add(button, gbc);
            }
            gbc.gridy++; // Move to the next row
        }

        add(calcPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static boolean isInteger(double number) {
        return Math.floor(number) == number;
    }

}














