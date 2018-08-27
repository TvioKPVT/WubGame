package Wub;

import javafx.scene.control.*;

import java.io.IOException;

public class BattleController {
public Label pstr;
public Label pend;
public Label pcurrhp;
public Label pspecies;
public Label estr;
public Label eend;
public Label ecurrhp;
public Label battletext;
public Label especies;
public Button makeattackbutton;
public Button makeheal;
public Button youwin;
public Button tryagain;
public ProgressBar playerhp;
public ProgressBar enemyhp;

//объявление экземпляра класса врага. Перенес сюда из-за генерации расы. Если в дальнейшем возникнут косяки, потому что объявляется экземпляр не на старте игры, а непосредственно перед боем, то
//перенести его обратно в Game.java, и заделать костыляцию для game.prevloc = "жопа твоей мамки", чтобы по умолчанию была какая-то локация. Костыли-костылики.
public Enemy enemy = new Enemy();

Game game = Main.instance.game;
    //ответочка
    public void enemyTurn() {
        String k = battletext.getText();
        battletext.setText(k + "\nВраг нанес Вам " + Integer.toString(enemy.attack(game.player)) + " единиц урона.");
        playerhp.setProgress(game.player.percentHP());
        pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));
        makehealcheck();

        //проверка на смерть игрока
        if (game.player.isAlive == false) {
            makeattackbutton.setVisible(false);
            makeheal.setVisible(false);
            battletext.setText(k + "\nВы всё.");
            tryagain.setVisible(true);
        }
    }
    //проверка на доступность хила
    public void makehealcheck(){
        if (game.player.CurrHP==game.player.HP){
            makeheal.setDisable(true);
        }
        else {
            makeheal.setDisable(false);
            }
    }

public void initialize(){
    enemy.createCharacter();

    pstr.setText("STR: "+Integer.toString(game.player.STR));
    pend.setText("END: " + Integer.toString(game.player.END));
    pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));
    playerhp.setProgress(game.player.percentHP());
    pspecies.setText(game.player.species);

    enemyhp.setProgress(enemy.percentHP());
    estr.setText("STR: "+Integer.toString(enemy.STR));
    eend.setText("END: " + Integer.toString(enemy.END));
    ecurrhp.setText("HP: " + Integer.toString(enemy.CurrHP));
    especies.setText(enemy.species);

   makehealcheck();

    tryagain.setVisible(false);
    youwin.setVisible(false);

    battletext.setText("Вы видите врага.");

    //кнопка атаки.
    makeattackbutton.setOnAction(e -> {
        String w = battletext.getText();
        battletext.setText(w+"\nВы нанесли "+Integer.toString(game.player.attack(enemy))+ " единиц урона." );
        ecurrhp.setText("HP: " + Integer.toString(enemy.CurrHP));
        enemyhp.setProgress(enemy.percentHP());

        //проверка на смерть врага
        if (enemy.isAlive == false){
            makeattackbutton.setVisible(false);
            makeheal.setVisible(false);
            youwin.setVisible(true);
            battletext.setText(w+"\nВраг сдох!");


            }

        //враг жив, его ход
        else{
            enemyTurn();
        }

    });
    //кнопка хила
    makeheal.setOnAction(e->{
        String w = battletext.getText();
        battletext.setText(w+"\nВы вылечили "+Integer.toString(game.player.heal())+ " единиц здоровья." );
        playerhp.setProgress(game.player.percentHP());
        pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));
        enemyTurn();
    });




    //кнопка выхода из боя при победе
    youwin.setOnAction(e->{
        try {
            Main.instance.switchScene(game.prevloc);
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
