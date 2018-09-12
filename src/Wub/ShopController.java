package Wub;

import java.io.IOException;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ShopController {
    ArrayList<Thing> shopinventory = new ArrayList<>();
    Dice dice = new Dice();

    public Button back = new Button();
    public Button buy = new Button();
    public ListView<GUIThing> shopinventoryListView = new ListView<GUIThing>();


    Game game = Main.instance.game;

    public void Getshopperinventory(){
    Object [] wub = PredefinedItems.collection.keySet().toArray();




    for (int n = dice.d10(); n>0; n--){
        Object key =  wub[new Random().nextInt(wub.length)];
        shopinventory.add(PredefinedItems.collection.get(key));
    }
    }
    public void invinitialize(){
        ArrayList<GUIThing> templist = new ArrayList<GUIThing>();

        for (int i = 0; i<shopinventory.size();i++){
            Thing current_thing = shopinventory.get(i);
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
            shopinventoryListView.getItems().add(templist.get(i));
        }



    }

    public void initialize(){
        back.setText(TextVar.InventoryText.back_button);
        buy.setText(TextVar.InventoryText.buy_button);

        Getshopperinventory();
        invinitialize();

        buy.setOnAction(e->{



        });


        back.setOnAction(event ->{




            try {
                Main.instance.switchScene("Location.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } );
    }
}
