package org.paleosim.simulation;

import org.paleosim.util.Vector2D;

import java.awt.*;
import java.util.List;

public interface SimObject {

    void update(List<SimObject> currentState, double deltaTime);

    void render(Graphics2D graphics);

    Vector2D getPosition();

    int getSize();

}
