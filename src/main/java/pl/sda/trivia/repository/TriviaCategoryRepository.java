package pl.sda.trivia.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.sda.trivia.api.CategoryItem;
import pl.sda.trivia.api.TriviaCategory;
import pl.sda.trivia.api.TriviaURL;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TriviaCategoryRepository implements CategoryRepository{
    private HttpClient client = HttpClient.newHttpClient();
    @Override
    public List<CategoryItem> findAll() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://opentdb.com/api_category.php"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            TriviaCategory triviaCategory = mapper.readValue(response.body(), TriviaCategory.class);
            return triviaCategory.getTriviaCategories();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
