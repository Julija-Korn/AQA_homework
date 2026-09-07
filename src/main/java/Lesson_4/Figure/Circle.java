package Lesson_4.Figure;

public class Circle implements GeometricFigure {
    private String name;
    private double radius;
    private String fillColor;
    private String borderColor;

    public Circle(String name, double radius, String fillColor, String borderColor) {
        this.name = name;
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public String getName() {
        return name; }

    @Override
    public double getArea () {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter () {
        return 2 * Math.PI * radius;
    }
    @Override
    public String getFillColor() {
        return fillColor;
    }

    @Override
    public String getBorderColor() {
        return borderColor;
    }
}
