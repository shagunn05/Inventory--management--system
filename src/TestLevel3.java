import java.util.List;

/**
 * Runner class to demonstrate Java Streams, Filtering, and Reporting.
 */
public class TestLevel3 {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   INVENTORY MANAGEMENT SYSTEM - LEVEL 3 TEST    ");
        System.out.println("=================================================\n");

        Inventory warehouse = new Inventory();

        // 1. Populate the warehouse with sample catalog items
        warehouse.addItem(new ElectronicsItem("EL-101", "Apple MacBook Pro M3", 1999.00, 4, 12));
        warehouse.addItem(new ElectronicsItem("EL-102", "Sony WH-1000XM5 Headphones", 349.00, 2, 24)); // Low stock!
        warehouse.addItem(new ElectronicsItem("EL-103", "Logitech MX Master 3S Mouse", 99.00, 15, 24));
        warehouse.addItem(new Item("OF-201", "Ergonomic Standing Desk", 450.00, 3));
        warehouse.addItem(new Item("OF-202", "Mesh Ergonomic Chair", 180.00, 1)); // Low stock!
        warehouse.addItem(new Item("OF-203", "Wireless Presenter Remote", 45.00, 0)); // Out of stock!

        warehouse.displayAll();

        // -------------------------------------------------------------
        // Report 1: Total Warehouse Financial Valuation
        // -------------------------------------------------------------
        System.out.println(">>> REPORT 1: TOTAL INVENTORY VALUATION");
        double totalValue = warehouse.calculateTotalValuation();
        System.out.printf("  Total Warehouse Net Worth: $%,.2f USD\n\n", totalValue);

        // -------------------------------------------------------------
        // Report 2: Low-Stock Reorder Report (Threshold <= 2 units)
        // -------------------------------------------------------------
        System.out.println(">>> REPORT 2: LOW STOCK ALERT (Stock <= 2 units)");
        List<Item> lowStockList = warehouse.getLowStockItems(2);
        for (Item item : lowStockList) {
            System.out.printf("  [ALERT] %-25s | Units Remaining: %d\n", item.getName(), item.getQuantity());
        }
        System.out.printf("  Total items requiring replenishment: %d\n\n", lowStockList.size());

        // -------------------------------------------------------------
        // Report 3: Search by Keyword (e.g. searching "Ergonomic")
        // -------------------------------------------------------------
        String searchKeyword = "Ergonomic";
        System.out.println(">>> REPORT 3: SEARCH RESULTS FOR '" + searchKeyword + "'");
        List<Item> searchResults = warehouse.searchByName(searchKeyword);
        searchResults.forEach(item -> System.out.println("  Found: " + item.getName() + " - $" + item.getPrice()));
        System.out.println();

        // -------------------------------------------------------------
        // Report 4: Catalog Sorted by Price (High to Low)
        // -------------------------------------------------------------
        System.out.println(">>> REPORT 4: CATALOG SORTED BY PRICE (DESCENDING)");
        List<Item> sortedItems = warehouse.getItemsSortedByPrice();
        for (Item item : sortedItems) {
            System.out.printf("  $%-8.2f --> %s\n", item.getPrice(), item.getName());
        }
        System.out.println();

        // -------------------------------------------------------------
        // Report 5: Out of Stock Count
        // -------------------------------------------------------------
        System.out.println(">>> REPORT 5: OUT OF STOCK METRIC");
        long outOfStockCount = warehouse.countOutOfStockItems();
        System.out.printf("  Items completely out of stock: %d item(s)\n", outOfStockCount);
    }
}
