package Wub;

import Wub.Main;
import javafx.scene.control.*;

public class Controller {
public Label pstr;
public Label pend;
public Label pcurrhp;
public Label estr;
public Label eend;
public Label ecurrhp;


public void initialize(){
    pstr.setText("STR: "+Integer.toString(Main.game.player.STR));
    pend.setText("END: " + Integer.toString(Main.game.player.END));
    pcurrhp.setText("HP: " + Integer.toString(Main.game.player.CurrHP));

    estr.setText("STR: "+Integer.toString(Main.game.enemy.STR));
    eend.setText("END: " + Integer.toString(Main.game.enemy.END));
    ecurrhp.setText("HP: " + Integer.toString(Main.game.enemy.CurrHP));

}
}
