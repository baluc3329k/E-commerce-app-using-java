import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

abstract class Discount {
    public abstract double applyDiscount(double total, int quantity);
}

class FestiveDiscount extends Discount {
    @Override
    public double applyDiscount(double total, int quantity) {
        return total * 0.9;
    }
}

class BulkDiscount extends Discount {
    @Override
    public double applyDiscount(double total, int quantity) {
        if (quantity > 5) {
            return total * 0.8;
        }
        return total;
    }
}

interface Payment {
    void pay(double amount);
}

class OnlinePayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Total Amount Payable: " + amount);
    }
}

public class Shopify {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> cart = new ArrayList<>();

        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            double price = sc.nextDouble();
            int quantity = sc.nextInt();
            sc.nextLine();
            cart.add(new Product(name, price, quantity));
        }

        String discountType = sc.nextLine().trim().toLowerCase();

        Discount discount;
        if (discountType.equals("festive")) {
            discount = new FestiveDiscount();
        } else {
            discount = new BulkDiscount();
        }

        double total = 0;
        int totalQuantity = 0;

        for (Product p : cart) {
            System.out.println("Product: " + p.getName() + ", Price: " + p.getPrice() + ", Quantity: " + p.getQuantity());
            total += p.getPrice() * p.getQuantity();
            totalQuantity += p.getQuantity();
        }

        double finalAmount = discount.applyDiscount(total, totalQuantity);

        Payment payment = new OnlinePayment();
        payment.pay(finalAmount);

        sc.close();
    }
}
