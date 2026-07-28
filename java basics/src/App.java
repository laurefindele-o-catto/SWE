import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string to reverse: ");
        String original = scanner.nextLine();

        // Use StringBuilder to reverse the string efficiently
        String reversed = new StringBuilder(original).reverse().toString();

        // Output the result
        System.out.println("Reversed string: " + reversed);

        // Close the scanner resource
        scanner.close();
    }
}
