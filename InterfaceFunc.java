package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Color.black;
import static java.awt.Color.white;

public class InterfaceFunc extends JFrame {
    Color oldColor = null;


    public boolean pauseOn = false;

    int x = 8;
    int y = 8;
    public JButton matrizes [][] = new JButton[x][y];



    public void game (int m[][]) {
        int i, j, k , l;
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(8, 8));
        this.setResizable(false);
        for (i = 0, k = 1; i < x && k <= x + 1; i++, k++) {
            for (j = 0, l = 1; j < x && l <= x + 1; j++, l++) {

                matrizes[i][j] = new JButton();
                matrizes[i][j].setPreferredSize(new Dimension(10, 10));
                getContentPane().add(matrizes[i][j]);
                matrizes[i][j].setVisible(true);
                JButton btn = matrizes[i][j];
                JButton finalBtn = btn;
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent Me) {
                        oldColor = finalBtn.getBackground();
                        if (oldColor == white) {
                            finalBtn.setBackground(black);
                        }
                        else {
                            finalBtn.setBackground(white);
                        }
                    }
                    public void mouseReleased(MouseEvent Me) {
                        oldColor = finalBtn.getBackground();
                        if (oldColor == white) {
                            finalBtn.setBackground(black);
                        }
                        else {
                            finalBtn.setBackground(white);
                        }
                    }

                });

                matrizes[i][j] = btn;

                if (matrizes[i][j].getBackground() == white) {
                    m[k][l] = 0;

                }
                else if (matrizes[i][j].getBackground() == black) {
                    m[k][l] = 1;

                }


                    if (m[k][l] == 0) {

                        matrizes[i][j].setBackground(white);

                    }
                    else {
                        matrizes[i][j].setBackground(black);
                    }




            }
        }
        setSize(500,500);
        setVisible(true);



    }



}
