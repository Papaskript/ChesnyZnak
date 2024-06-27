package com.example.docchecnyznac;

import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CheZnakApiClient {

    private final RateLimiter rateLimiter;
    private final String apiUrl;
    private final Gson gson;


    public CheZnakApiClient(double requestPermSecond) {
        this.rateLimiter = RateLimiter.create(requestPermSecond);
        this.apiUrl = "https://ismp.crpt.ru/api/v3/lk/documents/create";
        this.gson = new Gson();



    }
    public synchronized String createDocument(Document document, String signature) throws IOException {
        rateLimiter.acquire();

        String jsonInput = gson.toJson(document);
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type","application/json");
        connection.setRequestProperty("Signature",signature);
        connection.setDoOutput(true);

        try (OutputStream outputStream = connection.getOutputStream()){
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input,0,input.length);
        }

        int responseCode = connection.getResponseCode();

        if(responseCode == 200) {
           try(Scanner scanner = new Scanner(connection.getInputStream(),StandardCharsets.UTF_8)){
               StringBuilder response = new StringBuilder();
               while (scanner.hasNext()){
                   response.append(scanner.nextLine());
               }
               return response.toString();
           }
        }else {
            throw new IOException("Request Filed" + responseCode);
        }

    }

}

