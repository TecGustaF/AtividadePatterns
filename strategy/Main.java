interface DiscountStrategy {
    double apply(double subtotal);
}

class NoDiscount implements DiscountStrategy {
    public double apply(double subtotal) {
        return subtotal;
    }
}

class PercentageDiscount implements DiscountStrategy {
    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    public double apply(double subtotal) {
        return subtotal * (1 - percent / 100);
    }
}

class FixedDiscount implements DiscountStrategy {
    private double amount;

    public FixedDiscount(double amount) {
        this.amount = amount;
    }

    public double apply(double subtotal) {
        return Math.max(0, subtotal - amount);
    }
}

class Cart {
    private double subtotal;
    private DiscountStrategy strategy;

    public Cart(double subtotal, DiscountStrategy strategy) {
        this.subtotal = subtotal;
        this.strategy = strategy;
    }

    public void setDiscountStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public double total() {
        return strategy.apply(subtotal);
    }
}

public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart(200, new NoDiscount());

        System.out.println("Total (sem desconto): " + cart.total());

        cart.setDiscountStrategy(new PercentageDiscount(10));
        System.out.println("Total (10%): " + cart.total());

        cart.setDiscountStrategy(new FixedDiscount(20));
        System.out.println("Total (-20): " + cart.total());
    }
}
