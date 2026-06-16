package services;
import entities.Cashier;
import entities.User;
import java.util.ArrayList;

public class CashierService{
    private static ArrayList<Cashier> cashiers;
    public CashierService() {
        cashiers = new ArrayList<>();
        cashiers.add(new Cashier("Cashier1", 25, "987654321V", "Female",
                "0712345678", "Kandy", "cashier123"));
    }
    public static boolean addCashier(String name, int age, String nic, String gender,
                            String phone, String address, String password) {
        cashiers.add(new Cashier(name, age, nic, gender, phone, address, password));
        return true;
    }
    public boolean removeCashier(int userId) {
        return cashiers.removeIf(user -> user.getUserId() == userId);
    }

    public static ArrayList<Cashier> getAllCashiers(){
        return cashiers;
    }

    public static Cashier authenticateCashier(String name, String password) {
        for (Cashier cashier : cashiers) {
            if (cashier.getName().equals(name) && cashier.getPassword().equals(password)) {
                return cashier;
            }
        }
        return null;
    }
}
