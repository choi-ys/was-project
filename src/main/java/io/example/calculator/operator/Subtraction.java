package io.example.calculator.operator;

public class Subtraction implements Operator {
    private static final String SUBTRACTION_OPERATOR = "-";

    @Override
    public boolean isMatched(String operator) {
        return operator.equals(SUBTRACTION_OPERATOR);
    }

    @Override
    public int operate(int firstOperand, int secondOperand) {
        return firstOperand - secondOperand;
    }
}
