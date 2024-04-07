package io.example;

public enum HttpMethod {
    GET,
    POST,
    PATCH,
    PUT,
    DELETE;

    public boolean isGetRequest() {
        return this == GET;
    }
}
