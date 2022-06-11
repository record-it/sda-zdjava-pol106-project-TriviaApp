package pl.sda.trivia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pl.sda.trivia.api.Category;
import pl.sda.trivia.api.Difficulty;
import pl.sda.trivia.api.Type;

import java.util.List;

@Data
public class Quiz {
    @JsonIgnore
    private Category category;
    @JsonIgnore
    private Type type;
    @JsonIgnore
    private Difficulty difficulty;
    private String question;
    @JsonProperty("correct_answer")
    private String correctAnswer;
    @JsonProperty("incorrect_answers")
    private List<String> incorrectAnswers;
}
