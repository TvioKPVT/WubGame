package Wub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main extends Application {
    public Game game;
    public static Main instance;
    public Stage parentWindow = new Stage();


    public Main() {
        instance = this;
        game = new Game();
        game.startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        parentWindow = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("StartOfTheGame.fxml"));
        Scene scene = new Scene(root);

        //parentWindow.initStyle(StageStyle.UNDECORATED);  //убрать кнопки управления окном.
        parentWindow.setTitle("Заебись игра");
        parentWindow.setScene(scene);
        parentWindow.show();

    }

    public void switchScene(String filename) throws IOException {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(filename));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Scene scene = new Scene(root);
        parentWindow.setScene(scene);

    }
}
