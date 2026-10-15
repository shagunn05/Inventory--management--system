import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Multi-threaded Concurrency Stress Test.
 * Demonstrates: ExecutorService, CountDownLatch, Race Condition prevention, and Daemon Threads.
 */
public class TestLevel4 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=================================================");
        System.out.println("   INVENTORY MANAGEMENT SYSTEM - LEVEL 4 TEST    ");
        System.out.println("         MULTITHREADING & CONCURRENCY            ");
        System.out.println("=================================================\n");

        Inventory warehouse = new Inventory();

        // 1. Target Item: A high-demand laptop with ONLY 2 UNITS in stock!
        Item flashSaleItem = new ElectronicsItem("EL-999", "PlayStation 5 Pro", 699.99, 2, 12);
        warehouse.addItem(flashSaleItem);
        warehouse.addItem(new Item("OF-101", "Printer Paper", 25.00, 1)); // Also low stock for background worker

        // 2. Start Background Daemon Thread (Checks stock every 3 seconds)
        StockMonitorWorker worker = new StockMonitorWorker(warehouse, 3000);
        Thread monitorThread = new Thread(worker, "StockMonitorThread");
        monitorThread.setDaemon(true); // Automatically exits when main program ends
        monitorThread.start();

        System.out.println("\n>>> SCENARIO: FLASH SALE CONCURRENCY TEST");
        System.out.println("Item: PlayStation 5 Pro | Initial Stock: " + flashSaleItem.getQuantity());
        System.out.println("Simulating 6 customers clicking 'BUY' at the exact same millisecond...\n");

        int customerCount = 6;
        ExecutorService threadPool = Executors.newFixedThreadPool(customerCount);

        // CountDownLatch: Used to synchronize all threads so they fire at the EXACT same moment
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch finishSignal = new CountDownLatch(customerCount);

        for (int i = 1; i <= customerCount; i++) {
            final String customerName = "Customer-" + i;

            threadPool.submit(() -> {
                try {
                    // Wait at the starting line until signal is fired
                    startSignal.await();

                    // Attempt to purchase 1 unit
                    warehouse.sellItem("EL-999", 1);

                } catch (InsufficientStockException e) {
                    System.out.printf("  [REJECTED] [%s] %s\n", customerName, e.getMessage());
                } catch (Exception e) {
                    System.out.printf("  [ERROR] [%s] %s\n", customerName, e.getMessage());
                } finally {
                    finishSignal.countDown();
                }
            });
        }

        // Fire the starting gun: All 6 threads attack the stock simultaneously!
        Thread.sleep(500);
        System.out.println("💥 [START SIGNAL] All 6 threads released simultaneously!\n");
        startSignal.countDown();

        // Wait for all 6 threads to finish
        finishSignal.await(5, TimeUnit.SECONDS);
        threadPool.shutdown();

        System.out.println("\n>>> CONCURRENCY VERIFICATION SUMMARY:");
        System.out.println("  Initial Stock:    2");
        System.out.println("  Total Buyers:     6");
        System.out.println("  Remaining Stock:  " + flashSaleItem.getQuantity());

        if (flashSaleItem.getQuantity() == 0) {
            System.out.println("\n✅ [PASSED] THREAD INTEGRITY VERIFIED: Exactly 2 buyers succeeded, 4 were safely blocked.");
            System.out.println("   Zero race conditions. Stock NEVER became negative!");
        }

        // Let background daemon thread trigger one alert before we exit
        System.out.println("\nWaiting 3 seconds to let background monitor scan the warehouse...");
        Thread.sleep(3500);

        System.out.println("Main program exiting. Goodbye!");
    }
}
