import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Background Daemon Worker Thread.
 * Demonstrates: Runnable interface, Thread sleeping, Daemon threads, and Atomic flags.
 */
public class StockMonitorWorker implements Runnable {
    private final Inventory inventory;
    private final AtomicBoolean isRunning;
    private final long sleepIntervalMs;

    public StockMonitorWorker(Inventory inventory, long sleepIntervalMs) {
        this.inventory = inventory;
        this.isRunning = new AtomicBoolean(true);
        this.sleepIntervalMs = sleepIntervalMs;
    }

    @Override
    public void run() {
        System.out.println("[DAEMON THREAD] StockMonitorWorker started in background...");

        while (isRunning.get()) {
            try {
                // Sleep for the specified interval
                Thread.sleep(sleepIntervalMs);

                // Periodic low-stock audit
                List<Item> lowStock = inventory.getLowStockItems(2);
                if (!lowStock.isEmpty()) {
                    System.out.println("\n🔔 [BACKGROUND ALERT] Low stock detected in warehouse:");
                    for (Item item : lowStock) {
                        System.out.printf("   --> Warning: %s (ID: %s) has only %d unit(s) left!\n",
                                item.getName(), item.getId(), item.getQuantity());
                    }
                    System.out.println();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("[DAEMON THREAD] StockMonitorWorker terminated.");
    }

    public void stopWorker() {
        isRunning.set(false);
    }
}
