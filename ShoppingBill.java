
public class ShoppingBill {

    // Electronics: 18% GST
    static double calculateElectronicsGST(double a, double b) {
        return (a + b) * 1.18;
    }

    // Fashion: 13% GST
    static double calculateFashionGST(double c, double d) {
        return (c + d) * 1.13;
    }

    // Food: 7% GST
    static double calculateFoodGST(double e) {
        return e * 1.07;
    }

    // Calculate total bill after GST
    static double totalBill(double a, double b, double c,
                            double d, double e) {
        return calculateElectronicsGST(a, b)+ calculateFashionGST(c, d)+ calculateFoodGST(e);
    }

    // Calculate 20% discount and final payable amount
    static double calculateDiscountedBill(double a, double b,double c, double d,double e) {
        double total = totalBill(a, b, c, d, e);
        double discount = total * 0.20;
        return total - discount;
    }

    public static void main(String[] args) {

        double total = totalBill(100, 100, 100, 100, 100);
        double discount = total * 0.20;
        double payable = calculateDiscountedBill(
                            100, 100, 100, 100, 100);

        System.out.printf("Total after GST = %.2f%n", total);
        System.out.printf("Discount = %.2f%n", discount);
        System.out.printf("Final payable amount = %.2f%n", payable);
    }
}
