public class test {
    public static void main(String[] args) {
        System.out.println("=== LEVEL 1: BASIC INVENTORY TEST ===");

        // Naya electronic item banaya
        ElectronicsItem laptop = new ElectronicsItem("E-101", "Dell XPS 15", 85000.0, 5, 24);
        laptop.display();

        // 1. Stock Sell karke dekhte hain
        System.out.println("\n--- Selling 2 laptops ---");
        laptop.deductStock(2);

        // 2. Zyada stock sell karke dekhte hain (Available sirf 3 hai)
        System.out.println("\n--- Selling 10 laptops ---");
        laptop.deductStock(10); // Ye mana kar dega!

        // 3. Stock add karke dekhte hain
        System.out.println("\n--- Restocking 5 laptops ---");
        laptop.addStock(5);
        laptop.display();
    }
}
