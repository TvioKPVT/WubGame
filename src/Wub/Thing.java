package Wub;

import java.util.HashMap;
import java.util.Map;

public class Thing {
    //internal use
    Dice dice = new Dice();
    enum type_options { DEFAULT, WEAPON, CONSUMABLE }

    //external use
    //int damage, heal;
    String title = "Thing";
    String description = "";
    type_options type = type_options.DEFAULT;

    //public thing functions
    public int get_damage(Character character){return 0;}
    public int get_healed(){return 0;}
    public String toString(){return title;}

    public Thing(){}
}


class Sword extends Thing{

    Sword(){
        title = "Меч";
        type = type_options.WEAPON;
    }

    public int get_damage(Character character){
        return (2 * character.STR) + 10 + dice.d10() + 1;
    }


}


class Berry extends Thing {

    Berry(){
        title = "Ягодка";
        type = type_options.CONSUMABLE;
    }

    public int get_healed(){return 20;}
}


class PredefinedItems {
    public static final Map<String, Thing> collection = new HashMap<>();

    static {
        collection.put("sword", new Sword());
        collection.put("berry", new Berry());
    }
}
