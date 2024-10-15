package org.paleosim.render;

import org.paleosim.simulation.Simulation;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer {

    private final Simulation simulation;
    private final Canvas canvas;

    public Renderer(Simulation simulation, Canvas canvas) {
        this.simulation = simulation;
        this.canvas = canvas;
    }

    public void render() {
        BufferStrategy bs = canvas.getBufferStrategy();
        if (bs == null) {
            canvas.createBufferStrategy(2);
            return;
        }

        Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(33, 39, 56));
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        simulation.getState().forEach(simObject -> simObject.render(g2d));

        g2d.dispose();
        bs.show();
    }

}
