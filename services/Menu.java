package services;
import entities.Admin;
import entities.Cashier;

public class Menu {
    public static void displayMainMenu() {
        clearConsole();
        System.out.println("=".repeat(50));
        System.out.println("\t\tTHE FASHION STORE");
        System.out.println("=".repeat(50));
        System.out.println("\t1. Admin Login");
        System.out.println("\t2. Cashier Login");
        System.out.println("\t3. Exit");
        System.out.println("=".repeat(50));
        System.out.print("Select option: ");
    }

    public static void displayAdminMenu(Admin admin) {
        clearConsole();
        System.out.println("=".repeat(50));
        System.out.println("          ADMIN DASHBOARD");
        System.out.println("       Welcome, " + admin.getName());
        System.out.println("=".repeat(50));
        System.out.println("1. Create New Bill");
        System.out.println("2. Manage Stock");
        System.out.println("3. Manage Users");
        System.out.println("4. View Sales Reports");
        System.out.println("5. View Stock Report");
        System.out.println("6. Logout");
        System.out.println("=".repeat(50));
        System.out.print("Select option: ");
    }

    public static void displayCashierMenu(Cashier cashier) {
        clearConsole();
        System.out.println("=".repeat(50));
        System.out.println("          CASHIER DASHBOARD");
        System.out.println("       Welcome, " + cashier.getName());
        System.out.println("=".repeat(50));
        System.out.println("1. Create New Bill");
        System.out.println("2. Check Stock");
        System.out.println("3. Logout");
        System.out.println("=".repeat(50));
        System.out.print("Select option: ");
    }

    public static void manageStockMenu(){
        System.out.println("\n--- STOCK MANAGEMENT ---");
        System.out.println("1. Add New Item");
        System.out.println("2. Update Stock Quantity");
        System.out.println("3. View All Items");
        System.out.println("4. Check Low Stock");
        System.out.println("5. Back to Main Menu");
        System.out.print("Select: ");
    }

    public static void manageUsersMenu(){
        System.out.println("\n--- USER MANAGEMENT ---");
        System.out.println("1. Add New User");
        System.out.println("2. View All Users");
        System.out.println("3. Remove User");
        System.out.println("4. Back to Main Menu");
        System.out.print("Select: ");
    }
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }
}
