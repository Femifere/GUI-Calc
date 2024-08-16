package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

// Custom rounded JTextField
public class RoundedTextField extends JTextField {
    private static final int ARC_WIDTH = 30;
    private static final int ARC_HEIGHT = 30;

    public RoundedTextField() {
        setOpaque(false);
    }

    @Override
    public boolean isFocusable() {
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT));
            g2.dispose();
        }
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT));
        g2.dispose();
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            char keyChar = e.getKeyChar();
            if (Character.isDigit(keyChar) || "+-*/.()".indexOf(keyChar) != -1) {
                String newText = getText() + keyChar;
                setText(newText);
            } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                String text = getText();
                if (!text.isEmpty()) {
                    String newText = text.substring(0, text.length() - 1);
                    setText(newText);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                ExpressionEvaluator.evaluateExpression();
            }
        }
    }



}






