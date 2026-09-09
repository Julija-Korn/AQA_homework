package lesson2_3;

public class Park {

    private Attraction[] attractions;
    private int count;

    public Park (int capacity) {
        this.attractions = new Attraction[capacity];
        this.count = 0;
    }

    public void addAttraction (String name, String workingHours, int price) {
        if (count >= attractions.length) {
            System.out.println ("Парк переполнен!");
            return;
        }
        attractions [count] = new Attraction(name, workingHours, price);
        count++;
    }

    public void printAllAttractions () {
        if (count == 0) {

            System.out.println("Аттракционов нет.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(attractions [i]);
        }
    }

    public class Attraction {
        private String name;
        private String workingHours;
        private int price;

        public Attraction(String name, String workingHours, int price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Attraction{" +
                    "название='" + name + '\'' +
                    ", время работы='" + workingHours + '\'' +
                    ", цена=" + price +
                    '}';
        }
    }

        public static void main(String[] args) {
            Park myPark = new Park(4);

            myPark.addAttraction ("Солнышко", "10:00 - 22:00", 250);
            myPark.addAttraction ("Американские горки", "10:00 - 22:00", 400);
            myPark.addAttraction ("Колесо обозрения", "12:00 - 22:00", 450);

            myPark.printAllAttractions();
        }
}
