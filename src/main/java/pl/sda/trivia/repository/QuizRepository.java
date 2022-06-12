package pl.sda.trivia.repository;

import pl.sda.trivia.api.Difficulty;
import pl.sda.trivia.api.Type;
import pl.sda.trivia.model.Quiz;

import java.util.List;

public interface QuizRepository {
    List<Quiz> findQuizByDifficultyAndType(int count, Difficulty difficulty, Type type);
}
