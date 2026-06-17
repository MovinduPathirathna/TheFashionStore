import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import entities.*;
import services.AdminService;
import services.CashierService;
import services.InventoryService;
import services.AuthService;
import services.ReportService;
import services.BillingService;
import services.Menu;
import services.Bill;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static AdminService adminService = new AdminService();
    private static CashierService cashierService = new CashierService();
    private static InventoryService inventoryService = new InventoryService();
    private static BillingService billingService = new BillingService(inventoryService);
    private static ReportService reportService = new ReportService(billingService, inventoryService);
    private static AuthService authServiceAdmin;
    private static AuthService authServiceCashier;

    public static void main(String[] args){
        System.out.println("Welcome...");
        while (true) {
            Menu.displayMainMenu();
            String choice = scanner.next();
            scanner.nextLine();
            switch (choice) {
                case "1":
                    authServiceAdmin = new AuthService(adminService);
                    adminLogin();
                    break;
                case "2":
                    authServiceCashier = new AuthService(cashierService);
                    cashierLogin();
                    break;
                case "3":
                    System.out.println("Thank you for using the system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Press Enter to continue...");
                    scanner.nextLine();
                    break;
            }
        }
    }

    private static void adminLogin() {
        System.out.print("\nEnter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (authServiceAdmin.loginAdmin(username, password)) {
            adminDashboard();
        } else {
            System.out.println("Invalid admin credentials! Press Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void cashierLogin() {
        System.out.print("\nEnter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (authServiceCashier.loginCashier(username, password)) {
            cashierDashboard();
        } else {
            System.out.println("Invalid credentials! Press Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void adminDashboard() {
        Admin currentAdmin = authServiceAdmin.getCurrentAdmin();

        while (true) {
            Menu.displayAdminMenu(currentAdmin);
            String choice = scanner.next();
            scanner.nextLine();

            switch (choice) {
                case "1":
                    createNewBill(currentAdmin);
                    break;
                case "2":
                    manageStock();
                    break;
                case "3":
                    manageUsers();
                    break;
                case "4":
                    reportService.generateSalesReport();
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                case "5":
                    reportService.generateStockReport();
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                case "6":
                    authServiceAdmin.logout();
                    System.out.println("Logged out successfully! Press Enter to continue...");
                    scanner.nextLine();
                    return;
                default:
                    System.out.println("Invalid option! Press Enter to continue...");
                    scanner.nextLine();
            }
        }
    }

    private static void cashierDashboard() {
        Cashier currentCashier = authServiceCashier.getCurrentCashier();

        while (true) {
            Menu.displayCashierMenu(currentCashier);
            String choice = scanner.next();
            scanner.nextLine();

            switch (choice) {
                case "1":
                    createNewBill(currentCashier);
                    break;
                case "2":
                    reportService.generateStockReport();
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    break;
                case "3":
                    authServiceCashier.logout();
                    System.out.println("Logged out successfully! Press Enter to continue...");
                    scanner.nextLine();
                    return;
                default:
                    System.out.println("Invalid option! Press Enter to continue...");
                    scanner.nextLine();
            }
        }
    }

    private static void createNewBill(User user) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("               CREATE NEW BILL");
        System.out.println("=".repeat(50));

        String billId = billingService.createNewBill(user.getName());
        System.out.println("Bill ID: " + billId);
        System.out.println("Enter item codes (type 'DONE' to finish, 'LIST' to view items):");

        while (true) {
            System.out.print("\nEnter Item Code (3 digits): ");
            String code = scanner.nextLine().trim().toUpperCase();

            if (code.equals("DONE")) {
                break;
            }

            if (code.equals("LIST")) {
                viewAvailableItems();
                continue;
            }

            Item item = inventoryService.getItemByCode(code);
            if (item == null) {
                System.out.println("Item not found! Try codes like: 131, 251, 343, 422");
                continue;
            }

            System.out.println("Item: " + item.getName() + " | Price: Rs." + item.getPrice());
            System.out.print("Enter quantity: ");
            int quantity;
            try{
            quantity = scanner.nextInt();
            scanner.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Invalid quantity! Try again!");
                scanner.nextLine();
                continue;
            }
            if (billingService.addItemToBill(billId, code, quantity)) {
                System.out.println("Item added to bill!");
                inventoryService.saveInventory();
            } else {
                System.out.println("Failed to add item. Check stock!");
            }
        }

        Bill bill = billingService.getBill(billId);
        System.out.print("Enter discount percentage: ");
        double discount = scanner.nextDouble();
        bill.applyDiscount(discount);
        if (bill != null && !bill.toString().isEmpty()) {
            System.out.println(bill);
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void viewAvailableItems() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                       AVAILABLE ITEMS");
        System.out.println("=".repeat(80));
        System.out.printf("%-8s %-20s %-10s %-5s %-8s %-10s %-10s\n",
                "CODE", "NAME", "TYPE", "SIZE", "COLOR", "PRICE", "STOCK");
        System.out.println("-".repeat(80));

        for (Item item : inventoryService.getAllItems()) {
            System.out.println(item);
        }
        System.out.println("=".repeat(80));
    }

    private static void manageStock() {
        while (true) {
            Menu.manageStockMenu();
            String choice = scanner.next();
            scanner.nextLine();

            switch (choice) {
                case "1":
                    addNewItem();
                    break;
                case "2":
                    updateStock();
                    break;
                case "3":
                    reportService.generateStockReport();
                    break;
                case "4":
                    System.out.print("Enter low stock threshold: ");
                    int threshold = scanner.nextInt();
                    scanner.nextLine();
                    checkLowStock(threshold);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option!");
            }
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void addNewItem() {
        System.out.println("\n--- ADD NEW ITEM ---\n");
        System.out.print("Item Name: ");
        String name = scanner.nextLine();
        System.out.print("Type (Shirt/T-Shirt/Trousers/Shorts): ");
        String type = scanner.nextLine();
        System.out.print("Size (XS/S/M/L/XL): ");
        String size = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Price: Rs.");
        double price = scanner.nextDouble();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();


        String code="";
        if (type.equalsIgnoreCase("shirt")) { code = code.concat("1"); }
        else if (type.equalsIgnoreCase("t-shirt")) { code = code.concat("2"); }
        else if (type.equalsIgnoreCase("trouser")) { code = code.concat("3"); }
        else if (type.equalsIgnoreCase("short")) { code = code.concat("4"); }

        if (size.equalsIgnoreCase("xs")){ code = code.concat("1"); }
        else if(size.equalsIgnoreCase("s")){ code = code.concat("2"); }
        else if(size.equalsIgnoreCase("m")){ code = code.concat("3"); }
        else if(size.equalsIgnoreCase("l")){ code = code.concat("4"); }
        else if(size.equalsIgnoreCase("xl")){ code = code.concat("5"); }

        if (color.equalsIgnoreCase("black")){ code = code.concat("1"); }
        else if(color.equalsIgnoreCase("green")){ code = code.concat("2"); }
        else if(color.equalsIgnoreCase("blue")){ code = code.concat("3"); }
        else if(color.equalsIgnoreCase("white")){ code = code.concat("4"); }
        else if(color.equalsIgnoreCase("red")){ code = code.concat("5"); }
        else if(color.equalsIgnoreCase("brown")){ code = code.concat("6"); }
        else if(color.equalsIgnoreCase("yellow")){ code = code.concat("7"); }

        Item newItem = new Item(code, name, type, size, color, price, quantity);
        if (inventoryService.addItem(newItem)) {
            System.out.println("Item added successfully!");
            inventoryService.saveInventory();
        } else {
            System.out.println("Item code already exists!");
        }
    }

    private static void updateStock() {
        System.out.print("\nEnter Item Code: ");
        String code = scanner.nextLine();
        System.out.print("Enter quantity to add (use negative to remove): ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if (inventoryService.updateStock(code, quantity)) {
            System.out.println("Stock updated successfully!");
            inventoryService.saveInventory();
        } else {
            System.out.println("Failed to update stock!");
        }
    }

    private static void checkLowStock(int threshold) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("               LOW STOCK ALERT");
        System.out.println("               Threshold: " + threshold);
        System.out.println("=".repeat(60));

        ArrayList<Item> lowStock = inventoryService.getLowStockItems(threshold);
        if (lowStock.isEmpty()) {
            System.out.println("All items have sufficient stock!");
        } else {
            for (Item item : lowStock) {
                System.out.println(item.getItemCode() + " - " + item.getName() +
                        " | Stock: " + item.getQuantity());
            }
        }
        System.out.println("=".repeat(60));
    }

    private static void manageUsers() {
        while (true) {
            Menu.manageUsersMenu();
            String choice = scanner.next();
            scanner.nextLine();

            switch (choice) {
                case "1":
                    addNewUser();
                    break;
                case "2":
                    viewAllUsers();
                    break;
                case "3":
                    removeUser();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid option!");
            }
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void addNewUser() {
        System.out.println("\n--- ADD NEW USER ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        int age;
        while(true){
            System.out.print("Age: ");
            try{
                age = scanner.nextInt();
                break;
            }catch(InputMismatchException e){
                System.out.println("Invalid age!");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        System.out.print("NIC: ");
        String nic = scanner.nextLine();
        System.out.print("Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        String role;
        String password;
        boolean running=true;
        while(running) {
            System.out.print("Role (admin/cashier): ");
            role = scanner.nextLine();
            if (role.equals("admin")) {
                System.out.print("Password: ");
                password = scanner.nextLine();
                AdminService.addAdmin(name, age, nic, gender, phone, address, password);
                System.out.println("User added successfully!");
                running = false;
            } else if (role.equals("cashier")) {
                System.out.print("Password: ");
                password = scanner.nextLine();
                CashierService.addCashier(name, age, nic, gender, phone, address, password);
                System.out.println("User added successfully!");
                running = false;
            } else {
                System.out.println("Invalid role! try again...");
            }
        }
    }

    private static void viewAllUsers() {
        System.out.println("\n--- ALL USERS ---");
        for (Admin admin : AdminService.getAllAdmins()) {
            System.out.println(admin);
        }
        for (Cashier cashier : CashierService.getAllCashiers()){
            System.out.println(cashier);
        }
    }

    private static void removeUser() {
        boolean running=true;
        while(running) {
            System.out.print("Enter user type: ");
            String type = scanner.next();
            System.out.print("Enter User ID to remove: ");
            int userId = scanner.nextInt();
            scanner.nextLine();
            if (type.equalsIgnoreCase("admin")) {
                if (adminService.removeAdmin(userId)) {
                    System.out.println("Admin removed successfully!");
                    running = false;
                } else {
                    System.out.println("Admin not found");
                    running = false;
                }
            } else if (type.equalsIgnoreCase("cashier")) {
                if (cashierService.removeCashier(userId)) {
                    System.out.println("Cashier removed successfully!");
                    running = false;
                } else {
                    System.out.println("Cashier not found");
                    running = false;
                }
            } else {
                System.out.println("Invalid user type! try again...");
            }
        }
    }
}