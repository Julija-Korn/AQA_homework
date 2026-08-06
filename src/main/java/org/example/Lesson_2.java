package org.example;

public class Lesson_2 {

    //Метод 1
    public static void printThreeWords () {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    //Метод 2
    public static void checkSumSign() {
        int a = 3;
        int b = 2;
        int sum = a + b;
        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательна");
        }
    }

    // Метод 3
    public static void printColor() {
        int value = 45;
        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Жёлтый");
        } else {
            System.out.println("Зелёный");
        }
    }

    // Метод 4
    public static void compareNumbers() {
        int a = 2;
        int b = 1000;
        if (a >= b) {
            System.out.println(" a >= b ");
        } else {
                System.out.println(" a < b ");
            }
        }

    // Метод 5
    public static boolean isSumInRange (int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // Метод 6
    public static void checkPositiveOrNegative (int number) {
        if (number >= 0) {
            System.out.println("Число " + number + " положительное");
        } else {
            System.out.println("Число " + number + " отрицательное");
        }
    }

    // Метод 7
    public static boolean isNegativeOrPositive (int numder) {
        return numder >= 0;
    }

    // Метод 8
    public static void printStringN  ( String text, int count) {
        for ( int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }

    // Метод 9
    public static boolean isLeapYear ( int year) {
        return ( year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }

    // Метод 10
    public static void invertArray () {
        int[] array = {
            1, 1, 0, 0, 1, 0, 1, 1, 0, 0
        } ;
        for ( int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                array[i] = 0;
            } else {
                array[i] = 1;
            }
        }
        System.out.print (" Массив после замены ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    // Метод 11
    public static void fillArray () {
        int [] array = new int [100];
        for (int i = 0; i < array.length; i++) {
            array [i] = i + 1;
        }
        System.out.print ("Массив: ");
        printArray (array);
    }

    // Метод 12
    public static void multiplyNumbers () {
        int array [] = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.print ("Исходный массив: " );
        printArray (array);
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] = array[i] * 2;
            }
        }
        System.out.print ("Изменённый массив: ");
        printArray(array);
    }

    // Метод 13
    public static void fillDiagonalMatrix (int size) {
        int [][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix [i][i] = 1;
            matrix [i][size - 1 - i] = 1;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print (matrix [i][j] + " ");
            }
            System.out.println ();
        }
    }

    // Метод 14
    public static int[] createArray (int len, int intilialArray) {
        int [] array = new int [len];
        for (int i = 0; i < array.length; i++) {
            array [i] = intilialArray;
        }
        return array;
    }
    private static void printArray (int [] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print (array[i]);
            if (i < array.length - 1) {
                System.out.print (", ");
            }
        }
        System.out.println();
    }

    // Точка входа (main метод)
    public static void main (String[] args) {
        System.out.println("Задание 1: printThreeWords()");
        printThreeWords ();

        System.out.println("\nЗадание 2: checkSumSign()");
        checkSumSign();

        System.out.println("\nЗадание 3: printColor()");
        printColor();

        System.out.println("\nЗадание 4: compareNumbers()");
        compareNumbers();

        System.out.println("\nЗадание 5: isSumInRange(int a, int b)");
        System.out.println("Результат: " + isSumInRange(5,  10));
        System.out.println("Результат: " + isSumInRange(15, 20));

        System.out.println("\nЗадание 6: checkPositiveOrNegative (5)");
        checkPositiveOrNegative (5);
        checkPositiveOrNegative (-6);
        checkPositiveOrNegative (0);

        System.out.println("\nЗадание 7: isNegativeOrPositive (-5)");
        System.out.println(" (-5): " + isNegativeOrPositive (-5));
        System.out.println(" (5): " + isNegativeOrPositive (5));
        System.out.println(" (0): " + isNegativeOrPositive (0));

        System.out.println("\nЗадание 8: printStringN (\"Привет\", 4)");
        printStringN  ("Привет", 4);

        System.out.println("\nЗадание 9: isLeapYear (2024) ");
        System.out.println("2024 год високостный? " + isLeapYear (2024));
        System.out.println("2025 год високостный? " + isLeapYear (2025));
        System.out.println("1900 год високостный? " + isLeapYear (1900));

        System.out.println("\nЗадание 10: invertArray () ");
        invertArray ();

        System.out.println("\nЗадание 11: fillArray ()");
        fillArray ();

        System.out.println("\nЗадание 12: multiplyNumbers () ");
        multiplyNumbers ();

        System.out.println("\nЗадание 13: fillDiagonalMatrix (5) ");
        fillDiagonalMatrix (5);

        System.out.println("\nЗадание 14: createArray (5, 7)" );
        int [] result = createArray (5, 7);
        for (int num : result) {
            System.out.print (num + " ");
        }
        System.out.println();
    }
}
