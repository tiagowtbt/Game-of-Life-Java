package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JFrame implements Runnable{

    public int dead( ){
        int dead = 0;
        int count = 0;

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {

                if(m[i][j] == 1) {
                    count++;

                }


            }
        }

        if (count == 0) {
            dead = 1;
        }
        dispose();
        return dead;

    }


    public int tickCount = 0;
    Random r = new Random();
    int x = 10, y = 10;

    int m[][] = {   {2, 2, 2, 3, 4, 5, 6, 7, 8, 2},
                    {2, 0, 0, 0, 0, 0, 0, 0, 0, 2},
                    {2, 0, 0, 0, 1, 0, 0, 0, 0, 2},
                    {3, 0, 0, 0, 1, 0, 0, 0, 0, 3},
                    {4, 0, 0, 0, 1, 0, 0, 0, 0, 4},
                    {5, 0, 0, 0, 0, 0, 0, 0, 0, 5},
                    {6, 0, 0, 0, 0, 0, 0, 0, 0, 6},
                    {7, 0, 0, 0, 0, 0, 0, 0, 0, 7},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {2, 2, 2, 3, 4, 5, 6, 7, 8, 2}};

    int nm[][] = new int[x][y];





    public void rules() {



        this.getContentPane().setLayout(new GridLayout(8, 8));

        InterfaceFunc If = new InterfaceFunc();
        int count;

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {

                nm[i][j] = m[i][j];


            }
        }


        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {

                count = 0;

                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {



                        if (l == i && k == j) {
                            m[i][j] = m[i][j];
                        }

                        if (m[k][l] == 1) {
                            count++;
                        }


                    }
                }

                //Para os casos na qual a celula analisada se encontra viva, é adicionado +1, uma vez que o contador conta a celula viva.
                if (m[i][j] == 1 && count >= 3 && count <= 4) { //Qualquer célula viva com dois ou três vizinhos vivos continua no mesmo estado para a próxima geração.
                    nm[i][j] = 1;
                }
                if (m[i][j] == 1 && count > 4) { //Qualquer célula viva com mais de três vizinhos vivos morre de superpopulação.
                    nm[i][j] = 0;
                }
                if (m[i][j] == 1 && count < 3) { //Qualquer célula viva com menos de dois vizinhos vivos morre de solidão.
                    nm[i][j] = 0;
                }
                if (m[i][j] == 0 && count == 3) { //Qualquer célula morta com exatamente três vizinhos vivos se torna uma célula viva.
                    nm[i][j] = 1;
                }
                if (m[i][j] == 0 && count > 3) { //Celulas mortas.
                    nm[i][j] = 0;
                }
                if (m[i][j] == 0 && count < 3) { //Celulas mortas.
                    nm[i][j] = 0;
                }


            }
        }



        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {

                m[i][j] = nm[i][j];


            }
        }
        If.game(m);

    }

    public boolean running = false;
    public synchronized void start() {
        running = true;
        new Thread(this).start();

    }
    public synchronized void stop() {
        running = false;
        new Thread(this).stop();

    }

    public void run () {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D/60D;
        int frames = 0;
        int ticks = 0;
        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime)/nsPerTick;
            lastTime = now;
            boolean renderizar = false;

            while(delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                renderizar = true;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(renderizar) {
                frames++;
                render();
            }
            if(System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;

            }


        }
    }

    public void tick() {
        tickCount++;
    }
    public void render() {
        rules();

    }

   public void main( ) {
       InterfaceFunc If = new InterfaceFunc();
        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {

                m[i][j] = r.nextInt(2);

            }
        }

       If.game(m);
       try {
           Thread.sleep(1000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }


   }

}