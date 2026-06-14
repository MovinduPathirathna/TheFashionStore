package services;
import entities.*;
public class AuthService {
    private AdminService adminService;
    private CashierService cashierService;
    private Admin currentAdmin;
    private Cashier currentCashier;

    public AuthService(AdminService adminService) {
        this.adminService = adminService;
        this.currentAdmin = null;
    }
    public AuthService(CashierService cashierService) {
        this.cashierService = cashierService;
        this.currentCashier = null;
    }

    public boolean loginAdmin(String username, String password) {
        Admin admin = AdminService.authenticateAdmin(username, password);
        if (admin != null) {
            currentAdmin = admin;
            return true;
        }
        return false;
    }
    public boolean loginCashier(String username, String password) {
        Cashier cashier = CashierService.authenticateCashier(username, password);
        if (cashier != null) {
            currentCashier = cashier;
            return true;
        }
        return false;
    }

    public void logout(){
        currentAdmin = null;
        currentCashier = null;
    }

    public Admin getCurrentAdmin() {
        return currentAdmin;
    }
    public Cashier getCurrentCashier(){ return currentCashier;}
}
