package Wub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;





public class Main extends Application {
    public Game game;
    public static Main instance;

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

        Parent root = FXMLLoader.load(getClass().getResource("Battle.fxml"));
        primaryStage.setTitle("Заебись игра");
        primaryStage.setScene(new Scene(root,640, 480));
        primaryStage.show();



    }
}
