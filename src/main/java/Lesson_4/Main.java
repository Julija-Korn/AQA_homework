package Lesson_4;

public class Main {

    public static void main (String[] args) {
        System.out.println("1. Создаём животных");
        Cat cat1 = new Cat ("Мурка");
        Cat cat2 = new Cat ("Персик");
        Cat cat3 = new Cat ("Мийя");
        Dog dog1 = new Dog ("Арчи");
        Dog dog2 = new Dog ("Гав");

        System.out.println("Создано котов: " + Cat.getCatCount());
        System.out.println("Создано собак: " + Dog.getDogCount());
        System.out.println("Всего животных: " + Animal.getAnimalCount());

        System.out.println("\n 2. Проверяем бег и плавание");
        cat1.run ( 170);
        cat2.run ( 30);
        cat3.run ( 230);
        cat1.swim ( 4);

        dog1.run ( 480);
        dog2.run ( 370);
        dog1.swim ( 8);
        dog2.swim ( 15);

        System.out.println("\n 3. Создаем миску и кормим котов");
        Bowl bowl = new Bowl(25);
        System.out.println("В миске: " + bowl.getFoodAmount() + " еды.");

        Cat[] cats = {cat1, cat2, cat3};
        for (Cat cat : cats) {
            cat.eat(bowl,  10);
        }

        System.out.println("\n 4. Проверяем сытость котов");
        for (Cat cat : cats) {
            System.out.println(cat.name + " сыт? " + (cat.isFull() ? " Да" : " Нет"));
        }

        System.out.println("\n 5. Добавляем еду в миску и кормим голодного кота ");
        bowl.addFood( 15);
        cat3.eat(bowl,  10);

        System.out.println("Остаток еды в миске:" + bowl.getFoodAmount());
    }
}
