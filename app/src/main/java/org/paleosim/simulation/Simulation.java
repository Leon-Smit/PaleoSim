package org.paleosim.simulation;

import java.util.ArrayList;
import java.util.List;

public abstract class Simulation {

    protected List<SimObject> simObjects;

    public Simulation() {
        simObjects = new ArrayList<>();
    }

    public abstract void update(long deltaTime);

    public abstract List<SimObject> getState();

}
