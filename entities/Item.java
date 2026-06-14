package entities;

public class Item {
    private String itemCode;
    private String name;
    private String type;
    private String size;
    private String color;
    private double price;
    private int quantity;

    public Item(String itemCode, String name, String type, String size,
                String color, double price, int quantity) {
        this.itemCode = itemCode;
        this.name = name;
        this.type = type;
        this.size = size.toUpperCase();
        this.color = color;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemCode() { return itemCode; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getSize() { return size; }
    public String getColor() { return color; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }


    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }


    public boolean sellItem(int qty) {
        if (qty <= quantity) {
            quantity -= qty;
            return true;
        }
        return false;
    }

    public void addStock(int qty) {
        quantity += qty;
    }

    public boolean needsReorder(int threshold) {
        return quantity <= threshold;
    }

    @Override
    public String toString() {
        return String.format("%-8s %-20s %-10s %-5s %-8s Rs.%-8.2f %-5d",
                itemCode, name, type, size, color, price, quantity);
    }
    public String toFile(){
        return itemCode+","+name+","+type+","+size+","+color+","+price+","+quantity;
    }
}