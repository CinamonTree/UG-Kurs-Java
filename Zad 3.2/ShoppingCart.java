
public class ShoppingCart {
    
    private Product[] items;
    private int capacity;
    private int itemCount;

    public ShoppingCart(int capacity) {
        validateCapacity(capacity);
        this.capacity = capacity;
        this.items = new Product[capacity];
        this.itemCount = 0;
    }

    public void addItem(Product product) {
        if (!isAtMaxCapacity()) {
            this.items[this.itemCount] = product;
            this.itemCount++;
        }
    }

    public void removeItem(String productName) {
        int itemIndex = findItemIndexByName(productName);
        if (!isItemIndexFound(itemIndex)) {
            return;
        }
        deleteItemAt(itemIndex);
    }

    public double calculateTotal() {
        double sum = 0;
        for (int i = 0; i < itemCount; i++) {
            sum += this.items[i].getPrice() * this.items[i].getQuantity();
        }
        return sum;
    }

    public void displayItems() {
        for (Product product : items) {
            System.out.printf("Product: %s, Price: %.2f, Quantity: %d\n", product.getName(), product.getPrice(), product.getQuantity());
        }
    }

    private void deleteItemAt(int index) {
        this.items[index] = null;
        shiftItemsLeft(index);
        this.itemCount--;
    }

    private boolean isItemIndexFound(int index) {
        return index >= 0;
    }

    private int findItemIndexByName(String productName) {
        for (int i = 0; i < this.itemCount; i++) {
            if (this.items[i].getName().equals(productName)) {
                return i;
            }
        }
        return -1;
    }

    private void shiftItemsLeft(int toIndex) {
        for (int i = toIndex; i < this.capacity - 1; i++) {
            this.items[i] = this.items[i + 1];
        }
    }

    private boolean isAtMaxCapacity() {
        return this.capacity == this.itemCount;
    }

    private void validateCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity cannot be lower or equal to 0.");
        }
    }

}
