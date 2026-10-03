import java.util.*;

interface PaymentMethod {
    boolean processPayment(double amount);
    String getMethodName();
}

class CreditCardPayment implements PaymentMethod {
    private boolean shouldSucceed;

    public CreditCardPayment(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }

    @Override
    public boolean processPayment(double amount) {
        return shouldSucceed;
    }

    @Override
    public String getMethodName() { return "Credit Card"; }
}

class PayPalPayment implements PaymentMethod {
    private boolean shouldSucceed;

    public PayPalPayment(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }

    @Override
    public boolean processPayment(double amount) {
        return shouldSucceed;
    }

    @Override
    public String getMethodName() { return "PayPal"; }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

enum OrderStatus {
    PENDING, PAID
}

class Order {
    private Customer customer;
    private Map<Product, Integer> items = new HashMap<>();
    private OrderStatus status;

    public Order(Customer customer) {
        this.customer = customer;
        this.status = OrderStatus.PENDING;
        System.out.printf("Order created for %s.%n", customer.getName());
    }

    public void addProduct(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public boolean processPayment(PaymentMethod paymentMethod) {
        if (items.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return false;
        }

        System.out.printf("Payment initiated via %s for Order %s.%n",
                paymentMethod.getMethodName(), customer.getName());

        double totalAmount = calculateTotal();
        boolean success = paymentMethod.processPayment(totalAmount);

        if (success) {
            this.status = OrderStatus.PAID;
            System.out.printf("Payment for Order %s successful. Order status: %s.%n",
                    customer.getName(), capitalize(status.name()));
            return true;
        } else {
            System.out.printf("Payment for Order %s failed. Order status: %s.%n",
                    customer.getName(), capitalize(status.name()));
            return false;
        }
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}

public class Question05_PaymentProcessing {
    public static void main(String[] args) {
        Product prodA = new Product("Product A", 10.0);
        Product prodB = new Product("Product B", 20.0);
        Product prodC = new Product("Product C", 30.0);

        Customer customerX = new Customer("Customer X");
        Order orderX = new Order(customerX);
        orderX.addProduct(prodA, 2);
        orderX.addProduct(prodB, 1);
        orderX.processPayment(new CreditCardPayment(true));

        Customer customerY = new Customer("Customer Y");
        Order orderY = new Order(customerY);
        orderY.processPayment(new CreditCardPayment(true));

        Customer customerZ = new Customer("Customer Z");
        Order orderZ = new Order(customerZ);
        orderZ.addProduct(prodC, 1);
        orderZ.processPayment(new PayPalPayment(false));
    }
}