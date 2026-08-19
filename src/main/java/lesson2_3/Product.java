package lesson2_3;

public class Product {

    private String name;
    private String productionDate;
    private String manufacturer;
    private String countryOfOrigin;
    private double price;
    private boolean isReserved;

    public Product(String name, String productionDate, String manufacturer, String countryOfOrigin, double price, boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.isReserved = isReserved;
    }

    @Override
    public String toString() {
        return "product{" +
                "name='" + name + '\'' +
                ", productionDate='" + productionDate + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                ", price=" + price +
                ", isReserved=" + isReserved +
                '}';
    }

    public void print () {
        System.out.println (this);
    }
}
