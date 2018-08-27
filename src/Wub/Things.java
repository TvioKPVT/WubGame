package Wub;

public class Things {
    //Game game = Main.instance.game;
    Dice dice = new Dice();
    int damage;
    String description;
    String title = "Thing";


    public String toString(){return title;}
}

class Weapon extends Things{
    public int get_damage(Character character){return 0;}
}
class Sword extends Weapon{

    Sword(){
        title = "Меч";
    }

    public int get_damage(Character character){
        damage = (2 * character.STR) +10+dice.d10()+1;
        return damage;
    }


}
