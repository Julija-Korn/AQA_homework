package Lesson_4;

class Cat extends Animal {

    private static int catCount = 0;
    private boolean isFull;

    public Cat(String name) {
        super(name, 200, 0);
        this.isFull = false;
        catCount ++;
    }

    public void eat (Bowl bowl, int amount) {
        if (isFull) {
            System.out.println(name + " сыт и не хочет есть.");
            return;
        }
        boolean success = bowl.takeFood (amount);
        if (success) {
            this.isFull = true;
            System.out.println(name + " поел" + amount + " еды и стал сытым.");
        } else {
            System.out.println(name + " не смог поесть. В миске не достаточно еды");
        }
    }

    public boolean isFull() {
        return isFull;
    }

    public static int getCatCount() {
        return catCount;
    }
}
