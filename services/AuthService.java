package services;
import entities.Admin;
import entities.Cashier;
/**
 * Service handling system-wide authentication operations for Admins and Cashiers.
 */
public class AuthService {
    private AdminService adminService;
     private CashierService cashierService;
    private Admin currentAdmin;
    private Cashier currentCashier;
    /**
     * Initializes authentication tracking specifically for Admin clients.
     */
    public AuthService(AdminService adminService) {
        this.adminService = adminService;
        this.currentAdmin = null;
    }
    /**
     * Initializes authentication tracking specifically for Cashier clients.
     */
    public AuthService(CashierService cashierService) {
        this.cashierService = cashierService;
        this.currentCashier = null;
    }
    /**
     * Authenticates an admin against system profiles.
     * @return true if successful, false otherwise.
     */
    public boolean loginAdmin(String username, String password) {
        Admin admin = AdminService.authenticateAdmin(username, password);
        if (admin != null) {
            currentAdmin = admin;
            return true;
        }
        return false;
    }
    /**
     * Authenticates a cashier against system profiles.
     * @return true if successful, false otherwise.
     */
    public boolean loginCashier(String username, String password) {
        Cashier cashier = CashierService.authenticateCashier(username, password);
        if (cashier != null) {
            currentCashier = cashier;
            return true;
        }
        return false;
    }
    /**
     * Clears all existing active session states.
     */


    public void logout(){
        currentAdmin = null;
        currentCashier = null;
    }

    public Admin getCurrentAdmin() {
        return currentAdmin;
    }
    public Cashier getCurrentCashier(){ return currentCashier;}
}
