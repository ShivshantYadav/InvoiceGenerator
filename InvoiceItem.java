public class InvoiceItem {
    private Product product;
    private int quantity;

    public InvoiceItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.getPrice() * quantity;
    }

    public String toString() {
        return String.format("%-20s %-10d ₹%-10.2f ₹%-10.2f",
                product.getName(), quantity, product.getPrice(), getTotal());
    }
}
