package pl.sda.trivia;

import pl.sda.trivia.api.Difficulty;
import pl.sda.trivia.api.Type;
import pl.sda.trivia.error.DataFormatException;
import pl.sda.trivia.error.DataNotAvailableException;
import pl.sda.trivia.model.Quiz;
import pl.sda.trivia.model.QuizToComplete;
import pl.sda.trivia.repository.QuizRepository;
import pl.sda.trivia.repository.TriviaAPIQuizRepository;
import pl.sda.trivia.service.QuizService;
import pl.sda.trivia.service.TriviaQuizService;

import java.util.*;

public class TriviaAppConsole {
    static private final QuizRepository quizRepo = new TriviaAPIQuizRepository();
    //wstrzyknięcie zależności
    static private final QuizService quizService = new TriviaQuizService(quizRepo);
    static private final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Set<QuizToComplete> quizzes;
        try {
             quizzes = quizService.findQuizSet(
                    5,
                    Difficulty.ANY,
                    Type.MULTIPLE_CHOICE
            );
        } catch (DataNotAvailableException e){
            System.out.println(e.getMessage());
            System.out.println("Aplikacja nie może działać!");
            return;
        } catch (DataFormatException e){
            System.out.println(e.getMessage());
            return;
        }
        List<QuizToComplete> list = new ArrayList<>(quizzes);
        int currentQuiz = 0;
        exit:
        while(true){
            QuizToComplete quizToComplete = list.get(currentQuiz);
            screenTemplate(quizToComplete);
            String userChoice = scanner.nextLine().toUpperCase();
            switch (userChoice){
                case "1":
                    if (quizToComplete.getOptions().size() > 0){
                        quizToComplete.setAnswer(quizToComplete.getOptions().get(0));
                    }
                    break;
                case "2":
                    //warunek
                    quizToComplete.setAnswer(quizToComplete.getOptions().get(1));
                    break;
                case "3":
                    //warunek
                    quizToComplete.setAnswer(quizToComplete.getOptions().get(2));
                    break;
                case "4":
                    //warunek
                    quizToComplete.setAnswer(quizToComplete.getOptions().get(3));
                    break;
                case "N":
                    if (currentQuiz < list.size() - 1) {
                        currentQuiz++;
                    }
                    break;
                case "P":
                    if (currentQuiz > 0) {
                        currentQuiz--;
                    }
                    break;
                case "K":
                    break exit;
            }
        }
        int points = quizService.evaluateQuizSet(new HashSet<>(list));
        System.out.println("Zdobyłeś punktów: " + points);
        System.out.println("Twoje i poprawne odpowiedzi");
        showQuizzesResults(list);
    }

    //Zdefiniuj metodę screenTemplate, aby pobierała dane z quiz
    //Dla ostatniego pytania możliwe przejście tylko do poprzedniego
    //Dla pierwszego pytanie możliwe przejście tylko do następnego
    static void screenTemplate(QuizToComplete quiz){
        System.out.println(quiz.getQuestion());
        System.out.println("-".repeat(quiz.getQuestion().length()));//taka sama szerokość jak długość pytania
        int count = 1;
        for(var option: quiz.getOptions()){
            if (option.equals(quiz.getAnswer())){
                System.out.println(count + ". [X] " + option);
            } else {
                System.out.println(count + ". [ ] " + option);
            }
            count++;
        }
        System.out.println("Menu: odpowiedź: 1, 2, 3 ,4 lub 1, 2; następne pytanie: N; poprzednie: P; koniec: K");
    }

    static void showQuizzesResults(List<QuizToComplete> quizzes){
        for (var quiz: quizzes) {
            System.out.println(quiz.getQuestion());
            System.out.println("-".repeat(quiz.getQuestion().length()));
            int count = 1;
            for (var option : quiz.getOptions()) {
                if (option.equals(quiz.getAnswer()) && option.equals(quiz.getCorrectAnswer())) {
                    System.out.println(count + ". [x] " + option);
                } else
                if (option.equals(quiz.getAnswer())){
                    System.out.println(count + ". [-] " + option);
                } else
                if (option.equals(quiz.getCorrectAnswer())){
                    System.out.println(count + ". [*] " + option);
                }
                else {
                    System.out.println(count + ". [ ] " + option);
                }
                count++;
            }
        }
    }
}
