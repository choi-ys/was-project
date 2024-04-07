package io.example.calculator.operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Operator:Multiplication")
class MultiplicationTest {
    @Test
    @DisplayName("주어진 연산자가 곱셈 연산자 인지 확인 한다.")
    void matchedMultiplicationOperator() {
        // Given
        final String MultiplicationOperator = "*";

        // When
        Multiplication multiplication = new Multiplication();

        // Then
        assertThat(multiplication.isMatched(MultiplicationOperator)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"+", "-", "/", "%"})
    @DisplayName("곱셈 연산자가 아닌 경우 일치 하지 않는다.")
    void isNotMatchedByOtherOperator(final String given) {
        // Given
        Multiplication multiplication = new Multiplication();

        // When
        boolean actual = multiplication.isMatched(given);

        // Then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("곱셈 연산자를 이용하여 곱셈을 수행한다.")
    void Multiplication() {
        // Given
        final int firstOperand = 2;
        final int secondOperand = 2;
        final int expected = firstOperand * secondOperand;

        Multiplication multiplication = new Multiplication();

        // When
        int actual = multiplication.operate(firstOperand, secondOperand);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
