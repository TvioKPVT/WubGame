package Wub;

public class Weapon  {
    //Game game = Main.instance.game;
    private Dice dice = new Dice();
    int damage;
    String description;

    public int Sword(Character character){
        damage = (2 * character.STR) +10+dice.d10()+1;
        return damage;
    }

}

