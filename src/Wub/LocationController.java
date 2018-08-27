package Wub;


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
    public Label pspecies;
    public Button enterthebattle;
    public Button gotocave;
    public Button explore;
    public Button inventory;
    public ProgressBar playerhp;




    Game game = Main.instance.game;
    Stage parentWindow = Main.instance.parentWindow;

//переключение сцены на пещеры
    public void gotocaves(){
        loctext.setText("Вы в пещере. Тут мокро и воняет.");
        game.player.currloc = "caves";
        gotocave.setText("Вернуться в лес");

        //кнопка, отвечающая за исследования на локации Пещеры
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

        gotocave.setOnAction(e->{
            gotoforest();

    });
    }
//переключение сцены на лес. Так же участвует в инициализации стартовой локации.
    public void gotoforest(){
        loctext.setText("Вы в лесу. Тут лесяво.");
        gotocave.setOnAction(e->gotocaves());

        if (game.player.cave== false){
            gotocave.setVisible(false);
        }
        else{
            gotocave.setVisible(true);
        }

        enterthebattle.setVisible(false);
        gotocave.setText("В пещеры");

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

    }

//инициализация уровня на старте.
    public void initialize(){
        playerhp.setStyle("-fx-accent: #991111");
        gotoforest();


        pstr.setText("STR: "+Integer.toString(game.player.STR));
        pend.setText("END: " + Integer.toString(game.player.END));
        pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));
        playerhp.setProgress(game.player.percentHP());
        pspecies.setText(game.player.species);


        enterthebattle.setOnAction(e ->{

            try {
                Main.instance.switchScene("Battle.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } );
        gotocave.setOnAction(e->gotocaves());

        inventory.setOnAction(e->{
            try {
                Main.instance.switchScene("Inventory.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } );



    }

}

