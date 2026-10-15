/**
 * Thread-Safe Item Model.
 * Demonstrates: Encapsulation + Multithreading Thread-Safety (synchronized methods).
 */
public class Item {
    private String id;
    private String name;
    private double price;
    private int quantity;

    public Item(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = (price >= 0) ? price : 0.0;
        this.quantity = (quantity >= 0) ? quantity : 0;
    }

    // Thread-safe getter
    public synchronized int getQuantity() {
        return quantity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    // THREAD-SAFE: Only one thread can add stock at a time
    public synchronized void addStock(int amount) {
        if (amount > 0) {
            this.quantity += amount;
            System.out.printf("[%s] Replenished %d units. New balance: %d\n",
                    Thread.currentThread().getName(), amount, this.quantity);
        }
    }

    // THREAD-SAFE: Only one thread can deduct stock at a time (Prevents overselling!)
    public synchronized boolean deductStock(int amount) {
        if (amount <= 0) return false;

        if (this.quantity >= amount) {
            this.quantity -= amount;
            System.out.printf("  [SUCCESS] [%s] Purchased %d unit(s). Remaining stock: %d\n",
                    Thread.currentThread().getName(), amount, this.quantity);
            return true;
        } else {
            System.out.printf("  [BLOCKED] [%s] Insufficient stock! Requested: %d, Available: %d\n",
                    Thread.currentThread().getName(), amount, this.quantity);
            return false;
        }
    }

    public synchronized void display() {
        System.out.printf("ID: %-8s | Name: %-25s | Price: $%-8.2f | Stock: %-4d\n",
                id, name, price, quantity);
    }
}