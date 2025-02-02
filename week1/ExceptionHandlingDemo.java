import java.util.Scanner;

class ExceptionHandlingDemo {
    public static void processInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a number: ");
            double num = scanner.nextDouble();
            double reciprocal = 1 / num;
            System.out.println("Reciprocal: " + reciprocal);
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed.");
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter a numeric value.");
        } finally {
            scanner.close();
        }
    }

    public static void main(String[] args) {
        processInput();
    }
}
