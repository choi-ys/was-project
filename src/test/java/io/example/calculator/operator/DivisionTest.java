package io.example.calculator.operator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Operator:Division")
class DivisionTest {
    @Test
    @DisplayName("주어진 연산자가 나눗셈 연산자 인지 확인 한다.")
    void matchedDivisionOperator() {
        // Given
        final String DivisionOperator = "/";

        // When
        Division division = new Division();

        // Then
        assertThat(division.isMatched(DivisionOperator)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"+", "-", "*", "%"})
    @DisplayName("나눗셈 연산자가 아닌 경우 일치 하지 않는다.")
    void isNotMatchedByOtherOperator(final String given) {
        // Given
        Division division = new Division();

        // When
        boolean actual = division.isMatched(given);

        // Then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("나눗셈 연산자를 이용하여 나눗셈을 수행한다.")
    void Division() {
        // Given
        final int firstOperand = 2;
        final int secondOperand = 2;
        final int expected = firstOperand / secondOperand;

        Division division = new Division();

        // When
        int actual = division.operate(firstOperand, secondOperand);

        // Then
        assertThat(actual).isEqualTo(expected);
    }
}
