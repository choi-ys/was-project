package io.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;

public class HttpResponse {
    private static final Logger logger = LoggerFactory.getLogger(HttpResponse.class);

    private static final String CRLF = "\r\n";
    private static final String HTTP_200_OK_MESSAGE = "HTTP/1.1 200 OK %s" + CRLF;
    private static final String CONTENT_TYPE_MESSAGE = "Content-Type: %s;charset=utf-8" + CRLF;
    private static final String CONTENT_LENGTH_MESSAGE = "Content-Length: %s" + CRLF;


    private final DataOutputStream dos;

    public HttpResponse(DataOutputStream dos) {
        this.dos = dos;
    }

    public void response200Header(String contentType, int lengthOfBodyContent) {
        try {
            dos.writeBytes(HTTP_200_OK_MESSAGE);
            dos.writeBytes(String.format(CONTENT_TYPE_MESSAGE, contentType));
            dos.writeBytes(String.format(CONTENT_LENGTH_MESSAGE, lengthOfBodyContent));
            dos.writeBytes(CRLF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void responseBody(byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        }catch (IOException e) {
            logger.error(e.getMessage());

        }
    }
}
