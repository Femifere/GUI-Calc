package Calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonHandler implements ActionListener {
    private final JTextField display;


    //Constructor
    public ButtonHandler(JTextField display) {
        this.display = display;
    }

    //Operators and Functionality Implementations
    @Override
    public void actionPerformed(ActionEvent e) {
        //Get Command from the Button that was clicked
        String command = e.getActionCommand();
        switch (command) {
            //Clear
            case "C":
                display.setText("");
                break;
            //Delete
            case "⌫":
                String text = display.getText();
                if (!text.isEmpty()) {
                    String newText = text.substring(0, text.length() - 1);
                    display.setText(newText);
                }
                break;
            //Equals to
            case "=":
                try {
                    String expression = display.getText();
                    double result = MainExpressionEval.evaluateExpression(expression);
                    display.setText(isInteger(result) ? Integer.toString((int) result) : Double.toString(result));
                } catch (RuntimeException ex) {
                    display.setText("Error");
                }
                break;
            //Brackets
            case "()":
                handleBracketInput();
                break;
            //New Input
            default:
                String newText = display.getText() + command;
                display.setText(newText);
                break;
        }
    }

    //Bracket input functionality
    private void handleBracketInput() {
        String text = display.getText();
        int openBrackets = 0;
        int closeBrackets = 0;

        // Count the number of open and close brackets
        for (char c : text.toCharArray()) {
            if (c == '(') {
                openBrackets++;
            } else if (c == ')') {
                closeBrackets++;
            }
        }

        // Decide whether to insert an opening or closing parenthesis
        String newBracket = "";
        if (openBrackets == closeBrackets || openBrackets < closeBrackets) {
            newBracket = "(";
        } else if (openBrackets > closeBrackets) {
            newBracket = ")";
        }

        // Insert the new parenthesis into the expression
        String newText = display.getText() + newBracket;
        display.setText(newText);
    }

    //Check if Double variable can be converted to integer
    private boolean isInteger(double number) {
        return number % 1 == 0;
    }
}
