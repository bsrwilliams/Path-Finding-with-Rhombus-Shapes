package com.bradleywilliams;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class JourneyPlanner {
    private List<Rhombus> shapes = this.createShapes();
    private static JourneyPlanner planner = new JourneyPlanner();

    public static void main(String args[]) {
        for (int i = 1; i <= 12; i++) {
            switch (i) {
                case 1: planner.output(new Vertex(15, 1), new Vertex(14,21), i);
                    break;
                case 2: planner.output(new Vertex(14, 21), new Vertex(14,3), i);
                    break;
                case 3: planner.output(new Vertex(14, 3), new Vertex(7,23), i);
                    break;
                case 4: planner.output(new Vertex(13, 0), new Vertex(19,19), i);
                    break;
                case 5: planner.output(new Vertex(12, 3), new Vertex(18,16), i);
                    break;
                case 6: planner.output(new Vertex(11, 21), new Vertex(14,8), i);
                    break;
                case 7: planner.output(new Vertex(11, 3), new Vertex(17,16), i);
                    break;
                case 8: planner.output(new Vertex(8, 5), new Vertex(8,15), i);
                    break;
                case 9: planner.output(new Vertex(7, 23), new Vertex(9,18), i);
                    break;
                case 10: planner.output(new Vertex(17, 20), new Vertex(10,3), i);
                    break;
                case 11: planner.output(new Vertex(15, 0), new Vertex(11,22), i);
                    break;
                case 12: planner.output(new Vertex(11, 22), new Vertex(8,8), i);
                    break;
            }
        }
    }

    public List<Vertex> nextConfigs(Vertex state) {
        // Holds all the available vertices (edges)
        List<Vertex> availableVertices = new ArrayList<Vertex>();
        // Looping through all shapes one at a time
        for (int i = 0; i < this.shapes.size(); i++) {
            Rhombus testRhombus = this.shapes.get(i);
            // Choosing a vertex in the current shape to draw a line to, from 'state'
            for (int j = 0; j <= 3; j++) {
                boolean intersects = false;
                // Get each vertex inside a shape
                Vertex currentVertex = testRhombus.getShape().get(j);
                for (Rhombus testing : this.shapes) {
                    // Does the line created from state to currentVertex cross the line Upper Left -> Upper right?
                    if (Vertex.linesIntersect(state, currentVertex, testing.getUpperLeft(), testing.getUpperRight())) {
                        intersects = true;
                        // Does the line created from state to currentVertex cross the line Upper Left -> Lower Left?
                    } else if (Vertex.linesIntersect(state, currentVertex, testing.getUpperLeft(), testing.getLowerLeft())) {
                        intersects = true;
                        // Does the line created from state to currentVertex cross the line Lower Left -> Lower Right?
                    } else if (Vertex.linesIntersect(state, currentVertex, testing.getLowerLeft(), testing.getLowerRight())) {
                        intersects = true;
                        // Does the line created from state to currentVertex cross the line Upper Right -> Lower Right?
                    } else if (Vertex.linesIntersect(state, currentVertex, testing.getUpperRight(), testing.getLowerRight())) {
                        intersects = true;
                        // Does the line created from state to currentVertex cross through the current shape?
                    } else if (Vertex.linesIntersect(state, currentVertex, testing.getUpperLeft(), testing.getLowerRight())) {
                        intersects = true;
                        // Does the line created from state to currentVertex cross through the current shape?
                    } else if (Vertex.linesIntersect(state, currentVertex, testing.getUpperRight(), testing.getLowerLeft())) {
                        intersects = true;
                    }
                }
                // If the line drawn from parameter 'state' to currentVertex doesnt cross any other line pair on any rhombus add to available vertices
                if (!intersects) {
                    availableVertices.add(currentVertex);
                }
            }
        }
        return availableVertices;
    }


    public LinkedList<Vertex> iterativeDeepening(Vertex first, Vertex last) {
        for (int depth = 1; true; depth++) {
            LinkedList<Vertex> route = depthFirst(first, last, depth);
            if (route != null) return route;
        }
    }

    private LinkedList<Vertex> depthFirst(Vertex first, Vertex last, int depth) {
        if (depth == 0) {
            return null;
        } else if (first.equals(last)) {
            LinkedList<Vertex> route = new LinkedList<Vertex>();
            route.add(first);
            return route;
        } else {
            List<Vertex> nexts = nextConfigs(first);
            for (Vertex next : nexts) {
                LinkedList<Vertex> route = depthFirst(next, last, depth - 1);
                if (route != null) {
                    route.addFirst(first);
                    return route;
                }
            }
            return null;
        }
    }

    public List<Rhombus> createShapes() {
        ArrayList<Rhombus> rhombuses = new ArrayList<Rhombus>();
        Rhombus one = new Rhombus(17, 12, 21, 12, 17, 16, 13, 16);
        rhombuses.add(one);
        Rhombus two = new Rhombus(6, 21, 11, 21, 11, 22, 6, 22);
        rhombuses.add(two);
        Rhombus three = new Rhombus(1, 1, 2, 1, 2, 7, 1, 7);
        rhombuses.add(three);
        Rhombus four = new Rhombus(17, 19, 19, 19, 19, 20, 17, 20);
        rhombuses.add(four);
        Rhombus five = new Rhombus(5, 17, 7, 17, 5, 20, 3, 20);
        rhombuses.add(five);
        Rhombus six = new Rhombus(8, 7, 12, 7, 14, 11, 10, 11);
        rhombuses.add(six);
        Rhombus seven = new Rhombus(6, 18, 9, 18, 7, 23, 4, 23);
        rhombuses.add(seven);
        Rhombus eight = new Rhombus(11, 3, 12, 3, 12, 5, 11, 5);
        rhombuses.add(eight);
        Rhombus nine = new Rhombus(13, 0, 15, 0, 15, 1, 13, 1);
        rhombuses.add(nine);
        Rhombus ten = new Rhombus(14, 16, 18, 16, 14, 21, 10, 21);
        rhombuses.add(ten);
        Rhombus eleven = new Rhombus(6, 8, 14, 8, 17, 10, 9, 10);
        rhombuses.add(eleven);
        Rhombus twelve = new Rhombus(7, 5, 8, 5, 8, 8, 7, 8);
        rhombuses.add(twelve);
        Rhombus thirteen = new Rhombus(10, 3, 14, 3, 11, 5, 7, 5);
        rhombuses.add(thirteen);
        Rhombus fourteen = new Rhombus(16, 16, 19, 16, 16, 20, 13, 20);
        rhombuses.add(fourteen);
        Rhombus fifteen = new Rhombus(15, 2, 20, 2, 21, 7, 16, 7);
        rhombuses.add(fifteen);
        Rhombus sixteen = new Rhombus(7, 14, 9, 14, 10, 15, 8, 15);
        rhombuses.add(sixteen);
        return rhombuses;
    }

    public void output(Vertex start, Vertex finish, int index) {
        LinkedList<Vertex> route = planner.iterativeDeepening(start, finish);
        try {
            File fileN = new File(index + ".txt");
            PrintWriter writer = new PrintWriter(fileN);
            for (int i = 0; i < route.size(); i++) {
                writer.print(route.get(i) + " ");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}