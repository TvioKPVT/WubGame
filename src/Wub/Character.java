package Wub;


//Характеристики персонажей в игре.
public class Character {
    int STR;
    int END;
    int HP;
    int CurrHP;
    boolean isAlive=true;

    private Dice dice = new Dice();

    public void createCharacter(){
        STR = dice.d310();
        END = dice.d310();
        HP = (STR+END)*2;
        CurrHP = HP;

    }

    public int attack(Character target) {
        int n = this.STR*2;
        target.CurrHP -= n;
        if (target.CurrHP <= 0) target.isAlive = false;
        return n;
    }
}
//Методы, доступные игроку.
class Player extends Character{

}
//Методы, доступны врагам.
class Enemy extends Character {

}
