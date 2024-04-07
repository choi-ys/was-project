package io.example;

import java.util.*;

public class QueryStrings {
    public static final String QUERY_STRING_DELIMITER = "&";
    public static final String NOT_EXIST_KEY_EXCEPTION_MESSAGE = "해당하는 key가 존재하지 않습니다.";

    private final Set<QueryString> elements = new HashSet<>();

    private QueryStrings(String queryStrings) {
        Arrays.stream(queryStrings.split(QUERY_STRING_DELIMITER))
                .forEach(queryString -> elements.add(QueryString.from(queryString))
                );
    }

    public static QueryStrings from(String queryStrings) {
        return new QueryStrings(queryStrings);
    }

    public int size() {
        return elements.size();
    }

    public Set<QueryString> getElements() {
        return Collections.unmodifiableSet(elements);
    }

    public boolean containsAll(List<QueryString> queryStrings) {
        return elements.containsAll(queryStrings);
    }

    public String getValue(String key) {
        return elements.stream().filter(queryString -> queryString.exist(key))
                .map(QueryString::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_KEY_EXCEPTION_MESSAGE));
    }
}
