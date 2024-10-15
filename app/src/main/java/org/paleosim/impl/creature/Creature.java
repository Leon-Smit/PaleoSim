package org.paleosim.impl.creature;

import org.paleosim.simulation.SimObject;
import org.paleosim.util.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Creature implements SimObject {

    Vector2D position;
    Vector2D velocity;
    Vector2D acceleration;
    Vector2D target;

    List<Vector2D> body;
    Color color;
    int size;
    float speedMultiplier;


    public Creature() {
        super();
        position = new Vector2D(320, 240);
        velocity = new Vector2D(0, 0);
        acceleration = new Vector2D(0, 0);
        target = randomTarget();
        body = new ArrayList<>();
        body.add(position);
        int length = 2 + (int) (Math.random() * 10);
        for (int i = 0; i < length; i++) {
            body.add(new Vector2D(position));
        }
        Random random = new Random();
        float hue = random.nextFloat();
        float saturation = (random.nextInt(2000) + 7000) / 10000f;
        float luminance = 0.9f;
        color = Color.getHSBColor(hue, saturation, luminance);

        size = random.nextInt(15) + 15;
        speedMultiplier = 2 - (size - 15) / 15.0f;
    }

    @Override
    public void update(List<SimObject> currentState, double deltaTime) {
        acceleration = target.subtract(position).limit(0.1f * speedMultiplier);
        velocity = velocity.add(acceleration).limit(10 * speedMultiplier);
        position = position.add(velocity.scale((float) (1 / deltaTime)));

        if (position.dist(target) < size) {
            target = randomTarget();
        }

        body.set(0, position);
        for (int i = 1; i < body.size(); i++) {
            Vector2D previous = body.get(i - 1);
            Vector2D current = body.get(i);
            Vector2D difference = previous.subtract(current).limit(size / 2.0f - i);
            body.set(i, previous.subtract(difference));
        }

        currentState.forEach(other -> {
            if (!this.equals(other) && position.dist(other.getPosition()) < (size + other.getSize()) / 2.0f) {
                this.velocity = this.velocity.add(other.getPosition().subtract(position).scale(-0.2f));
            }
        });
    }

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(color);
        graphics.fillOval((int) position.getX() - size / 2, (int) position.getY() - size / 2, size, size);

        for (int i = 1; i < body.size(); i++) {
            Vector2D segment = body.get(i);
            graphics.fillOval((int) segment.getX() - size / 2 + i, (int) segment.getY() - size / 2 + i, size - i * 2, size - i * 2);
        }

        graphics.setColor(Color.white);
        graphics.fillOval((int) target.getX() - 3, (int) target.getY() - 3, 6, 6);
        graphics.setColor(new Color(1, 1, 1, 0.2f));
        graphics.drawLine((int) position.getX(), (int) position.getY(), (int) target.getX(), (int) target.getY());
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public int getSize() {
        return size;
    }

    private Vector2D randomTarget() {
        return new Vector2D((float) (Math.random() * 640), (float) (Math.random() * 480));
    }
}

