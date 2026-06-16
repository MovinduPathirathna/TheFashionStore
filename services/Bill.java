package services;
import entities.BillItem;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Bill {
    final private String billId;
    final private Date date;
    final private String cashierName;
    final private ArrayList<BillItem> items;
    // added the final key word
    private double total;
    private double discount;
    private double finalAmount;

    public Bill(String billId, String cashierName) {
        this.billId = billId;
        this.cashierName = cashierName;
        this.date = new Date();
        this.items = new ArrayList<>();
        this.total = 0;
        this.discount = 0;
        this.finalAmount = 0;
    }

    public void addItem(BillItem item) {
        items.add(item);
        total += item.getTotal();
        finalAmount = total - discount;
    }

    public void applyDiscount(double percentage) {
        discount = total * (percentage / 100);
        finalAmount = total - discount;
    }

    public String getBillId() { return billId; }
    public double getFinalAmount() { return finalAmount; }
    //removed the never used getData, getItems, and getTotal methods

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").repeat("=", 60).append("\n");
        sb.append("                       INVOICE\n");
        sb.repeat("=", 60).append("\n");
        sb.append("Bill ID   : ").append(billId).append("\n");
        sb.append("Date      : ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)).append("\n");
        sb.append("Cashier   : ").append(cashierName).append("\n");
        sb.repeat("-", 60).append("\n");
        sb.append(String.format("%-8s %-20s %-6s %-12s %-12s\n",
                "CODE", "ITEM", "QTY", "PRICE", "TOTAL"));
        sb.repeat("-", 60).append("\n");

        for (BillItem item : items) {
            sb.append(item).append("\n");
        }

        sb.repeat("-", 60).append("\n");
        sb.append(String.format("%-45s Rs.%12.2f\n", "SUB TOTAL:", total));
        sb.append(String.format("%-45s Rs.%12.2f\n", "DISCOUNT:", discount));
        sb.append(String.format("%-45s Rs.%12.2f\n", "FINAL AMOUNT:", finalAmount));
        sb.repeat("=", 60).append("\n");
        //replace append with repeat

        return sb.toString();
    }
}
