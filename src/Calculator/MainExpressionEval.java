package Calculator;

public class MainExpressionEval {
    // Method to evaluate a mathematical expression and return the result
    static double evaluateExpression(String expression) {
        // Creating an anonymous inner class to encapsulate the parsing logic
        return new Object() {
            int index = -1, ch; // Variables to keep track of current index and current character

            // Method to move to the next character in the expression
            void nextChar() {
                ch = (++index < expression.length()) ? expression.charAt(index) : -1;
            }

            // Method to consume whitespace and check if the next character matches the expected character
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar(); // Skip whitespace
                if (ch == charToEat) { // If the next character matches the expected character
                    nextChar(); // Move to the next character
                    return true; // Return true indicating successful consumption
                }
                return false; // Return false indicating the character was not consumed
            }

            // Method to start parsing the expression
            double parse() {
                nextChar(); // Move to the first character in the expression
                double x = parseExpression(); // Parse the expression
                if (index < expression.length()) // If there are characters left after parsing
                    throw new RuntimeException("Unexpected: " + (char) ch); // Throw an exception indicating unexpected character
                return x; // Return the parsed result
            }

            // Method to parse an expression
            double parseExpression() {
                double x = parseTerm(); // Parse the first term
                for (;;) { // Infinite loop
                    if (eat('+')) x += parseTerm(); // If next character is '+', parse the next term and add to result
                    else if (eat('-')) x -= parseTerm(); // If next character is '-', parse the next term and subtract from result
                    else return x; // If next character is neither '+', nor '-', return the result
                }
            }

            // Method to parse a term
            double parseTerm() {
                double x = parseFactor(); // Parse the first factor
                for (;;) { // Infinite loop
                    if (eat('*')) x *= parseFactor(); // If next character is '*', parse the next factor and multiply
                    else if (eat('/')) x /= parseFactor(); // If next character is '/', parse the next factor and divide
                    else return x; // If next character is neither '*', nor '/', return the result
                }
            }

            // Method to parse a factor
            double parseFactor() {
                if (eat('+')) return parseFactor(); // If next character is '+', parse the next factor
                if (eat('-')) return -parseFactor(); // If next character is '-', parse the next factor and negate it

                double x;
                int startPos = this.index; // Store the starting position of the factor
                if (eat('(')) { // If next character is '(', indicating start of parentheses
                    x = parseExpression(); // Parse the expression within parentheses
                    eat(')'); // Consume the closing parenthesis
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // If next character is a digit or decimal point
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar(); // Continue parsing digits and decimal point
                    x = Double.parseDouble(expression.substring(startPos, this.index)); // Parse the substring as a double
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch); // Throw an exception for unexpected character
                }

                return x; // Return the parsed factor
            }
        }.parse(); // Start parsing the expression and return the result
    }
}
