package Wub;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Характеристики персонажей в игре.
public class Character {
    //Game game = Main.instance.game;
    public Thing weapon = null;
    public Thing armor = null;
    int STR;
    int END;
    int AGI;
    int HP;
    int CurrHP;
    int CurrAP;
    int AP;
    int EXP;
    int EXPforkill;
    int LVL;
    int ChanceToHit;
    boolean isAlive = true;
    boolean cave = false;
    boolean city = false;
    boolean city_trader = false;
    int money;
    Thing sword = PredefinedItems.collection.get("sword");
    Thing berry = PredefinedItems.collection.get("berry");
    Thing axe = PredefinedItems.collection.get("axe");
    Thing jaws = PredefinedItems.collection.get("jaws");
    Thing skin = PredefinedItems.collection.get("skin");
    Thing roughskin = PredefinedItems.collection.get("rough skin");
    Thing leatherarmor = PredefinedItems.collection.get("leather armor");

    String species = null;
    String currloc = "forest";
    List<Thing> inventory = new ArrayList<>();

    Thing weapon_equipped = new Thing();
    Thing armor_equipped = new Thing();


    private Dice dice = new Dice();

    //создание персонажей
    public void createCharacter() {
        if (this.species == "Humanoid Player") {
            STR = dice.d310() + 5;
            END = dice.d310() + 5;
            AGI = dice.d310() + 5;
            AP = 55 + (AGI * 3);
            HP = 100 + (END * 3);
            ChanceToHit = 2 * STR;
            CurrHP = HP;
            CurrAP = AP;
            EXP = 0;
            LVL = 1;
            isAlive = true;
            cave = false;
            city = false;
            city_trader = false;
            inventory = new ArrayList<>();
            inventory.add(axe);
            inventory.add(skin);
            weapon = sword;
            armor = leatherarmor;
            inventory.add(berry);
            money = 0;

            weapon_equipped = sword;
            armor_equipped =leatherarmor;



        } else if (this.species == "Wolf") {
            STR = dice.d310() + 7;
            END = dice.d310() + 3;
            AGI = dice.d310() + 5;
            AP = 55 + (AGI * 3);
            HP = 100 + (END * 3);
            CurrHP = HP;
            CurrAP = AP;
            ChanceToHit = 2 * STR;
            isAlive = true;
            EXPforkill = 100;
            LVL = 1;

            weapon = jaws;
            armor = roughskin;


        } else if (this.species == "Bat") {

            STR = dice.d310() + 2;
            END = dice.d310() + 2;
            AGI = dice.d310() + 5;
            AP = 55 + (AGI * 3);
            HP = 100 + (END * 3);
            CurrHP = HP;
            CurrAP = AP;
            ChanceToHit = 2 * STR;
            isAlive = true;
            EXPforkill = 75;
            LVL = 1;


            weapon = jaws;
            armor = skin;

        } else if (this.species == "Humanoid") {
            STR = dice.d310() + 5;
            END = dice.d310() + 5;
            AGI = dice.d310() + 5;
            AP = 55 + (AGI * 3);
            HP = 100 + (END * 3);
            CurrHP = HP;
            CurrAP = AP;
            ChanceToHit = 2 * STR;
            isAlive = true;
            cave = false;
            EXPforkill = 200;
            LVL = 1;
            rollforweapon();
            rollforarmor();


        }
    }

    public void lvlroll(Character player) {//фунцкия, чекающая ЛВЛ игрока для спавна врага соответствущего ЛВЛ
        int n = player.LVL;
        if (n > 1) {
            Random random = new Random();
            int a = random.nextInt(2) - 1;
            n += a;
            for (int i = 0; i <= n; i++) {
                this.lvlrise();
            }
        }
        this.LVL = n;

    }

    public void lvlupcheck() {
        if (this.EXP >= (this.LVL * 500)) {
            this.STR += 3;
            this.END += 3;
            this.HP += 2 * this.END;
            this.LVL++;
            this.CurrHP += 2 * this.END;
        }
    }

    public void lvlrise() {
        this.STR += 3;
        this.END += 3;
        this.HP += 2 * this.END;
        this.LVL++;
        this.CurrHP += 2 * this.END;
    }

    //высчет текущего процента хп для прогрессбара
    public double percentHP() {
        double n = this.HP / 100.0;
        double c = this.CurrHP / n;
        double a = c / 100;
        return a;

    }

    //атака голыми руками.
    public int attack(Character target) {
        int n = 0;
        if (this.weapon != null) {

            n = weapon.get_damage(this) - target.armor.dt;

        } else {
            n = this.STR + dice.d10() + 1 - target.armor.dt;

        }
        target.CurrHP -= n;
        if (target.CurrHP <= 0) target.isAlive = false;
        this.CurrAP = this.CurrAP - this.weapon.apcost;

        return n;

    }

    public int heal() {
        int n = (dice.d310() * 2);
        if (this.CurrHP + n <= this.HP) {
            this.CurrHP = this.CurrHP + n;
        } else {
            n = this.HP - this.CurrHP;
            this.CurrHP = this.HP;
        }

        return n;
    }

    public String specieschoose() {
        Game game = Main.instance.game;

        String n = null;
        if (game.player.currloc == "caves") {
            n = "Bat";
        } else if (game.player.currloc == "forest") {
            int a = dice.d10();
            if (a > 5) {
                n = "Wolf";
            } else n = "Humanoid";
        }


        return n;
    }

    public void rollforweapon() {
        int n = dice.d10();

        if (n < 5) {

            this.weapon = sword;
        } else {

            this.weapon = axe;
        }
    }

    public void rollforarmor() {
        int n = dice.d10();

        if (n<5){

            this.armor = leatherarmor;
        }

        else {
            this.armor = skin;
        }

    }
}
//Методы, доступные игроку.
class Player extends Character{

    Player(){
        species  = "Humanoid Player";
    }

}
//Методы, доступны врагам.
class Enemy extends Character {
    Enemy() {
        species = specieschoose();
    }

}
