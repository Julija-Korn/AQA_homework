package Lesson_5;

public class Main {
    public static void main(String[] args) {
        System.out.println("Тест 1. Корректный массив");
        String[][] validArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = ArrayProcessor.processArray(validArray);
            System.out.println("Сумма всех элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n Тест 2: Неправильный размер");
        String[][] invalidSizeArray = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };

        try {
            int result = ArrayProcessor.processArray(invalidSizeArray);
            System.out.println("Сумма всех элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n Тест 3: Некорректные данные");
        String[][] invalidDataArray = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "abc", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = ArrayProcessor.processArray(invalidDataArray);
            System.out.println("Сумма всех элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n Тест 4: Массив с null строкой");
        String[][] arrayWithNull = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                null,
                {"13", "14", "15", "16"}
        };

        try {
            int result = ArrayProcessor.processArray(arrayWithNull);
            System.out.println("Сумма всех элементов: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        ArrayProcessor.demonstrateArrayIndexOutOfBounds();
    }
}
