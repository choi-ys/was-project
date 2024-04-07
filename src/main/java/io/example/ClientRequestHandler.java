package io.example;

import io.example.calculator.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientRequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ClientRequestHandler.class);
    public static final String APPLICATION_JSON = "application/json";

    private final Socket clientSocket;

    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        logger.info("[ClientRequestHandler] new client {} started.", Thread.currentThread().getName());
        
        try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()){
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            DataOutputStream dos = new DataOutputStream(out);

            HttpRequest httpRequest = HttpRequest.from(br);

            if(httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")){
                QueryStrings queryStrings = httpRequest.getQueryStrings();

                int firstOperand = Integer.parseInt(queryStrings.getValue("firstOperand"));
                String operator = queryStrings.getValue("operator");
                int secondOperand = Integer.parseInt(queryStrings.getValue("secondOperand"));

                int result = Calculator.calculate(firstOperand, operator, secondOperand);
                byte[] body = String.valueOf(result).getBytes();

                HttpResponse response = new HttpResponse(dos);
                response.response200Header(APPLICATION_JSON, body.length);
                response.responseBody(body);
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
