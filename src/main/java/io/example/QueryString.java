package io.example;

import java.util.Objects;

public class QueryString {
    private final String key;
    private final String value;

    private QueryString(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static QueryString from(String queryString) {
        String[] queryStringTokens = queryString.split("=");
        validateQueryStringFormat(queryStringTokens);
        return new QueryString(queryStringTokens[0], queryStringTokens[1]);
    }

    private static void validateQueryStringFormat(String[] queryStringTokens) {
        if (queryStringTokens.length != 2) {
            throw new IllegalArgumentException("잘못된 queryString 포맷을 가진 문자열입니다. key=value 형태로 이루어진 queryString을 입력하세요.");
        }
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryString that = (QueryString) o;
        return Objects.equals(key, that.key) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    public boolean exist(String target) {
        return key.equals(target);
    }
}
