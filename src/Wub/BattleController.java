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

Game game = Main.instance.game;

public void initialize(){
    game.enemy.createCharacter();
    pstr.setText("STR: "+Integer.toString(game.player.STR));
    pend.setText("END: " + Integer.toString(game.player.END));
    pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));

    estr.setText("STR: "+Integer.toString(game.enemy.STR));
    eend.setText("END: " + Integer.toString(game.enemy.END));
    ecurrhp.setText("HP: " + Integer.toString(game.enemy.CurrHP));

    youwin.setVisible(false);

    battletext.setText("Вы видите врага.");

    //кнопка атаки.
    makeAttackButton.setOnAction(e -> {
        String w = battletext.getText();
        battletext.setText(w+"\nВы нанесли "+Integer.toString(game.player.attack(game.enemy))+ " единиц урона." );
        ecurrhp.setText("HP: " + Integer.toString(game.enemy.CurrHP));
        if (game.enemy.isAlive == false){
            makeAttackButton.setVisible(false);
            youwin.setVisible(true);
            battletext.setText(w+"\nВраг сдох!");


            }


        else{
            String k = battletext.getText();
            battletext.setText(k+"\nВраг нанес Вам "+Integer.toString(game.enemy.attack(game.player))+ " единиц урона.");
            pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));
            if (game.player.isAlive == false){
                makeAttackButton.setVisible(false);
                battletext.setText(w+"\nВы всё.");
            }

        }

    });
    //кнопка выхода из боя
    youwin.setOnAction(e->{
        String n = battletext.getText();
        battletext.setText(n + "\nВы полутали труп");
        try {
            Main.instance.switchScene("Location.fxml");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    });

}
}
