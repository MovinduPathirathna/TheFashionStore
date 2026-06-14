package entities;

public class Cashier extends User{
    private String password;
    public Cashier(String name, int age, String nic, String gender,
            String phone, String address, String password){
        super(name, age, nic, gender, phone, address);
        this.password = password;
    }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-15s | Role: Cashier | Phone: %s",
                getUserId(), getName(), getPhone());
    }
}
