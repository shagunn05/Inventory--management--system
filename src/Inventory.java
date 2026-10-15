import java.util.*;
import java.util.stream.Collectors;

/**
 * Enhanced Inventory class demonstrating Java 8+ Streams API,
 * Lambdas, Filtering, and Financial Calculations.
 */
public class Inventory {
    private Map<String, Item> items = new HashMap<>();

    // 1. Basic Catalog Methods
    public void addItem(Item item) {
        if (item != null) {
            items.put(item.getId(), item);
            System.out.println("[CATALOG] Added: " + item.getName());
        }
    }

    public Item getItem(String id) throws ItemNotFoundException {
        if (!items.containsKey(id)) {
            throw new ItemNotFoundException("Item with ID '" + id + "' was not found in the catalog.");
        }
        return items.get(id);
    }

    public void sellItem(String id, int quantity) throws ItemNotFoundException, InsufficientStockException {
        Item item = getItem(id);
        if (item.getQuantity() < quantity) {
            throw new InsufficientStockException("Insufficient stock for '" + item.getName()
                    + "'. Requested: " + quantity + ", Available: " + item.getQuantity());
        }
        item.deductStock(quantity);
    }

    // =========================================================================
    // LEVEL 3: JAVA STREAMS API METHODS
    // =========================================================================

    /**
     * STREAM FEATURE 1: Financial Valuation Report
     * Calculates the total monetary worth of all inventory: SUM(unit price * quantity).
     */
    public double calculateTotalValuation() {
        return items.values().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    /**
     * STREAM FEATURE 2: Search by Keyword
     * Case-insensitive search through item names.
     */
    public List<Item> searchByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>(items.values());
        }
        String lowerKeyword = keyword.toLowerCase().trim();

        return items.values().stream()
                .filter(item -> item.getName().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }

    /**
     * STREAM FEATURE 3: Low-Stock Filter (Reorder Required)
     * Finds all items whose stock level is less than or equal to a given threshold.
     */
    public List<Item> getLowStockItems(int threshold) {
        return items.values().stream()
                .filter(item -> item.getQuantity() <= threshold)
                .collect(Collectors.toList());
    }

    /**
     * STREAM FEATURE 4: Sorting by Price Descending
     * Returns all items ordered from highest price to lowest price.
     */
    public List<Item> getItemsSortedByPrice() {
        return items.values().stream()
                .sorted(Comparator.comparingDouble(Item::getPrice).reversed())
                .collect(Collectors.toList());
    }

    /**
     * STREAM FEATURE 5: Count Out-of-Stock Items
     * Counts items where quantity is exactly 0.
     */
    public long countOutOfStockItems() {
        return items.values().stream()
                .filter(item -> item.getQuantity() == 0)
                .count();
    }

    // Display all items
    public void displayAll() {
        System.out.println("\n==================== CURRENT INVENTORY STORE ====================");
        items.values().forEach(Item::display);
        System.out.println("=================================================================\n");
    }
}