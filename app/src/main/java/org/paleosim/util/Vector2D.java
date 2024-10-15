package org.paleosim.util;

public class Vector2D {

    private final float x;
    private final float y;

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D vector) {
        this.x = vector.getX();
        this.y = vector.getY();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(x + other.getX(), y + other.getY());
    }

    public Vector2D scale(float scalar) {
        return new Vector2D(x * scalar, y * scalar);
    }

    public Vector2D limit(float limit) {
        if (getMagnitude() <= 0) {
            return this;
        }
        return this.setMagnitude(Math.min(getMagnitude(), limit));
    }

    public float getMagnitude() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vector2D setMagnitude(float magnitude) {
        float factor = magnitude / getMagnitude();
        return new Vector2D(x * factor, y * factor);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.getX(), this.y - other.getY());
    }

    public float dist(Vector2D other) {
        float deltaX = this.x - other.getX();
        float deltaY = this.y - other.getY();
        return new Vector2D(deltaX, deltaY).getMagnitude();
    }
}
