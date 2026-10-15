public class ElectronicsItem extends Item {
    private int warrantyMonths;

    public ElectronicsItem(String id, String name, double price, int quantity, int warrantyMonths) {
        super(id, name, price, quantity);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("   -> Warranty: " + warrantyMonths + " months");
    }
}




