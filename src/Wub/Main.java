package Wub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {
    static Dice dice = new Dice();
    static Player player = new Player();
    static Enemy enemy = new Enemy();

//Dice

    public static class Dice {
        public int d310;
        Random random = new Random();

        public int d310(){
            int n = 0;
            while (n<=2){
                n = random.nextInt(10)+1;
            }

            return n;
        }
    }










    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        enemy.createCharacter();
        player.createCharacter();

        launch(args);
    }
}
