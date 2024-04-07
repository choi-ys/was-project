package io.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class QueryStringTest {

    @Test
    @DisplayName("하나의 qeuryString이 주어지면 key value로 분리된 QueryString 객체가 생성된다.")
    void create() {
        // Given
        final String given = "firstOperand=11";

        // When
        QueryString actual = QueryString.from(given);

        // Then
        assertThat(actual.getKey()).isEqualTo("firstOperand");
        assertThat(actual.getValue()).isEqualTo("11");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "operand="})
    @DisplayName("queryString 포맷이 잘못된 경우 예외가 발생한다.")
    void throwException_WhenInvalidQueryStringFormat(final String given) {
        // When & Then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> QueryString.from(given));
    }
}