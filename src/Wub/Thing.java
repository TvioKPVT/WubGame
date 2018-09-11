package Wub;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class Thing {
    Game game = Main.instance.game;
    //internal use
    Dice dice = new Dice();
    enum type_options { DEFAULT, WEAPON, ARMOR, CONSUMABLE }

    //external use
    //int damage, heal;
    String title = "Thing";
    int apcost = 0;
    type_options type = type_options.DEFAULT;

    //public thing functions
    public int get_damage(Character character){return 0;}
    public String get_description (Character character){return "";}
    public int get_healed(){return 0;}
    public String toString(){return title;}
    public boolean canwear;
    public int dt;


    public Thing(){}
}

class Axe extends Thing{

    Axe(){
        title = TextVar.Weapon.weapon_name_1;

        type = type_options.WEAPON;
        apcost = 25;
        canwear = true;
    }
    public int get_damage(Character character){
        return (2 * character.STR) + 5 + dice.d10() + 1;
    }
    public String get_description (Character character){return MessageFormat.format ( TextVar.Weapon.weapon_description_1, get_damage(character));}
}

class Skin extends Thing {

    Skin(int DT) {
        title = TextVar.Armor.armor_name_1;
        type =type_options.ARMOR;
        dt = DT;
        canwear = true;
}

}

class LeatherArmor extends Thing{

    LeatherArmor(){
        title =TextVar.Armor.armor_name_2;
        type = type_options.ARMOR;
        dt = 6;
        canwear = true;

    }
    public String get_description (Character character){return TextVar.Armor.armor_description_2;}
}


class Sword extends Thing{

    Sword(){
        title = TextVar.Weapon.weapon_name_2;
        type = type_options.WEAPON;
        apcost = 20;
        canwear = true;
    }

    public int get_damage(Character character){
        return (2 * character.STR) + 10 + dice.d10() + 1;
    }
    public String get_description (Character character){return MessageFormat.format ( TextVar.Weapon.weapon_description_2, get_damage(character));}


}

class Jaws extends Thing{

    Jaws(){
        title = TextVar.Weapon.weapon_name_3;
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
        title = TextVar.Consumnables.con_name_1;
        type = type_options.CONSUMABLE;
    }

    public int get_healed(){return 200;}
    public String get_description (Character character){return TextVar.Consumnables.con_description_1;}
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

