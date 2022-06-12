package pl.sda.trivia.service;

import pl.sda.trivia.api.Difficulty;
import pl.sda.trivia.api.Type;
import pl.sda.trivia.model.QuizToComplete;
import java.util.Set;

public interface QuizService {
    Set<QuizToComplete> findQuizSet(int amount, Difficulty difficulty, Type type);
    int evaluateQuizSet(Set<QuizToComplete> quizzes);
}
