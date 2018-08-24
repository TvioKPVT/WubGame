package Wub;

import javafx.scene.control.*;

public class BattleController {
public Label pstr;
public Label pend;
public Label pcurrhp;
public Label estr;
public Label eend;
public Label ecurrhp;
public Label battletext;
public Button makeAttackButton;

Game game = Main.instance.game;

public void initialize(){
    pstr.setText("STR: "+Integer.toString(game.player.STR));
    pend.setText("END: " + Integer.toString(game.player.END));
    pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));

    estr.setText("STR: "+Integer.toString(game.enemy.STR));
    eend.setText("END: " + Integer.toString(game.enemy.END));
    ecurrhp.setText("HP: " + Integer.toString(game.enemy.CurrHP));

    battletext.setText("Вы видите врага.");

    makeAttackButton.setOnAction(e -> {
        String w = battletext.getText();
        battletext.setText(w+"\nВы нанесли "+Integer.toString(game.player.attack(game.enemy))+ " единиц урона." );
        ecurrhp.setText("HP: " + Integer.toString(game.enemy.CurrHP));
        if (game.enemy.isAlive == false){
            battletext.setText(w+"\nВраг сдох.");
        }
        else{
            String k = battletext.getText();
            battletext.setText(k+"\nВраг нанес Вам "+Integer.toString(game.enemy.attack(game.player))+ " единиц урона.");
            pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));
            if (game.player.isAlive == false){
                battletext.setText(w+"\nВы всё.");
            }
        }

    });

}
}
