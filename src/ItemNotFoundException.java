/**
 * Custom checked exception thrown when an item ID cannot be located in the inventory.
 */
public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
