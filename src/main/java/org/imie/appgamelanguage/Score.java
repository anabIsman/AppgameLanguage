package org.imie.appgamelanguage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Score extends Application {
    private String scorePlayer = "";
    private Label lblInfoScore;
    public Score() {}
    public Score(String scorePlayer) {
        this.scorePlayer = scorePlayer;
    }
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Score.class.getResource("score.fxml"));
        GridPane root = null;
        try {
            root = fxmlLoader.load();
        } catch (Exception e) {
            System.out.println("Erreur : "+e);
        }
        lblInfoScore = (Label) root.lookup("#lblInfoScore");
        lblInfoScore.setText("Vous avez obtenu " + this.scorePlayer) ;
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Score!");
        stage.setScene(scene);
        stage.show();
    }
}