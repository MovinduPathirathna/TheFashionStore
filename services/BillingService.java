package services;
import entities.BillItem;
import entities.Item;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BillingService {
    private ArrayList<Bill> bills;
    private InventoryService inventory;

    public BillingService(InventoryService inventory) {
        this.bills = new ArrayList<>();
        this.inventory = inventory;
    }

    public String createNewBill(String cashierName) {
        String billId = "BILL" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        Bill bill = new Bill(billId, cashierName);
        bills.add(bill);
        return billId;
    }

    public Bill getBill(String billId) {
        for (Bill bill : bills) {
            if (bill.getBillId().equals(billId)) {
                return bill;
            }
        }
        return null;
    }

    public boolean addItemToBill(String billId, String itemCode, int quantity) {
        Bill bill = getBill(billId);
        Item item = inventory.getItemByCode(itemCode);

        if (bill != null && item != null && item.sellItem(quantity)) {
            BillItem billItem = new BillItem(item, quantity);
            bill.addItem(billItem);
            return true;
        }
        return false;
    }

    public ArrayList<Bill> getAllBills() {
        return new ArrayList<>(bills);
    }

    public double getTotalSales() {
        double total = 0;
        for (Bill bill : bills) {
            total += bill.getFinalAmount();
        }
        return total;
    }
}
