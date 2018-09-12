package Wub;

import javafx.scene.control.Button;

import java.io.IOException;

public class StartOfTheGameController {
    Game game = Main.instance.game;
    public Button start;

    public void initialize() throws IOException {
        game.player.createCharacter();
        start.setText(TextVar.LocationsText.start_button);

        start.setOnAction(e->{
            try {
                Main.instance.switchScene("Location.fxml");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });


    }
}
