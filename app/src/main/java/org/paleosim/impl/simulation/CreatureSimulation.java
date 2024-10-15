package org.paleosim.impl.simulation;

import org.paleosim.impl.creature.Creature;
import org.paleosim.simulation.SimObject;
import org.paleosim.simulation.Simulation;

import java.util.List;

public class CreatureSimulation extends Simulation {

    public CreatureSimulation() {
        super();
        for (int i = 0; i < 20; i++) {
            this.simObjects.add(new Creature());
        }
    }

    @Override
    public void update(long deltaTime) {
        this.simObjects.forEach(simObject -> simObject.update(this.simObjects, deltaTime));
    }

    @Override
    public List<SimObject> getState() {
        return this.simObjects;
    }

}
