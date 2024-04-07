package io.example.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Calculator:V5")
class CalculatorTest {
    private static final int firstOperand = 2;
    private static final int secondOperand = 2;

    @ParameterizedTest(name = "[case:#{index}]{0}{1}{2}={3}")
    @MethodSource
    @DisplayName("사칙 연산")
    void calculate(
        final int firstOperand,
        final String operator,
        final int secondOperand,
        final int expected
    ) {
        //When
        int actual = Calculator.calculate(firstOperand, operator, secondOperand);

        // Then
        assertEquals(actual, expected);
    }

    static Stream<Arguments> calculate() {
        return Stream.of(
            Arguments.of(2, "+", 2, 4),
            Arguments.of(2, "-", 2, 0),
            Arguments.of(2, "/", 2, 1),
            Arguments.of(2, "*", 2, 4)
        );
    }

    @Test
    @DisplayName("0으로 나누는 경우 예외가 발생한다.")
    void throwException_WhenDivisorIsZero() {
        // Given
        final int zeroDivisor = 0;
        final String operator = "/";

        // When & Then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Calculator.calculate(firstOperand, operator, zeroDivisor));
    }

    @Test
    @DisplayName("사칙 연산이 아닌 경우 예외가 발생한다.")
    void throwException_GivenNotForBasicOperations() {
        // Given
        final String wrongOperator = "%";

        // When & Then
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Calculator.calculate(firstOperand, wrongOperator, secondOperand));
    }
}
