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
    //создание персонажей
    public void createCharacter(){
        STR = dice.d310();
        END = dice.d310();
        HP = 100+(END*3);
        CurrHP = HP;
        isAlive=true;
        cave = false;

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
        int n = this.STR+dice.d10()+1;
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
        if (game.prevloc=="Caves.fxml"){
            n = "Bat";
        }
        else if(game.prevloc == "Location.fxml") {
            n = "Wolf";
        }


        return n;
    }
}
//Методы, доступные игроку.
class Player extends Character{
    String species = "humanoid";


}
//Методы, доступны врагам.
class Enemy extends Character {

   String species = specieschoose();


}
