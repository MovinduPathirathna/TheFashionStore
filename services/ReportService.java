package services;
import entities.Item;
import java.util.ArrayList;

public class ReportService {
    private final BillingService billingService;
    private final InventoryService inventoryService;

    public ReportService(BillingService billingService, InventoryService inventoryService) {
        this.billingService = billingService;
        this.inventoryService = inventoryService;
    }

    public void generateStockReport() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                       STOCK REPORT");
        System.out.println("=".repeat(80));
        System.out.printf("%-8s %-20s %-10s %-5s %-8s %-10s %-10s\n","CODE","NAME","TYPE","SIZE","COLOR","PRICE","QUANTITY");
        System.out.println("-".repeat(80));

        for (Item item : inventoryService.getAllItems()) {
            System.out.println(item);
        }
        System.out.println("=".repeat(80));
    }

    public void generateSalesReport() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                     SALES REPORT");
        System.out.println("=".repeat(70));

        ArrayList<Bill> bills = billingService.getAllBills();
        if (bills.isEmpty()) {
            System.out.println("No sales recorded yet.");
            return;
        }

        double totalSales = 0;
        for (Bill bill : bills) {
            System.out.println("Bill: " + bill.getBillId() +
                    " | Total: Rs." + bill.getFinalAmount());
            totalSales += bill.getFinalAmount();
        }

        System.out.println("-".repeat(70));
        System.out.printf("TOTAL SALES: Rs.%.2f\n", totalSales);
        System.out.println("=".repeat(70));
    }
}
