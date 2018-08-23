package Wub;


import java.util.Random;

public class Dice {
    public int d310;
    Random random = new Random();

    public int d310(){
        int n = 0;
        while (n<=2){
            n = random.nextInt(10)+1;
        }

        return n;
    }
}