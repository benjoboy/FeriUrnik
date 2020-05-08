package com.skupina1.urnik.Map;


public class Point {
    float x, y;

    Point() {
        x = y = 0;
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    final void set(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
