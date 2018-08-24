package Wub;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LocationController {
    public Label pstr;
    public Label pend;
    public Label pcurrhp;
    public Label loctext;
    public Button enterthebattle;
    public Button gotocave;

    Game game = Main.instance.game;
    Stage parentWindow = Main.instance.parentWindow;




    public void initialize(){


        pstr.setText("STR: "+Integer.toString(game.player.STR));
        pend.setText("END: " + Integer.toString(game.player.END));
        pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));
        gotocave.setVisible(false);


        loctext.setText("Вы в лесу. Тут лесяво. Перед вами стоит враг.");

        enterthebattle.setOnAction(e ->{
            try {
                Main.instance.switchScene("Battle.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } );


    }


    }
