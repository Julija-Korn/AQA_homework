package Lesson_5;

public class ArrayProcessor {

    public static int processArray (String [][] array)
            throws MyArraySizeException, MyArrayDataException {
        if (array == null || array.length !=4) {
            throw new MyArraySizeException(" Массив должен иметь размер 4х4. Получено строк " +
                    (array == null ? "null" : array.length));
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null || array[i].length != 4) {
                throw new MyArraySizeException("Массив должен иметь размер 4х4. " +
                        "строка " + i + " имеет размер: " +
                        (array[i] == null ? "null" : array[i].length));
            }
        }

        int sum = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в ячейке [" + i + "][" + j + "]: \"" + array[i][j] + "\"");
                }
            }
        }
        return sum;
    }

    public static void demonstrateArrayIndexOutOfBounds () {
        System.out.println("\n Демонстрация ArrayIndexOutOfBoundsException");
        String [][] smallArray = {
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };

        try {
            System.out.println("Попытка доступа к smallArray[3][0]...");
            String value = smallArray [3][0];
            System.out.println("Значение" + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение" + e.getClass().getSimpleName());
            System.out.println("Сообщение: " + e.getMessage());
            System.out.println("Индекс выходит за границы массива!");
        }

        try {
            System.out.println("\nПопытка доступа к smallArray[0][5]...");
            String value = smallArray[0][5];
            System.out.println("Значение: " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Поймано исключение: " + e.getClass().getSimpleName());
            System.out.println("Сообщение: " + e.getMessage());
            System.out.println("Индекс столбца выходит за границы!");
        }
    }
}
