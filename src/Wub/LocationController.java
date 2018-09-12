package Wub;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.MessageFormat;

public class LocationController {
    public Label pstr;
    public Label pend;
    public Label pagi;
    public Label pcurrhp;
    public Label loctext;
    public Label pspecies;

    public Button gotocave;
    public Button explore;
    public Button inventory;
    public Button gotocity;
    public Button gotoshop;
    public ProgressBar playerhp;




    Game game = Main.instance.game;
    Stage parentWindow = Main.instance.parentWindow;

    public void exploring(){
        Dice dice = new Dice();
        int a = dice.d10();
        if (game.player.currloc == "forest") {
            if (a <= 3) {

                try {
                    Main.instance.switchScene("Battle.fxml");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else if (a >= 6 && a <= 7 && game.player.cave == false) {
                String n = loctext.getText();
                loctext.setText(n + TextVar.LocationsText.obtaining_caves);
                gotocave.setVisible(true);
                game.player.cave = true;
            } else if (a >= 4 && a <= 5 && game.player.city == false) {
                String n = loctext.getText();
                loctext.setText(n + TextVar.LocationsText.obtaining_city);
                gotocity.setVisible(true);
                game.player.city = true;
            } else {
                rollforlut();
            }
        }
        else if (game.player.currloc == "city"){

            if (a <= 3 && game.player.city_trader == false) {
                String n = loctext.getText();
                game.player.city_trader = true;
                loctext.setText(n + TextVar.LocationsText.obtaining_shop);
                gotoshop.setVisible(true);

            } else {
                String n = loctext.getText();

                loctext.setText(n + TextVar.LocationsText.nothing);

            }

        }

    }

    public void rollforlut(){
       // Dice dice = new Dice();
        //int n = dice.d10();
        //if (n <2){
            String w = loctext.getText();
            Thing berry = PredefinedItems.collection.get("berry");
            game.player.inventory.add(berry);
            loctext.setText(w+ MessageFormat.format ( TextVar.LocationsText.berry_gained, berry) );
        //}
    }

    //переключение сцены на пещеры
    public void gotocaves(){
        loctext.setText(TextVar.LocationsText.cave_on_enter);
        game.player.currloc = "caves";
        gotocave.setText(TextVar.LocationsText.gotoforest_button);

        //кнопка, отвечающая за исследования на локации Пещеры
        explore.setOnAction(e ->{
            Dice dice = new Dice();
            int a = dice.d10();

            if(a<=3){
                try {
                    Main.instance.switchScene("Battle.fxml");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            else {
                rollforlut();
            }


        });

        gotocave.setOnAction(e->{
            gotoforest();

    });
    }
//переключение сцены на лес. Так же участвует в инициализации стартовой локации.
    public void gotoforest(){
        loctext.setText(TextVar.LocationsText.forest_on_enter);
        game.player.currloc = "forest";
        gotocave.setOnAction(e->gotocaves());
        gotocity.setOnAction(e-> gotocity());

        if (game.player.cave== false){
            gotocave.setVisible(false);
        }
        else{
            gotocave.setVisible(true);
        }

        if (game.player.city== false){
            gotocity.setVisible(false);
        }
        else{
            gotocity.setVisible(true);
        }

        if (game.player.city_trader == false){
            gotoshop.setVisible(false);
        }
        else {
            gotoshop.setVisible(true);
        }


        gotocave.setText(TextVar.LocationsText.gotocave_button);
        gotocity.setText(TextVar.LocationsText.gotocity_button);

        explore.setOnAction(e ->{
            exploring();
                    });

    }

    public void gotocity() {
        loctext.setText(TextVar.LocationsText.city_on_enter);
        game.player.currloc = "city";
        gotocity.setText("Вернуться в лес");

        if (game.player.city_trader == false){
            gotoshop.setVisible(false);
        }

        else {
            gotoshop.setVisible(true);
        }

        gotocave.setVisible(false);

        gotocity.setOnAction(e -> {
            gotoforest();
        });
    }

//инициализация уровня на старте.
    public void initialize(){


        //Проверка на локацию при выходе из инвентаря.
        if (game.player.currloc == "city"){
            gotocity();
        }
        else if (game.player.currloc == "caves"){
            gotocaves();
        }
        else {
            gotoforest();
        }




        pstr.setText("STR: "+Integer.toString(game.player.STR));
        pend.setText("END: " + Integer.toString(game.player.END));
        pagi.setText("AGI: "+Integer.toString(game.player.AGI));
        pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));
        playerhp.setProgress(game.player.percentHP());
        playerhp.setStyle("-fx-accent: #991111");
        pspecies.setText(game.player.species);
        gotocave.setText(TextVar.LocationsText.gotocave_button);
        explore.setText(TextVar.LocationsText.explore_button);
        inventory.setText(TextVar.LocationsText.inv_button);
        gotoshop.setText(TextVar.LocationsText.gotoshop_button);




        gotocave.setOnAction(e-> gotocaves());
        gotocity.setOnAction(e-> gotocity());

        inventory.setOnAction(e->{
            try {
                Main.instance.switchScene("Inventory.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } );

        gotoshop.setOnAction(e ->{
            try {
                Main.instance.switchScene("Shop.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });



    }

}

