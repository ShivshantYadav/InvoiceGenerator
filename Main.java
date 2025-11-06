import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Invoice invoice = new Invoice();

        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();

        while (true) {
            System.out.print("\nEnter product name (or 'done' to finish): ");
            String name = sc.nextLine();
            if (name.equalsIgnoreCase("done")) break;

            System.out.print("Enter price: ");
            double price = sc.nextDouble();

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();
            sc.nextLine(); 

            invoice.addItem(new Product(name, price), qty);
        }

        System.out.println("\nGenerating invoice...");
        invoice.generateInvoiceFile(customerName);

        System.out.println("\n✅ Invoice Generated Successfully!");
    }
}
