package Lesson_4.Figure;

public class Triangle implements GeometricFigure {
    private double sizeA, sizeB, sizeC;
    private String fillColor;
    private String borderColor;
    private String name;

    public Triangle(double sizeA, double sizeB, double sizeC, String fillColor, String borderColor) {
        this.sizeA = sizeA;
        this.sizeB = sizeB;
        this.sizeC = sizeC;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.name = "Треугольник";

    }

    @Override
    public String getName() {
        return name; }

    @Override
    public double getArea () {
        double semiPer = (sizeA + sizeB + sizeC) / 2;
        return Math.sqrt(semiPer * (semiPer - sizeA) * (semiPer - sizeB) * (semiPer - sizeC));
    }

    @Override
    public double getPerimeter() {
        return  sizeA + sizeB + sizeC;
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
