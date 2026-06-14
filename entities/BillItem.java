package entities;

public class BillItem {
    private Item item;
    private int quantity;
    private double total;

    public BillItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.total = item.getPrice() * quantity;
    }

    public Item getItem() { return item; }
    public int getQuantity() { return quantity; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return String.format("%-8s %-20s %-6d Rs.%-8.2f Rs.%-8.2f",
                item.getItemCode(), item.getName(),
                quantity, item.getPrice(), total);
    }
}
