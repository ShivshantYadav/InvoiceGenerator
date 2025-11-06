import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Invoice {
    private List<InvoiceItem> items = new ArrayList<>();
    private double taxRate = 0.18; // 18% GST

    public void addItem(Product product, int quantity) {
        items.add(new InvoiceItem(product, quantity));
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(InvoiceItem::getTotal).sum();
    }

    public double getTax() {
        return getSubtotal() * taxRate;
    }

    public double getGrandTotal() {
        return getSubtotal() + getTax();
    }

    public void generateInvoiceFile(String customerName) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String fileName = "invoices/Invoice_" + customerName + "_" + dtf.format(LocalDateTime.now()) + ".txt";
            File dir = new File("invoices");
            if (!dir.exists()) dir.mkdir();

            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            writer.println("*********** INVOICE ***********");
            writer.println("Customer: " + customerName);
            writer.println("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            writer.println("--------------------------------------------");
            writer.printf("%-20s %-10s %-10s %-10s\n", "Product", "Qty", "Price", "Total");
            writer.println("--------------------------------------------");
            for (InvoiceItem item : items) {
                writer.println(item);
            }
            writer.println("--------------------------------------------");
            writer.printf("Subtotal: ₹%.2f\n", getSubtotal());
            writer.printf("Tax (18%%): ₹%.2f\n", getTax());
            writer.printf("Grand Total: ₹%.2f\n", getGrandTotal());
            writer.println("***************************************");
            writer.close();

            System.out.println("Invoice saved as: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
