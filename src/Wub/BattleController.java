package Wub;

import javafx.scene.control.*;

import java.io.IOException;

public class BattleController {
public Label pstr;
public Label pend;
public Label pcurrhp;
public Label estr;
public Label eend;
public Label ecurrhp;
public Label battletext;
public Button makeAttackButton;
public Button youwin;
public Button tryagain;
public ProgressBar playerhp;
public ProgressBar enemyhp;

Game game = Main.instance.game;

public void initialize(){
    game.enemy.createCharacter();
    pstr.setText("STR: "+Integer.toString(game.player.STR));
    pend.setText("END: " + Integer.toString(game.player.END));
    pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));
    playerhp.setProgress(game.player.percentHP());
    enemyhp.setProgress(game.enemy.percentHP());

    estr.setText("STR: "+Integer.toString(game.enemy.STR));
    eend.setText("END: " + Integer.toString(game.enemy.END));
    ecurrhp.setText("HP: " + Integer.toString(game.enemy.CurrHP));

    tryagain.setVisible(false);
    youwin.setVisible(false);

    battletext.setText("Вы видите врага.");

    //кнопка атаки.
    makeAttackButton.setOnAction(e -> {
        String w = battletext.getText();

        battletext.setText(w+"\nВы нанесли "+Integer.toString(game.player.attack(game.enemy))+ " единиц урона." );
        ecurrhp.setText("HP: " + Integer.toString(game.enemy.CurrHP));
        enemyhp.setProgress(game.enemy.percentHP());
        //проверка на смерть врага
        if (game.enemy.isAlive == false){
            makeAttackButton.setVisible(false);
            youwin.setVisible(true);
            battletext.setText(w+"\nВраг сдох!");


            }

        //враг жив, его ход
        else{
            String k = battletext.getText();
            battletext.setText(k+"\nВраг нанес Вам "+Integer.toString(game.enemy.attack(game.player))+ " единиц урона.");
            playerhp.setProgress(game.player.percentHP());
            pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));
            //проверка на смерть игрока
            if (game.player.isAlive == false){
                makeAttackButton.setVisible(false);
                battletext.setText(w+"\nВы всё.");
                tryagain.setVisible(true);
            }

        }

    });
    //кнопка выхода из боя при победе
    youwin.setOnAction(e->{
        try {
            Main.instance.switchScene("Location.fxml");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    });
    tryagain.setOnAction(e->{
        try {
            Main.instance.switchScene("StartOfTheGame.fxml");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    });
}
}
