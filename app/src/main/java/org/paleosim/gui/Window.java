package org.paleosim.gui;

import javax.swing.*;
import java.awt.*;

public class Window {

    private final Canvas canvas;

    public Window(int width, int height) {
        JFrame frame = new JFrame("PaleoSim");
        canvas = new Canvas();

        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public Canvas getCanvas() {
        return canvas;
    }

}
