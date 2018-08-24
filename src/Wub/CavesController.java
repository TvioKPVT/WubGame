package Wub;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;

public class CavesController {
    public Label pstr;
    public Label pend;
    public Label pcurrhp;
    public Label loctext;
    public Button enterthebattle;
    public Button explore;
    public ProgressBar playerhp;

    Game game = Main.instance.game;
    Stage parentWindow = Main.instance.parentWindow;




    public void initialize(){


        pstr.setText("STR: "+Integer.toString(game.player.STR));
        pend.setText("END: " + Integer.toString(game.player.END));
        pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));
        playerhp.setProgress(game.player.percentHP());



        enterthebattle.setVisible(false);


        loctext.setText("Вы в пещере. Тут мокро и воняет.");

        explore.setOnAction(e ->{
            Dice dice = new Dice();
            int a = dice.d10();

            if(a<=3){
                String n = loctext.getText();
                loctext.setText(n+"\nВы наткнулись на врага!");
                explore.setVisible(false);
                enterthebattle.setVisible(true);

            }
            else {
                String n = loctext.getText();
                loctext.setText(n+"\nВы нашли некоторую полезную хрень.");
            }
        });

        enterthebattle.setOnAction(e ->{
            game.prevloc = "Caves.fxml";
            try {
                Main.instance.switchScene("Battle.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } );


    }


}
