package Lesson_4;

class Bowl {
    private  int foodAmount;
    public Bowl (int initialFood) {
        this.foodAmount = initialFood;
    }
    public boolean takeFood (int amount) {
        if (foodAmount >= amount) {
            foodAmount -= amount;
            return true;
        }
        return false;
    }

    public void addFood (int amount) {
        if (amount > 0) {
            foodAmount += amount;
            System.out.println("В миску добавлено" + amount + " еды. Теперь в миске: " + foodAmount );
        }
    }

    public int getFoodAmount() {
        return foodAmount;
    }
}
