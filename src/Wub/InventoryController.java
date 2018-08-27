package Wub;

import javafx.scene.control.*;
import javafx.scene.control.ListView;


import java.io.IOException;

public class InventoryController {
    Game game = Main.instance.game;

    public Button back;
    public ListView<Things> inventoryListWiev = new ListView<Things>();



    public void initialize(){
//перекидывание всего инвентаря в просмотрщик
        for (int i = 0; i<game.player.inventory.size();i++){
            inventoryListWiev.getItems().add(game.player.inventory.get(i));
        }

        back.setOnAction(event ->{
            //for (int i = 0; i<inventoryListWiev.getFixedCellSize())
            try {
                Main.instance.switchScene("Location.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } );


    }
}
