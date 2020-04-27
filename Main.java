package com.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{



    public static void main(String[] args)  {



        Game game = new Game();
        game.main();
        do {

            game.rules();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while(game.dead() != 1);



    }
}
