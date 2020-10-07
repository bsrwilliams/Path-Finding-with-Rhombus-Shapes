package com.bradleywilliams;

import java.util.ArrayList;
import java.util.List;

public class Rhombus {
    private final Vertex upperLeft;
    private final Vertex upperRight;
    private final Vertex lowerLeft;
    private final Vertex lowerRight;
    private final List<Vertex> vertices;

    public Rhombus(int a, int b, int c, int d, int e, int f, int g, int h) {
        this.lowerLeft = new Vertex(a, b);
        this.lowerRight = new Vertex(c, d);
        this.upperRight = new Vertex(e, f);
        this.upperLeft = new Vertex(g, h);
        this.vertices = new ArrayList<>();
    }

    public Vertex getUpperLeft() {
        return upperLeft;
    }

    public Vertex getUpperRight() {
        return upperRight;
    }

    public Vertex getLowerLeft() {
        return lowerLeft;
    }

    public Vertex getLowerRight() {
        return lowerRight;
    }

    public List<Vertex> getShape() {
        this.vertices.add(lowerLeft);
        this.vertices.add(lowerRight);
        this.vertices.add(upperRight);
        this.vertices.add(upperLeft);
        return this.vertices;
    }
}
