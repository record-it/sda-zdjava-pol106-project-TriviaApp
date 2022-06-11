package pl.sda.trivia;

import pl.sda.trivia.api.Difficulty;
import pl.sda.trivia.api.TriviaURL;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TriviaAppSimple {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(TriviaURL.builder()
                        .amount(5)
                        .difficulty(Difficulty.EASY)
                        .build()
                        .getURL()
                )
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
