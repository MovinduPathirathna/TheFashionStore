package entities;

public class BillItem {
   final private Item item;
   final private int quantity;
   final private double total;
    // added the final key word

    public BillItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.total = item.getPrice() * quantity;
    }

    // removed the never used getItem method and getQuantity method
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return String.format("%-8s %-20s %-6d Rs.%-8.2f Rs.%-8.2f",
                item.getItemCode(), item.getName(),
                quantity, item.getPrice(), total);
    }
}
