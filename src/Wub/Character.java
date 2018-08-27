package Wub;


//Характеристики персонажей в игре.
public class Character {
    Game game = Main.instance.game;
    public Weapon weapon = new Weapon();
    int STR;
    int END;
    int HP;
    int CurrHP;
    boolean isAlive=true;
    boolean cave = false;
    boolean hasaweapon = false;
    String species=null;
    String currloc = "forest";

    private Dice dice = new Dice();
    //создание персонажей
    public void createCharacter(){
        if (this.species=="Humanoid Player"){
        STR = dice.d310()+5;
        END = dice.d310()+5;
        HP = 100+(END*3);
        CurrHP = HP;
        isAlive=true;
        cave = false;

        }
        else if (this.species =="Wolf"){
            STR = dice.d310()+7;
            END = dice.d310()+3;
            HP = 100+(END*3);
            CurrHP = HP;
            isAlive=true;

        } else if (this.species == "Bat") {

            STR = dice.d310()+2;
            END = dice.d310()+2;
            HP = 100+(END*3);
            CurrHP = HP;
            isAlive=true;
        }

    }

    //высчет текущего процента хп для прогрессбара
     public double percentHP(){
        double n=this.HP/100.0;
        double c=this.CurrHP/n;
        double a = c/100;
        return a;

     }
    //атака голыми руками.
    public int attack(Character target) {
        int n = 0;
        if (this.hasaweapon == true){

            n = weapon.Sword(this);

        }
        else{
        n = this.STR+dice.d10()+1;

        }
        target.CurrHP -= n;
        if (target.CurrHP <= 0) target.isAlive = false;
        return n;

    }

    public int heal(){
        int n = (dice.d310()*2);
        if (this.CurrHP+n<=this.HP) {
            this.CurrHP = this.CurrHP + n;
        }
        else{
            n = this.HP-this.CurrHP;
            this.CurrHP = this.HP;
        }

        return n;
    }

    public String specieschoose(){
        Game game = Main.instance.game;

        String n = null;
        if (game.player.currloc=="caves"){
            n = "Bat";
        }
        else if(game.player.currloc == "forest") {
            n = "Wolf";
        }


        return n;
    }
}
//Методы, доступные игроку.
class Player extends Character{

    Player(){
        species  = "Humanoid Player";
        hasaweapon = true;
    }

}
//Методы, доступны врагам.
class Enemy extends Character {
    Enemy() {
        species = specieschoose();
    }

}
