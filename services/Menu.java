package services;
import entities.Admin;
import entities.Cashier;
/**
 * Boundary/UI Class responsible for generating styled system terminals
 * and operational dashboards.
 */
public class Menu {
    /**
     * Renders primary landing terminal dashboard.
     */
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
    /**
     * Renders workspace modules restricted to system administrators.
     */
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
    /**
     * Renders standard transaction modules restricted to retail cashiers.
     */

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
    /**
     * Prints specific subsection routing for stock operations.
     */

    public static void manageStockMenu(){
        System.out.println("\n--- STOCK MANAGEMENT ---");
        System.out.println("1. Add New Item");
        System.out.println("2. Update Stock Quantity");
        System.out.println("3. View All Items");
        System.out.println("4. Check Low Stock");
        System.out.println("5. Back to Main Menu");
        System.out.print("Select: ");
    }
    /**
     * Prints specific subsection routing for user profile mutations.
     */
    public static void manageUsersMenu(){
        System.out.println("\n--- USER MANAGEMENT ---");
        System.out.println("1. Add New User");
        System.out.println("2. View All Users");
        System.out.println("3. Remove User");
        System.out.println("4. Back to Main Menu");
        System.out.print("Select: ");
    }
    /**
     * Environment utilities evaluating shell instances to clear visibility cleanly.
     */
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
