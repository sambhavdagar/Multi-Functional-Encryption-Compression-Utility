import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String EXPECTED_PASSWORD = "AdminPassword123!";
    private static int MAX_ATTEMPTS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        if (!checkPassword(scanner)) {
            System.out.println("Authentication failed. Exiting.");
            return;
        }

        while (true) {
            System.out.println("\n--- Multi-Functional Utility ---");
            System.out.println("1. Test Password Strength");
            System.out.println("2. Caesar Cipher (Encode/Decode)");
            System.out.println("3. Huffman Compression (Requires input.txt)");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    System.out.print("Enter password to test: ");
                    String pwd = scanner.nextLine();
                    PasswordValidator validator = new PasswordValidator(pwd);
                    List<String> weaknesses = validator.getWeaknesses();
                    if (weaknesses.isEmpty()) {
                        System.out.println("Strong password!");
                    } else {
                        System.out.println("Weaknesses found:");
                        for (String w : weaknesses) System.out.println("- " + w);
                    }
                    break;
                case "2":
                    System.out.print("Enter text: ");
                    String text = scanner.nextLine();
                    System.out.print("Enter shift amount (integer): ");
                    int shift = Integer.parseInt(scanner.nextLine());
                    CustomAlphabetCaesarCipher cipher = new CustomAlphabetCaesarCipher("ABCDEFGHIJKLMNOPQRSTUVWXYZ", shift);
                    String encoded = cipher.encode(text);
                    System.out.println("Encoded: " + encoded);
                    System.out.println("Decoded back: " + cipher.decode(encoded));
                    break;
                case "3":
                    System.out.print("Enter file path to compress (e.g., test.txt): ");
                    String file = scanner.nextLine();
                    try {
                        HuffmanEncoder encoder = new HuffmanEncoder();
                        encoder.encodeFile(file, file + ".huff");
                        System.out.println("File compressed successfully to " + file + ".huff");
                    } catch (Exception e) {
                        System.out.println("Error compressing file: " + e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static boolean checkPassword(Scanner scanner) {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter system password: ");
            String input = scanner.nextLine();
            if (input.equals(EXPECTED_PASSWORD)) {
                return true;
            }
            attempts++;
            System.out.println("Incorrect. " + (MAX_ATTEMPTS - attempts) + " attempts remaining.");
        }
        return false;
    }
}
