package Wub;

import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.IOException;
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
        playerhp.setProgress(game.player.percentHP());
        playerhp.setStyle("-fx-accent: #991111");

        inventoryListView.getItems().removeAll();





//перекидывание всего инвентаря в просмотрщик
        invinitialize();



        inventoryListView.setOnMouseClicked(e->{
            GUIThing n = inventoryListView.getFocusModel().getFocusedItem();

            Thing current_thing = game.player.inventory.get(n.index);

            if(current_thing.type.equals(Thing.type_options.CONSUMABLE)){
                use.setText("Использовать");


            }
           else if(current_thing.type.equals(Thing.type_options.WEAPON )){
                use.setText("Экипировать");

            }

            else if (current_thing.type.equals(Thing.type_options.ARMOR)){
                use.setText("Экипировать");

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

                game.player.weapon = current_thing;

                reboot();
            }

            else if (current_thing.type.equals(Thing.type_options.ARMOR)){

                game.player.armor = current_thing;

                reboot();
            }
        });

    }
}
