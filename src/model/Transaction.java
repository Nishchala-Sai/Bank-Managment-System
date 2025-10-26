package model;
import java.sql.Timestamp;

public class Transaction {
    private int id;
    private String type;
    private double amount;
    private Timestamp date;

    public Transaction(int id, String type, double amount, Timestamp date) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public int getId() { return id; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public Timestamp getDate() { return date; }
}
