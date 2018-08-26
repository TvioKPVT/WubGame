package Wub;

public class Weapon extends Character {
    Game game = Main.instance.game;
    private Dice dice = new Dice();
    int damage;
    String description;

    public void Sword(){
        damage = (2 * this.STR)+10+dice.d10()+1;
    }

}

