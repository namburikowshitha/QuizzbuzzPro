package quizzbuzzpro;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class QuizUserSystem {
    static final String USER_FILE = "existing.csv";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("🎮 --- Welcome to QuizBuzz Pro ---");

        while (true) {
            System.out.println("\n1️⃣ New Registration");
            System.out.println("2️⃣ Login as Existing User");
            System.out.println("3️⃣ Exit");
            System.out.print("🔝 Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> registerUser(sc);
                case "2" -> loginUser(sc);
                case "3" -> {
                    System.out.println("👋 Goodbye!");
                    return;
                }
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    static void registerUser(Scanner sc) {
        System.out.println("\n📝 --- New User Registration ---");

        String email;
        while (true) {
            System.out.print("📧 Enter Email: ");
            email = sc.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("❌ Invalid email format. Try again.");
                continue;
            }
            if (isAlreadyRegistered(email)) {
                System.out.println("⚠ You are already registered! Try login instead.");
                return;
            }
            break;
        }

        System.out.print("👤 Enter Name: ");
        String name = sc.nextLine();

        String password;
        while (true) {
            System.out.println("""
                    🔐 Your password must include:
                    • At least 8 characters
                    • One uppercase letter
                    • One lowercase letter
                    • One digit
                    • One special character (!@#$%^&*)
                    """);
            System.out.print("🔑 Enter Password: ");
            password = sc.nextLine();
            if (isValidPassword(password)) break;
            System.out.println("❌ Invalid password. Try again.");
        }

        System.out.print("🎓 Enter Education: ");
        String education = sc.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            bw.write(name + "," + email + "," + password + "," + education);
            bw.newLine();
            System.out.println("✅ Registration Successful! You can now log in.");
        } catch (IOException e) {
            System.out.println("⚠ Error writing to file.");
        }
    }

    static void loginUser(Scanner sc) {
        System.out.println("\n🔐 --- Login ---");
        System.out.print("📧 Enter Email: ");
        String email = sc.nextLine();
        System.out.print("🔑 Enter Password: ");
        String password = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts.length == 4 && parts[1].equalsIgnoreCase(email) && parts[2].equals(password)) {
                    System.out.println("✅ Login Successful! Welcome " + parts[0] + " 🎉");
                    QuizManager quizManager = new QuizManager(sc);
                    quizManager.displayCategories();
                    System.out.print("📚 Choose subject name  to attempt: ");
                    String category = sc.nextLine();
                    quizManager.takeQuiz(category, parts[0], parts[1]);
                    return;
                }
            }
            System.out.println("❌ Invalid email or password.");
        } catch (IOException e) {
            System.out.println("⚠ Error reading user file.");
        }
    }

    static boolean isAlreadyRegistered(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts.length >= 2 && parts[1].equalsIgnoreCase(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("⚠ Error checking existing users.");
        }
        return false;
    }

    static boolean isValidPassword(String password) {
        return password.length() >= 8 &&
                Pattern.compile("[A-Z]").matcher(password).find() &&
                Pattern.compile("[a-z]").matcher(password).find() &&
                Pattern.compile("[0-9]").matcher(password).find() &&
                Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
    }

    static boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,}$";
        return Pattern.matches(regex, email.toLowerCase());
    }
}
