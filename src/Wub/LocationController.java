package Wub;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;

public class LocationController {
    public Label pstr;
    public Label pend;
    public Label pcurrhp;
    public Label loctext;
    public Button enterthebattle;
    public Button gotocave;
    public Button explore;
    public ProgressBar playerhp;

    Game game = Main.instance.game;
    Stage parentWindow = Main.instance.parentWindow;




    public void initialize(){


        pstr.setText("STR: "+Integer.toString(game.player.STR));
        pend.setText("END: " + Integer.toString(game.player.END));
        pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));
        playerhp.setProgress(game.player.percentHP());

        if (game.player.cave== false){
            gotocave.setVisible(false);
        }
        else{
            gotocave.setVisible(true);
        }

        enterthebattle.setVisible(false);


        loctext.setText("Вы в лесу. Тут лесяво.");

        explore.setOnAction(e ->{
            Dice dice = new Dice();
            int a = dice.d10();

            if(a<=3){
                String n = loctext.getText();
                loctext.setText(n+"\nВы наткнулись на врага!");
                explore.setVisible(false);
                enterthebattle.setVisible(true);
            }
            else if (a>=4 && a <7 && game.player.cave==false){
                String n = loctext.getText();
                loctext.setText(n+"\nВы нашли пещеры!");
                gotocave.setVisible(true);
                game.player.cave=true;
            }
            else {
                String n = loctext.getText();
                loctext.setText(n+"\nВы нашли некоторую полезную хрень.");
            }
        });

        enterthebattle.setOnAction(e ->{
            try {
                Main.instance.switchScene("Battle.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } );


    }


    }
