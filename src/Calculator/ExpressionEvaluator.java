package Calculator;

import static Calculator.Calculator.isInteger;

public class ExpressionEvaluator {
    public static void evaluateExpression() {
        String expression = Calculator.display.getText();
        try {
            double result = MainExpressionEval.evaluateExpression(expression);
            Calculator.display.setText(isInteger(result) ? Integer.toString((int) result) : Double.toString(result));
        } catch (NumberFormatException | ArithmeticException ex) {
            Calculator.display.setText("Error");
        }
    }
}

