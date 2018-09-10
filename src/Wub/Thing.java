package Wub;

import java.util.HashMap;
import java.util.Map;

public class Thing {
    //internal use
    Dice dice = new Dice();
    enum type_options { DEFAULT, WEAPON, ARMOR, CONSUMABLE }

    //external use
    //int damage, heal;
    String title = "Thing";
    String description = "";
    int apcost = 0;
    type_options type = type_options.DEFAULT;

    //public thing functions
    public int get_damage(Character character){return 0;}
    public int get_healed(){return 0;}
    public String toString(){return title;}
    public boolean canwear;
    public int dt;


    public Thing(){}
}

class Axe extends Thing{

    Axe(){
        title = "Топор";
        type = type_options.WEAPON;
        apcost = 25;
        canwear = true;
    }
    public int get_damage(Character character){
        return (2 * character.STR) + 5 + dice.d10() + 1;
    }
}

class Skin extends Thing {

    Skin(int DT) {
        title ="Шкура";
        type =type_options.ARMOR;
        dt = DT;
        canwear = true;
}

}

class LeatherArmor extends Thing{

    LeatherArmor(){
        title ="Кожаная броня";
        type = type_options.ARMOR;
        dt = 6;
        canwear = true;
    }
}


class Sword extends Thing{

    Sword(){
        title = "Меч";
        type = type_options.WEAPON;
        apcost = 20;
        canwear = true;
    }

    public int get_damage(Character character){
        return (2 * character.STR) + 10 + dice.d10() + 1;
    }


}

class Jaws extends Thing{

    Jaws(){
        title = "Челюсти";
        type = type_options.WEAPON;
        apcost = 15;
        canwear = true;
    }
    public int get_damage(Character character){
        return (1 * character.STR) + 5 + dice.d10() + 1;
    }

}


class Berry extends Thing {

    Berry(){
        title = "Ягодка";
        type = type_options.CONSUMABLE;
    }

    public int get_healed(){return 200;}
}


class PredefinedItems {
    public static final Map<String, Thing> collection = new HashMap<>();

    static {
        collection.put("sword", new Sword());
        collection.put("berry", new Berry());
        collection.put("jaws",new Jaws());
        collection.put("axe", new Axe());
        collection.put("skin", new Skin(1));
        collection.put("rough skin", new Skin(5));
        collection.put("leather armor", new LeatherArmor());
    }
}
