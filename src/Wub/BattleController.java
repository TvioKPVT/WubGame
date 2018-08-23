package Wub;

import Wub.Main;
import Wub.Battle;
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

    makeAttackButton.setOnAction(e -> {game.player.attack();
    ecurrhp.setText("HP: " + Integer.toString(game.enemy.CurrHP));
    if (game.enemy.isAlive == false){
        battletext.setText("Враг сдох.");
    }
    });

}
}
