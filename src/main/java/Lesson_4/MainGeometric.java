package Lesson_4;

import Lesson_4.Figure.Circle;
import Lesson_4.Figure.GeometricFigure;
import Lesson_4.Figure.Rectangle;
import Lesson_4.Figure.Triangle;

public class MainGeometric {
    public static void main (String[] args) {
        System.out.println("ХАРАКТЕРИСТИКИ ФИГУР");

        GeometricFigure[] figures = {
                new Circle("Круг", 5.0, "Жёлтый", "Красный"),
                new Rectangle(4.0, 6.0, "Фиолетовый", "Белый"),
                new Triangle(3.0, 4.0, 5.0, "Зелёный", "Чёрный")
        };

        for (GeometricFigure fig : figures) {
            fig.printInfo();
        }
    }
}
