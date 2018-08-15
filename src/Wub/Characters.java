package Wub;


import static Wub.Main.dice;
import static Wub.Main.enemy;
import static Wub.Main.player;


//Характеристики персонажей в игре.
public  class Characters {
    int STR;
    int END;
    int HP;
    int CurrHP;
    boolean isAlive=true;

    public void createCharacter(){
        STR = dice.d310;
        END = dice.d310;
        HP = (STR+END)*2;
        CurrHP = HP;

    }
}
//Методы, доступные игроку.
 class Player extends Characters{
    public int attack() {
        int n = this.STR*2;
        enemy.CurrHP -= n;
        if (enemy.CurrHP <= 0) enemy.isAlive = false;
        return n;
    }
}
//Методы, доступны врагам.
class Enemy extends Characters {

    public int attack() {
        int n = this.STR*2;
        player.CurrHP -= n;
        if (player.CurrHP <= 0) player.isAlive = false;
        return n;
    }

}
