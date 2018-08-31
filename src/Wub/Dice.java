package Wub;


import java.util.Random;

public class Dice {
    Random random = new Random();

    public int d310(){
        int n = 0;
        while (n<=2){
            n = random.nextInt(10)+1;
        }

        return n;
    }

    public int d10(){
        int n = random.nextInt(10);
        return n;
    }

    public int d100(){
        int n = random.nextInt(100)+1;
        return n;
    }
}
//l