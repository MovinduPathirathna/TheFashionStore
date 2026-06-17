/**
 * Represents a generic User entity in the system.
 * Handles user attributes and automatic ID generation.
 * * @author YourName
 * @version 1.0
 */
package entities;

public class User {
    private int userId;
    private String name;
    private int age;
    private String nic;
    private String gender;
    private String phone;
    private String address;
    private static int idCounter = 1000;

    public User(String name, int age, String nic, String gender,
                String phone, String address) {
        this.userId = ++idCounter;
        this.name = name;
        this.age = age;
        this.nic = nic;
        this.gender = gender;
        this.phone = phone;
        this.address = address;;
    }

    // Getters
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getPhone() { return phone; }


}