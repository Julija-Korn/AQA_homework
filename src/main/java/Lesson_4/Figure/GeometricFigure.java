package Lesson_4.Figure;

public interface GeometricFigure {
    String getName();
    String getFillColor ();
    String getBorderColor ();
    double getArea ();
    default double getPerimeter () {
        return 0.0;
    }
    default  void printInfo () {
        System.out.println("Название: " + getName());
        System.out.println("Периметр: " + getPerimeter());
        System.out.println("Площадь: " + getArea());
        System.out.println("Цвет заливки: " + getFillColor());
        System.out.println("Цвет границы: " + getBorderColor());
        System.out.println("-------------------------");
    }
}
