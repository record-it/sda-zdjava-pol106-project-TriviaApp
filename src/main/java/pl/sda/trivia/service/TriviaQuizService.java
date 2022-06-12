package pl.sda.trivia.service;

import pl.sda.trivia.api.Category;
import pl.sda.trivia.api.Difficulty;
import pl.sda.trivia.api.Type;
import pl.sda.trivia.model.QuizToComplete;
import pl.sda.trivia.repository.QuizRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TriviaQuizService implements QuizService {
    private final QuizRepository quizRepository;

    public TriviaQuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Set<QuizToComplete> findQuizSet(int amount, Difficulty difficulty, Type type, Category category) {
        return quizRepository.findQuizByDifficultyAndTypeAndCategory(amount, difficulty, type, category)
                .stream()
                .map(quiz -> {
                            List<String> options = new ArrayList<>(quiz.getIncorrectAnswers());
                            options.add(quiz.getCorrectAnswer());
                            Collections.shuffle(options);
                            return QuizToComplete.builder()
                                    .question(quiz.getQuestion())
                                    .correctAnswer(quiz.getCorrectAnswer())
                                    .options(options)
                                    .build();
                        }
                )
                .collect(Collectors.toSet());
    }

    @Override
    public int evaluateQuizSet(Set<QuizToComplete> quizzes) {
        //TODO: zaimplementuj metodę zliczająca liczbę poprawnych odpowiedzi.
        return (int) quizzes
                .stream()
                .filter(quiz -> quiz.getCorrectAnswer().equals(quiz.getAnswer()))
                .count();

//        int points = 0;
//        for (var quiz: quizzes) {
//            if (quiz.getCorrectAnswer().equals(quiz.getAnswer())){
//                points++;
//            }
//        }
//        return points;
    }
}
