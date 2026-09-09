package Lesson_4.Figure;

public class Rectangle implements GeometricFigure {
    private double width;
    private  double height;
    private String fillColor;
    private String borderColor;
    private String name;

    public Rectangle(double width, double height, String fillColor, String borderColor) {
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.name = "Прямоугольник";
    }

    @Override
    public String getName() {
        return name; }

    @Override
    public double getArea () {
        return  width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
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
