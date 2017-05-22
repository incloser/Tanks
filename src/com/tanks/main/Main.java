package com.tanks.main;

import com.tanks.display.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class Main {

    public static void main(String[] args) {

        Display.create(800, 600, "SuperTanks", 0xff00ff00);

        Timer t = new Timer(1000/60, new AbstractAction() {

            public void actionPerformed(ActionEvent ae) {
                Display.clear();
                Display.render();
                Display.swapBuffers();
            }
        });
        // чтобы повторял
        t.setRepeats(true);
        t.start();
    }
}
