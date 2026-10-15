/**
 * Custom checked exception thrown when requested quantity exceeds available stock.
 */
public class InsufficientStockException extends Exception {
    public InsufficientStockException(String message) {
        super(message);
    }
}
