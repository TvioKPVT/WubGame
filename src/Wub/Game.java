package Wub;

public class Game {
    static Dice dice = new Dice();
    static Player player = new Player();
    static Enemy enemy = new Enemy();

     public void startGame(){
         enemy.createCharacter();
         player.createCharacter();

     }
}
