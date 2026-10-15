# 📦 Multi-Threaded Inventory Management System (Java)

A robust, enterprise-grade inventory management system built with Core Java, demonstrating the four pillars of modern software engineering: **OOP**, **Collections Framework**, **Custom Exception Handling**, and **Multithreading & Concurrency**.

---

## 🌟 Key Features

* **OOP Design**: Encapsulation with defensive boundary checks, inheritance (`Item` -> `ElectronicsItem`), and polymorphism (`display()`).
* **Collections Framework**: Fast $O(1)$ key-value lookups using `Map<String, Item>` (HashMap).
* **Custom Exception Handling**: Domain-specific exception hierarchy (`InsufficientStockException`, `ItemNotFoundException`) preventing system crashes.
* **Java Streams & Analytics**: Dynamic inventory valuation (`mapToDouble().sum()`), low-stock filters, and price-sorted reporting.
* **Thread Safety & Race-Condition Prevention**: Synchronized methods preventing overselling during simultaneous checkout attempts.
* **Background Daemon Worker**: Automated monitoring thread running periodic low-stock audits without blocking user operations.
* **High-Concurrency Stress Test**: Live simulation using `ExecutorService` and `CountDownLatch` where multiple threads race for limited stock.

---

## 🏗️ Architecture & Concepts

| Concept | Implementation in Project |
| :--- | :--- |
| **Encapsulation** | Private attributes, validated setters, synchronized getters in `Item.java` |
| **Inheritance** | `ElectronicsItem` extends `Item` with specialized warranty tracking |
| **Polymorphism** | Dynamic method overriding for item presentation (`display()`) |
| **Collections** | `HashMap`, `List`, `Streams` API (`filter`, `map`, `sorted`, `collect`) |
| **Exceptions** | Custom checked exceptions with defensive boundary validation |
| **Concurrency** | `synchronized` locks, `Thread.sleep()`, `AtomicBoolean`, `ExecutorService`, `CountDownLatch` |

---

## 🚀 How to Run

### Prerequisites
* Java Development Kit (JDK 17 or higher)
* IntelliJ IDEA, Eclipse, or Command Line

### Run via IDE
* Run `TestLevel4.java` for the complete Multi-threaded Concurrency simulation.
* Run `TestLevel3.java` for Streams, Filtering, and Financial Reporting.
* Run `TestLevel2.java` for Collections and Exception Handling verification.

---

## 🧪 Concurrency Test Output (Zero Overselling Verified)

```text
>>> SCENARIO: FLASH SALE CONCURRENCY TEST
Item: PlayStation 5 Pro | Initial Stock: 2
Simulating 6 customers clicking 'BUY' at the exact same millisecond...

💥 [START SIGNAL] All 6 threads released simultaneously!

  [SUCCESS] [pool-1-thread-2] Purchased 1 unit(s). Remaining stock: 1
  [SUCCESS] [pool-1-thread-1] Purchased 1 unit(s). Remaining stock: 0
  [BLOCKED] [pool-1-thread-4] Insufficient stock! Requested: 1, Available: 0
  [REJECTED] [Customer-4] Insufficient stock for 'PlayStation 5 Pro'. Requested: 1, Available: 0
  ...
✅ [PASSED] THREAD INTEGRITY VERIFIED: Exactly 2 buyers succeeded, 4 were safely blocked.
   Zero race conditions. Stock NEVER became negative!