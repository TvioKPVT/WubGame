package Wub;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.text.MessageFormat;

public class BattleController {
public Label pstr;
public Label pend;
public Label pcurrhp;
public Label pcap;
public Label pspecies;
public Label pexp;
public Label estr;
public Label eend;
public Label ecap;
public Label ecurrhp;
public Text battletext = new Text("");
public Label especies;
public Label eweapon;
public TextFlow battlescreen;

public Button makeattackbutton;
public Button youwin;
public Button tryagain;

public ProgressBar playerhp;
public ProgressBar enemyhp;



Dice dice = new Dice();

//объявление экземпляра класса врага. Перенес сюда из-за генерации расы. Если в дальнейшем возникнут косяки, потому что объявляется экземпляр не на старте игры, а непосредственно перед боем, то
//перенести его обратно в Game.java, и заделать костыляцию для game.prevloc = "жопа твоей мамки", чтобы по умолчанию была какая-то локация. Костыли-костылики.
public Enemy enemy = new Enemy();

Game game = Main.instance.game;

    public void Formattext(){
        battletext = new Text("");
    }



    //ответочка
    public void enemyTurn() {
        while (enemy.CurrAP>=enemy.weapon.apcost){
            if (dice.d100()-20<enemy.ChanceToHit) { //временно увеличил шанс на попадание. Ввести скиллы и убрать
                Formattext();

                battletext = new Text(MessageFormat.format(TextVar.Battle.enemy_deal_damage,Integer.toString(enemy.attack(game.player))));
                battlescreen.getChildren().add(battletext);

                enemy.CurrAP = enemy.CurrAP - enemy.weapon.apcost;
                playerhp.setProgress(game.player.percentHP());
                pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));



                //проверка на смерть игрока
                if (game.player.isAlive == false) {

                    Formattext();
                    battletext = new Text(TextVar.Battle.you_died);
                    battlescreen.getChildren().add(battletext);
                    tryagain.setVisible(true);
                    makeattackbutton.setVisible(false);

                }
            }
            else{
                Formattext();
                battletext = new Text(TextVar.Battle.enemy_missed);
                battlescreen.getChildren().add(battletext);
                enemy.CurrAP = enemy.CurrAP - enemy.weapon.apcost;

            }
        }
        enemy.CurrAP = enemy.AP;
        apcheck();

    }
    //проверка на доступность хила


    public void rollforlut(){


        Dice dice = new Dice();
        int n = dice.d10();//заготовка для ролла лута, когда его будет больше, чем просто одна ягодка.
        Thing berry = PredefinedItems.collection.get("berry");
        game.player.money+=n;
        game.player.inventory.add(berry);
        Formattext();
        battletext = new Text(MessageFormat.format(TextVar.Battle.loot,berry,n) );
        battlescreen.getChildren().add(battletext);

    }

    public void apcheck(){
        Formattext();
        if (game.player.CurrAP<game.player.weapon.apcost) {
            makeattackbutton.setVisible(false);
            game.player.CurrAP = game.player.AP;
            enemyTurn();
        }
        else if (game.player.CurrHP<1){     // проверка на несдохшесть, иначе при смерти можно продолжать игру до посинения
            makeattackbutton.setVisible(false);
        }
        else makeattackbutton.setVisible(true);

    }




public void initialize(){


    enemy.createCharacter();
    enemy.lvlroll(game.player);
    apcheck();

    pexp.setText(String.valueOf(game.player.EXP)+ " EXP");
    pstr.setText("STR: "+Integer.toString(game.player.STR));
    pend.setText("END: " + Integer.toString(game.player.END));
    pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));
    pcap.setText("AP: " + Integer.toString(game.player.CurrAP));
    playerhp.setProgress(game.player.percentHP());
    pspecies.setText("Человек "+game.player.LVL+" LVL");//если будет много игровых рас, то надо будет тут поменять, чтобы отображало корректную расу

    enemyhp.setProgress(enemy.percentHP());
    estr.setText("STR: "+Integer.toString(enemy.STR));
    eend.setText("END: " + Integer.toString(enemy.END));
    ecap.setText("AP: " + Integer.toString(enemy.CurrAP));
    ecurrhp.setText("HP: " + Integer.toString(enemy.CurrHP));
    especies.setText(enemy.species+" "+enemy.LVL+" LVL");
    playerhp.setStyle("-fx-accent: #991111");
    enemyhp.setStyle("-fx-accent: #991111");
    eweapon.setText(String.valueOf(enemy.weapon+ "\n" +enemy.armor));

    makeattackbutton.setText(TextVar.Battle.attack_button);
    tryagain.setText(TextVar.Battle.try_again_button);
    youwin.setText(TextVar.Battle.victory_button);





    tryagain.setVisible(false);
    youwin.setVisible(false);


    Formattext();

    battletext = new Text(TextVar.Battle.enemy_detected);
    battlescreen.getChildren().add(battletext);

    //кнопка атаки.
    makeattackbutton.setOnAction(e -> {
        if (dice.d100()-20<game.player.ChanceToHit){  //временно увеличил шанс на попадание. Ввести скиллы и убрать

            Formattext();
            battletext= new Text(MessageFormat.format(TextVar.Battle.player_deal_damage,Integer.toString(game.player.attack(enemy))) );
            battlescreen.getChildren().add(battletext);
            ecurrhp.setText("HP: " + Integer.toString(enemy.CurrHP));
            pcap.setText(String.valueOf("AP: " + Integer.toString(game.player.CurrAP)));
            enemyhp.setProgress(enemy.percentHP());

            apcheck();

                //проверка на смерть врага
                if (enemy.isAlive == false) {
                    makeattackbutton.setVisible(false);

                    youwin.setVisible(true);
                    Formattext();
                    battletext = new Text(TextVar.Battle.enemy_died);
                    battlescreen.getChildren().add(battletext);
                    game.player.EXP += enemy.EXPforkill;
                    rollforlut();
                }

        }
        else {
            Formattext();
            battletext=new Text(TextVar.Battle.player_missed );
            battlescreen.getChildren().add(battletext);
            game.player.CurrAP= game.player.CurrAP - game.player.weapon.apcost;
            pcap.setText(String.valueOf("AP: " + Integer.toString(game.player.CurrAP)));
            apcheck();
        }



    });






    //кнопка выхода из боя при победе
    youwin.setOnAction(e->{
        game.player.lvlupcheck();
        game.player.CurrAP = game.player.AP;
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
