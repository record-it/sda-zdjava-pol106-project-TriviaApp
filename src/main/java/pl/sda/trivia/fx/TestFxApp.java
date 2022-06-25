package pl.sda.trivia.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static java.awt.Color.GREEN;

public class TestFxApp extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Rectangle rectangle = new Rectangle(100,100, 100, 200);
        rectangle.setFill(Color.GREEN);
        rectangle.setStroke(Color.BROWN);
        rectangle.setStrokeWidth(2);
        Circle circle = new Circle(200,200, 100);
        Circle circle1 = new Circle(200,200, 50);
        Arc arc = new Arc();
        arc.setCenterX(300);
        arc.setCenterY(300);
        arc.setRadiusX(50);
        arc.setRadiusY(50);
        arc.setStartAngle(90);
        arc.setLength(90);
        arc.setStrokeWidth(4);
        circle1.setFill(Color.GREEN);
        //dodanie do root'a
        Text text = new Text("Hello Java Fx");
        text.setX(10);
        text.setY(30);
        final Font lato = Font.font("Lato", 36);
        text.setFont(lato);
        text.setFill(Color.DARKGREEN);
        Line line = new Line(10,10, 480,380);
        line.setStroke(Color.DARKBLUE);
        line.setStrokeWidth(20);
        line.setStrokeLineCap(StrokeLineCap.ROUND);
        Path path = new Path();
        path.getElements().addAll(
                new MoveTo(400, 0),
                new LineTo(300, 100),
                new LineTo(300, 200),
                new LineTo(400, 0)
        );
        path.setStroke(Color.BROWN);
        path.setStrokeWidth(2);
        root.getChildren().addAll(rectangle, circle, circle1, text, arc, line, path);
        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Test JavaFx");
        stage.setScene(scene);
        stage.show();
    }
}
