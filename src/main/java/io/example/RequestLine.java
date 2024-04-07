package io.example;

public class RequestLine {
    public static final String URL_PATH_DELIMITER = "\\?";
    public static final String SPACE = " ";

    private final HttpMethod httpMethod;
    private final String path;
    private QueryStrings queryStrings;

    private RequestLine(HttpMethod httpMethod, String path, QueryStrings queryStrings) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.queryStrings = queryStrings;
    }

    private RequestLine(HttpMethod httpMethod, String path) {
        this.httpMethod = httpMethod;
        this.path = path;
    }

    public static RequestLine from(String requestLine) {
        String[] requestLineTokens = requestLine.split(SPACE);
        String[] urlPathTokens = requestLineTokens[1].split(URL_PATH_DELIMITER);
        HttpMethod httpMethod = HttpMethod.valueOf(requestLineTokens[0]);

        if (existQueryString(urlPathTokens)) {
            return new RequestLine(httpMethod, urlPathTokens[0], QueryStrings.from(urlPathTokens[1]));
        }
        return new RequestLine(httpMethod, urlPathTokens[0]);
    }

    private static boolean existQueryString(String[] urlPathTokens) {
        return urlPathTokens.length == 2;
    }

    public boolean isGetRequest() {
        return httpMethod.isGetRequest();
    }

    public boolean matchPath(String target) {
        return path.equals(target);
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getPath() {
        return path;
    }

    public QueryStrings getQueryStrings() {
        return queryStrings;
    }
}
