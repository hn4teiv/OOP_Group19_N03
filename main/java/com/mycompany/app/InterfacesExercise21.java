package com.mycompany.app;

interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a Circle");
    }

    // Lớp lồng nhau
    class Radius {
        private double radius;

        Radius(double radius) {
            this.radius = radius;
        }

        double getRadius() {
            return radius;
        }
    }
}

public class InterfacesExercise21 {
    public static void main(String[] args) {
        Shape circle = new Circle();
        circle.draw();

        Circle.Radius radius = new Circle().new Radius(5.0);
        System.out.println("Radius of the Circle: " + radius.getRadius());
    }
}
