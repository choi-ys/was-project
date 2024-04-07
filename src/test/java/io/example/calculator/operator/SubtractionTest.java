package io.example.calculator.operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Operator:Subtraction")
class SubtractionTest {
    @Test
    @DisplayName("주어진 연산자가 뺄셈 연산자 인지 확인 한다.")
    void matchedSubtractionOperator() {
        // Given
        final String SubtractionOperator = "-";

        // When
        Subtraction Subtraction = new Subtraction();

        // Then
        assertThat(Subtraction.isMatched(SubtractionOperator)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"+", "/", "*", "%"})
    @DisplayName("뺄셈 연산자가 아닌 경우 일치 하지 않는다.")
    void isNotMatchedByOtherOperator(final String given) {
        // Given
        Subtraction Subtraction = new Subtraction();

        // When
        boolean actual = Subtraction.isMatched(given);

        // Then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("뺄셈 연산자를 이용하여 뺄셈을 수행한다.")
    void Subtraction() {
        // Given
        final int firstOperand = 2;
        final int secondOperand = 2;
        final int expected = firstOperand - secondOperand;

        Subtraction Subtraction = new Subtraction();

        // When
        int actual = Subtraction.operate(firstOperand, secondOperand);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
