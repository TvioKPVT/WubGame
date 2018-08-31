package Wub;

import javafx.scene.control.*;
import javafx.scene.control.ListView;


import java.io.IOException;

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
    public Button back;
    public Button use;
    public ListView<Thing> inventoryListView = new ListView<Thing>();

//инициализация списка инвентаря. На случай, если пронадобится.
public void invinitialize(){

    for (int i = 0; i<game.player.inventory.size();i++){
        inventoryListView.getItems().add(game.player.inventory.get(i));
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
        playerhp.setProgress(game.player.percentHP());
        playerhp.setStyle("-fx-accent: #991111");

        inventoryListView.getItems().removeAll();
//перекидывание всего инвентаря в просмотрщик
        invinitialize();

        back.setOnAction(event ->{

                inventoryListView.getItems().removeAll();

            try {
                Main.instance.switchScene("Location.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } );

        use.setOnAction(event -> {
           int n = inventoryListView.getFocusModel().getFocusedIndex();

           Thing current_thing = game.player.inventory.get(n);
           //проверка на тип штуки, которую пытаешься использовать
            if(current_thing.type.equals(Thing.type_options.CONSUMABLE)) {

                   //проверка на остаток ХП. Если полное - не лезет еда, ничего не сделаешь.
                   if (game.player.CurrHP < game.player.HP) {

                       game.player.CurrHP += current_thing.get_healed();
                       if (game.player.CurrHP > game.player.HP){game.player.CurrHP = game.player.HP;}
                       game.player.inventory.remove(n);

                       try {
                           Main.instance.switchScene("Inventory.fxml");
                       } catch (IOException e1) {
                           e1.printStackTrace();
                       }


                   }

            }
        });

    }
}
