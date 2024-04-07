package io.example.calculator.operator;

public interface Operator {
    boolean isMatched(String operator);
    int operate(int firstOperand, int secondOperand);
}
