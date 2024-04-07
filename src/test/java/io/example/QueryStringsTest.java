package io.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("QueryStrings")
class QueryStringsTest {
    private static final String givenQueryString = "firstOperand=11&operator=*&secondOperand=55";

    @Test
    @DisplayName("여러개의 queryString으로 이루어진 문자열이 주어지면 QueryString 리스트로 분리된 QueryStrings 일급컬렉션이 생성된다.")
    void create() {
        // When
        QueryStrings actual = QueryStrings.from(givenQueryString);

        // Then
        assertThat(actual.size()).isEqualTo(3);
        assertThat(actual.containsAll(List.of(
                QueryString.from("firstOperand=11"),
                QueryString.from("operator=*"),
                QueryString.from("secondOperand=55")
        ))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "operand="})
    @DisplayName("queryString 포맷이 잘못된 경우 예외가 발생한다.")
    void throwException_WhenInvalidQueryStringFormat(final String given) {
        // When & Then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> QueryStrings.from(given));
    }


    @Test
    @DisplayName("QueryString 목록에서 key에 해당하는 value를 가져온다.")
    void getValue() {
        // Given
        final QueryStrings given = QueryStrings.from(givenQueryString);
        final String expected = "11";

        // When
        String actual = given.getValue("firstOperand");

        // Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("QueryString 목록에서 해당하는 key값이 없는 경우 예외가 발생한다.")
    void throwException_WhenNotExistKey() {
        // Given
        QueryStrings from = QueryStrings.from(givenQueryString);

        // When & Then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> from.getValue("notExistKey"));
    }
}