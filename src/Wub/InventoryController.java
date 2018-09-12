package Wub;

import javafx.scene.control.*;
import javafx.scene.control.ListView;


import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;

public class InventoryController {
    Game game = Main.instance.game;

    public Label pstr;
    public Label pend;
    public Label pagi;
    public Label pcurrhp;
    public Label pexp;
    public Label plvl;
    public ProgressBar playerhp;
    public Label pap;
    public Label weapon;
    public Label armorlabel;
    public Label description;
    public Label weapon_l;
    public Label armor_l;
    public Label money;
    public Button back;
    public Button use;
    public ListView<GUIThing> inventoryListView = new ListView<GUIThing>();



//инициализация списка инвентаря. На случай, если пронадобится.
    public void invinitialize(){
    ArrayList<GUIThing> templist = new ArrayList<GUIThing>();

    for (int i = 0; i<game.player.inventory.size();i++){
        Thing current_thing = game.player.inventory.get(i);
        int existed_index = -1;
        for (int j = 0; j<templist.size(); j++){
            if (templist.get(j).thing.equals(current_thing)){
                existed_index = j;
                break;
            }
        }

        if (existed_index != -1){
            templist.get(existed_index).counter += 1;
        }
        else {
            GUIThing current_guithing = new GUIThing(current_thing, i);
            templist.add(current_guithing);
        }
    }

    for (int i = 0; i<templist.size();i++){
        inventoryListView.getItems().add(templist.get(i));
    }



}
public void reboot() {
    try {
        Main.instance.switchScene("Inventory.fxml");
    } catch (IOException e1) {
        e1.printStackTrace();
    }
}
    public void initialize(){

        pexp.setText(String.valueOf(game.player.EXP)+" EXP");
        plvl.setText(String.valueOf(game.player.LVL)+ "LVL");
        weapon.setText(String.valueOf(game.player.weapon));
        pap.setText(String.valueOf((game.player.AP))+ " AP");
        pstr.setText("STR: "+Integer.toString(game.player.STR));
        pend.setText("END: " + Integer.toString(game.player.END));
        pagi.setText("AGI: "+Integer.toString(game.player.AGI));
        pcurrhp.setText("HP: " + Integer.toString(game.player.CurrHP));
        armorlabel.setText(String.valueOf(game.player.armor));
        description.setText("");
        playerhp.setProgress(game.player.percentHP());
        playerhp.setStyle("-fx-accent: #991111");
        back.setText(TextVar.InventoryText.back_button);
        use.setText(TextVar.InventoryText.inv_use_button);
        weapon_l.setText(TextVar.InventoryText.weapon);
        armor_l.setText(TextVar.InventoryText.armor);
        money.setText(MessageFormat.format(TextVar.InventoryText.money_label,game.player.money));

        inventoryListView.getItems().removeAll();





//перекидывание всего инвентаря в просмотрщик
        invinitialize();



        inventoryListView.setOnMouseClicked(e->{
            GUIThing n = inventoryListView.getFocusModel().getFocusedItem();


            Thing current_thing = game.player.inventory.get(n.index);
            description.setText(String.valueOf(current_thing.get_description(game.player)));

            if(current_thing.type.equals(Thing.type_options.CONSUMABLE)){
                use.setText(TextVar.InventoryText.inv_use_button);


            }
           else if(current_thing.type.equals(Thing.type_options.WEAPON )){
                use.setText(TextVar.InventoryText.inv_use_button_1);

            }

            else if (current_thing.type.equals(Thing.type_options.ARMOR)){
                use.setText(TextVar.InventoryText.inv_use_button_1);

            }

        });

        back.setOnAction(event ->{


                inventoryListView.getItems().removeAll();

            try {
                Main.instance.switchScene("Location.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } );

        use.setOnAction(event -> {
           GUIThing n = inventoryListView.getFocusModel().getFocusedItem();
           Thing current_thing = game.player.inventory.get(n.index);
           //проверка на тип штуки, которую пытаешься использовать
            if(current_thing.type.equals(Thing.type_options.CONSUMABLE)) {

                   //проверка на остаток ХП. Если полное - не лезет еда, ничего не сделаешь.
                   if (game.player.CurrHP < game.player.HP) {

                       game.player.CurrHP += current_thing.get_healed();
                       if (game.player.CurrHP > game.player.HP){game.player.CurrHP = game.player.HP;}
                       game.player.inventory.remove(n.index);

                      reboot();


                   }

            }

            else if (current_thing.type.equals(Thing.type_options.WEAPON)){
                game.player.inventory.add(game.player.weapon_equipped);
                game.player.weapon = current_thing;
                game.player.inventory.remove(current_thing);
                game.player.weapon_equipped = current_thing;

                reboot();
            }

            else if (current_thing.type.equals(Thing.type_options.ARMOR)){
                game.player.inventory.add(game.player.armor_equipped);
                game.player.armor = current_thing;
                game.player.inventory.remove(current_thing);
                game.player.armor_equipped = current_thing;
                reboot();
            }
        });

    }
}
