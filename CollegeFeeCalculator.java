
public class CollegeFeeCalculator {

    // Calculate tuition fee with 18% GST
    static double calculateTuitionFee(double a, double b) {
        return (a + b) * 1.18;
    }

    // Calculate examination fee with 13% GST
    static double calculateExamFee(double c, double d) {
        return (c + d) * 1.13;
    }

    // Calculate library fee with 7% GST
    static double calculateLibraryFee(double e) {
        return e * 1.07;
    }

    // Calculate total fee after GST
    static double totalBill(double a, double b, double c,
                            double d, double e) {
        return calculateTuitionFee(a, b)+ calculateExamFee(c, d)+ calculateLibraryFee(e);
    }

    // Apply a 20% scholarship discount
    static double calculateDiscountedBill(double a, double b,double c, double d,double e) {
        double total = totalBill(a, b, c, d, e);
        double discount = total * 0.20;
        return total - discount;
    }

    public static void main(String[] args) {

        double total = totalBill(10000, 5000, 1000, 500, 500);
        double discount = total * 0.20;
        double payable = calculateDiscountedBill(
                            10000, 5000, 1000, 500, 500);

        System.out.printf("Total college fees = %.2f%n", total);
        System.out.printf("Scholarship discount = %.2f%n", discount);
        System.out.printf("Final payable fees = %.2f%n", payable);
    }
}
