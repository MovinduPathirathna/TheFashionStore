package entities;
/**
 * Represents the stock details of an item in the Fashion Store inventory.
 * Handles quantity adjustments and checks if reordering is necessary.
 */

public class Stock {
    private String itemCode;
    private int quantity;
    private int reorderLevel;
    /**
     * Constructs a new Stock entity.
     * * @param itemCode    Unique identifier for the item
     * @param quantity    Current available item quantity
     * @param reorderLevel Minimum capacity before triggering a reorder alert
     */
    public Stock(String itemCode, int quantity, int reorderLevel) {
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }

    public String getItemCode() { return itemCode; }
    public int getQuantity() { return quantity; }
    public int getReorderLevel() { return reorderLevel; }
    /**
     * Updates the current stock quantity.
     * @param amount Positive values increase stock, negative values decrease it.
     */
    public void updateQuantity(int amount) {
        quantity += amount;
    }
    /**
     * Assesses whether stock levels dropped beneath or met the reorder marker.
     * @return true if stock needs replenishment, false otherwise.
     */
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
