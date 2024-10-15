package org.paleosim;

import org.paleosim.gui.Window;
import org.paleosim.impl.simulation.CreatureSimulation;
import org.paleosim.render.Renderer;
import org.paleosim.simulation.SimLoop;
import org.paleosim.simulation.Simulation;

public class App {
    public static void main(String[] args) {
        Simulation simulation = new CreatureSimulation();
        Window window = new Window(640, 480);
        Renderer renderer = new Renderer(simulation, window.getCanvas());
        SimLoop loop = new SimLoop(simulation, renderer);

        new Thread(loop).start();
    }
}
