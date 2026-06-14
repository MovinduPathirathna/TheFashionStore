package services;
import entities.Admin;
import entities.User;
import java.util.ArrayList;

public class AdminService{
    private static ArrayList<Admin> admins;
    public AdminService() {
        admins = new ArrayList<>();// Add default users
        admins.add(new Admin("Admin", 30, "123456789V", "Male",
                "0112345678", "Colombo", "admin123"));
    }
    public static boolean addAdmin(String name, int age, String nic, String gender,
                           String phone, String address, String password) {
        admins.add(new Admin(name, age, nic, gender, phone, address, password));
        return true;
    }
    public boolean removeAdmin(int userId) {
        return admins.removeIf(user -> user.getUserId() == userId);
    }

    public static ArrayList<Admin> getAllAdmins(){
        return admins;
    }

    public static Admin authenticateAdmin(String name, String password) {
        for (Admin admin : admins) {
            if (admin.getName().equals(name) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }
}
