package Wub;

import javafx.scene.control.*;
import javafx.scene.control.ListView;


import java.io.IOException;

public class InventoryController {
    Game game = Main.instance.game;

    public Button back;
    public Button use;
    public ListView<Things> inventoryListView = new ListView<Things>();



    public void initialize(){
//перекидывание всего инвентаря в просмотрщик
        for (int i = 0; i<game.player.inventory.size();i++){
            inventoryListView.getItems().add(game.player.inventory.get(i));
        }

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
           if(game.player.inventory.get(n).getClass().toString().equals("Wub.Berry")){

            }
        });

    }
}
