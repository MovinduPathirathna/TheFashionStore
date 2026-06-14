package entities;

public class Stock {
    private String itemCode;
    private int quantity;
    private int reorderLevel;

    public Stock(String itemCode, int quantity, int reorderLevel) {
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }

    public String getItemCode() { return itemCode; }
    public int getQuantity() { return quantity; }
    public int getReorderLevel() { return reorderLevel; }

    public void updateQuantity(int amount) {
        quantity += amount;
    }

    public boolean needsReorder() {
        return quantity <= reorderLevel;
    }

    @Override
    public String toString() {
        return String.format("%-10s | Stock: %-4d | Reorder: %-4d | Status: %s",
                itemCode, quantity, reorderLevel,
                needsReorder() ? "NEEDS REORDER" : "OK");
    }
}
