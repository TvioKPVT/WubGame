package Wub;


//Характеристики персонажей в игре.
public class Character {
    int STR;
    int END;
    int HP;
    int CurrHP;
    boolean isAlive=true;
    boolean cave = false;

    private Dice dice = new Dice();

    public void createCharacter(){
        STR = dice.d310();
        END = dice.d310();
        HP = (STR+END)*2;
        CurrHP = HP;
        isAlive=true;
        cave = false;

    }
     public double percentHP(){
        double n=this.HP/100.0;
        double c=this.CurrHP/n;
        double a = c/100;
        return a;

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
