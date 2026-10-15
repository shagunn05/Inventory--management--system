/**
 * Test application to demonstrate Collections and Exception Handling.
 */
public class TestLevel2 {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   INVENTORY MANAGEMENT SYSTEM - LEVEL 2 TEST    ");
        System.out.println("=================================================\n");

        Inventory store = new Inventory();

        // 1. Populating Inventory Collection
        store.addItem(new ElectronicsItem("E-101", "MacBook Pro M2", 1299.99, 3, 12));
        store.addItem(new ElectronicsItem("E-102", "Sony WH-1000XM5", 349.99, 5, 24));
        store.addItem(new Item("G-201", "Ergonomic Office Chair", 199.50, 8));

        // Display initial inventory
        store.displayAll();

        // 2. Scenario A: Successful Checkout
        try {
            System.out.println(">>> Scenario 1: Purchasing 2 units of Sony Headphones (E-102)...");
            store.sellItem("E-102", 2);
        } catch (Exception e) {
            System.err.println("[UNEXPECTED ERROR] " + e.getMessage());
        }

        // 3. Scenario B: Handling InsufficientStockException
        try {
            System.out.println("\n>>> Scenario 2: Purchasing 5 MacBooks (E-101) when only 3 are in stock...");
            store.sellItem("E-101", 5);
        } catch (InsufficientStockException e) {
            System.out.println("[CAUGHT EXPECTED EXCEPTION] " + e.getMessage());
        } catch (ItemNotFoundException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        // 4. Scenario C: Handling ItemNotFoundException
        try {
            System.out.println("\n>>> Scenario 3: Purchasing an invalid SKU (SKU-999)...");
            store.sellItem("SKU-999", 1);
        } catch (ItemNotFoundException e) {
            System.out.println("[CAUGHT EXPECTED EXCEPTION] " + e.getMessage());
        } catch (InsufficientStockException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        // Display updated inventory balance
        store.displayAll();
    }
}
