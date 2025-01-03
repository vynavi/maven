public class Factorial {

    // Method to calculate factorial using recursion
    public static int calculateFactorial(int n) {
        if (n == 0) {
            return 1;  // Base case: 0! = 1
        } else {
            return n * calculateFactorial(n - 1);  // Recursive call
        }
    }

    public static void main(String[] args) {
        // Input number for which factorial is to be calculated
        int number = 5;
        
        // Call the method to calculate factorial
        int result = calculateFactorial(number);
        
        // Print the result
        System.out.println("The factorial of " + number + " is: " + result);
    }
}