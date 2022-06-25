package pl.sda.trivia.fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.sda.trivia.api.Difficulty;
import pl.sda.trivia.api.Type;
import pl.sda.trivia.entity.QuizComplete;
import pl.sda.trivia.model.QuizToComplete;
import pl.sda.trivia.repository.*;
import pl.sda.trivia.service.QuizService;
import pl.sda.trivia.service.TriviaQuizService;

import java.util.*;

public class TriviaFxApp extends Application {
    private final ToggleGroup group = new ToggleGroup();
    private final VBox root = new VBox();
    private final Scene scene = new Scene(root);
    private final HBox buttons = new HBox();
    private final Button endBtn = new Button("Koniec");
    private QuizRepository quizRepository= new TriviaAPIQuizRepository();
    private final QuizCompleteRepositoryJpa quizCompleteRepository = new QuizCompleteRepositoryJpa();
    private QuizService quizService = new TriviaQuizService(quizRepository, quizCompleteRepository);
    private CategoryRepository categoryRepository = new TriviaCategoryRepository();

    private final QuizCompleteRepositoryJpa quizCompleteRespository = new QuizCompleteRepositoryJpa();
    int quizNumber = 0;
    Label question = new Label("");;
    RadioButton radio1 = new RadioButton("");;
    RadioButton radio2 = new RadioButton("");;
    RadioButton radio3 = new RadioButton("");;
    RadioButton radio4 = new RadioButton("");;
    final Set<QuizToComplete> quizSet = quizService.findQuizSet(5, Difficulty.ANY, Type.MULTIPLE_CHOICE, categoryRepository.findAll().get(0));
    List<QuizToComplete> quizList = new ArrayList<>(quizSet);
    Button prevBtn = new Button("Poprzednie");
    Button nextBtn = new Button("Następne");

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.TOP_CENTER);
        UpdateQuiz();
        radio1.setToggleGroup(group);
        radio2.setToggleGroup(group);
        radio3.setToggleGroup(group);
        radio4.setToggleGroup(group);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(10, 0, 10, 0));
        prevBtn.setOnAction(event -> {
            if(quizNumber > 0){
                setUserAnswer();
                quizNumber--;
                UpdateQuiz();
            }
        });
        nextBtn.setOnAction(event ->{
            if(quizList.size() - 1 > quizNumber){
                setUserAnswer();
                quizNumber++;
                UpdateQuiz();
            }
        });
        endBtn.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wyniki quizu");
            int points = quizService.evaluateQuizSet(new HashSet<>(quizList));
            alert.setContentText("Uzyskałeś punktów: " + points);
            alert.show();
        });
        buttons.getChildren().addAll(prevBtn, endBtn, nextBtn);
        root.getChildren().addAll(question,radio1, radio2, radio3, radio4, buttons);
        stage.setScene(scene);
        stage.setTitle("Trivia App");
        stage.show();
    }

    private void setUserAnswer(){
        final RadioButton selected = (RadioButton) group.getSelectedToggle();
        if (selected != null) {
            quizList.get(quizNumber).setAnswer(selected.getText());
        }
    }

    private void UpdateQuiz(){
        if(quizNumber == 0){
            prevBtn.setDisable(true);
        } else {
            prevBtn.setDisable(false);
        }
        if (quizNumber == quizList.size() - 1){
            nextBtn.setDisable(true);
        } else {
            nextBtn.setDisable(false);
        }
        question.setText(quizList.get(quizNumber).getQuestion());
        final QuizToComplete quiz = quizList.get(quizNumber);
        radio1.setText(quizList.get(quizNumber).getOptions().get(0));
        radio2.setText(quizList.get(quizNumber).getOptions().get(1));
        radio3.setText(quizList.get(quizNumber).getOptions().get(2));
        radio4.setText(quizList.get(quizNumber).getOptions().get(3));
        if (Objects.equals(quiz.getAnswer(),radio1.getText())){
            radio1.setSelected(true);
        } else {
            radio1.setSelected(false);
        }
        if (Objects.equals(quiz.getAnswer(), radio2.getText())){
            radio2.setSelected(true);
        } else {
            radio2.setSelected(false);
        }
        if (Objects.equals(quiz.getAnswer(), radio3.getText())){
            radio3.setSelected(true);
        } else {
            radio3.setSelected(false);
        }
        if (Objects.equals(quiz.getAnswer(),radio4.getText())){
            radio4.setSelected(true);
        } else {
            radio4.setSelected(false);
        }
    }
}
