package lesson2_3;

public class Main {
    public static void main(String[] args) {
        Product product = new Product("Тетрадь", "01.03.2026", "Centrum", "Россия", 79.99,true);
        product.print();

    Product[] productsArray = new Product[5];

    productsArray [0] = new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5009.99, true);
    productsArray [1] = new Product("MacBook Pro 16", "15.01.2025", "Apple Inc.", "USA", 2999.99, false);
    productsArray [2] = new Product("Sony WH-1000XM5", "10.12.2024", "Sony Corporation", "Japan", 39900.90, true);
    productsArray [3] = new Product("Java. Полное руководство", "01.11.2024", "Oracle Press", "USA", 15000.00, true);
    productsArray [4] = new Product("PlayStation 5", "01.12.2024", "Sony Interactive", "Japan", 69999.99, true);

    System.out.println("Список товаров: ");
    for (int i = 0; i < productsArray.length; i++) {
        productsArray [i].print();
       }
    }
}
