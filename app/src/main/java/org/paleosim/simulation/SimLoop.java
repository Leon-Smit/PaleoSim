package org.paleosim.simulation;

import org.paleosim.render.Renderer;

import static java.lang.Thread.sleep;

public class SimLoop implements Runnable {


    private final Simulation simulation;
    private final Renderer renderer;

    public SimLoop(Simulation s, Renderer r) {
        this.simulation = s;
        this.renderer = r;
    }

    boolean running = true;
    final int FPS = 60;
    final long frameTime = 1000 / FPS;

    @Override
    public void run() {
        long lastUpdateTime = System.currentTimeMillis();
        int currentFps = 0;
        long lastFPSTime = System.currentTimeMillis();

        while (running) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - lastUpdateTime;

            if (currentTime - lastFPSTime > 1000) {
                System.out.println(currentFps);
                currentFps = 0;
                lastFPSTime += 1000;
            }

            if (elapsedTime >= frameTime) {
                lastUpdateTime = System.currentTimeMillis();
                update(elapsedTime);
                render();
                currentFps++;
            }

            try {
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void update(long deltaTime) {
        simulation.update(deltaTime);
    }

    void render() {
        renderer.render();
    }

}
