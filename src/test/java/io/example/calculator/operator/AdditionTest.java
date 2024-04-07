package io.example.calculator.operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Operator:Addition")
class AdditionTest {
    @Test
    @DisplayName("주어진 연산자가 덧셈 연산자 인지 확인 한다.")
    void matchedAdditionOperator() {
        // Given
        final String additionOperator = "+";

        // When
        Addition addition = new Addition();

        // Then
        assertThat(addition.isMatched(additionOperator)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"-", "/", "*", "%"})
    @DisplayName("덧셈 연산자가 아닌 경우 일치 하지 않는다.")
    void isNotMatchedByOtherOperator(final String given) {
        // Given
        Addition addition = new Addition();

        // When
        boolean actual = addition.isMatched(given);

        // Then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("덧셈 연산자를 이용하여 덧셈을 수행한다.")
    void addition() {
        // Given
        final int firstOperand = 2;
        final int secondOperand = 2;
        final int expected = firstOperand + secondOperand;

        Addition addition = new Addition();

        // When
        int actual = addition.operate(firstOperand, secondOperand);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
