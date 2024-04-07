package io.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("RequestLine")
class RequestLineTest {

    @Test
    @DisplayName("HTTP 요청의 첫 라인인 RequestLine이 주어지면 HttpMethod, path로 분리된 RequestLine객체가 생성된다.")
    void createWithoutQueryStringRequestLine() {
        // Given
        final String given = "GET /calculate HTTP/1.1";

        // When
        RequestLine requestLine = RequestLine.from(given);

        // Then
        assertThat(requestLine.getHttpMethod()).isEqualTo(HttpMethod.GET);
        assertThat(requestLine.getPath()).isEqualTo("/calculate");
    }

    @Test
    @DisplayName("HTTP 요청의 첫 라인인 RequestLine이 주어지면 HttpMethod, path, queryString으로 분리된 RequestLine객체가 생성된다.")
    void createWithQueryStringRequestLine() {
        // Given
        final String given = "GET /calculate?firstOperand=11&operator=*&secondOperand=55 HTTP/1.1";

        // When
        RequestLine requestLine = RequestLine.from(given);

        // Then
        assertThat(requestLine.getHttpMethod()).isEqualTo(HttpMethod.GET);
        assertThat(requestLine.getPath()).isEqualTo("/calculate");
        assertThat(requestLine.getQueryStrings().getElements()).isEqualTo(Set.of(
                QueryString.from("firstOperand=11"),
                QueryString.from("operator=*"),
                QueryString.from("secondOperand=55")
        ));
    }
}