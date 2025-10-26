package model;

public class User {
    private int id;
    private String username;
    private double balance;

    public User(int id, String username, double balance) {
        this.id = id;
        this.username = username;
        this.balance = balance;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public double getBalance() { return balance; }
}
