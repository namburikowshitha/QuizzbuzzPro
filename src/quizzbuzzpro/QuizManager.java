package quizzbuzzpro;

import java.io.*;
import java.util.*;

public class QuizManager {
    private Scanner sc;
    private static final String QUESTION_FILE = "questions.csv";

    public QuizManager(Scanner sc) {
        this.sc = sc;
    }

    public void displayCategories() {
        System.out.println("\n📚 Available Quiz Categories:");
        System.out.println("1️⃣ Java");
        System.out.println("2️⃣ Python");
        System.out.println("3️⃣ SQL");
    }

    public void takeQuiz(String category, String userName, String email) {
        List<Question> questions = loadQuestions(category);
        if (questions.isEmpty()) {
            System.out.println("⚠ No questions found for this category.");
            return;
        }

        System.out.print("📌 How many questions do you want to attempt? ");
        int count;
        try {
            count = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid number. Exiting quiz.");
            return;
        }

        if (count > questions.size()) count = questions.size();

        Collections.shuffle(questions);
        List<Question> quizSet = questions.subList(0, count);

         SoundPlayer soundPlayer = new SoundPlayer();
         soundPlayer.playLoop("background.wav");

        int score = 0;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < quizSet.size(); i++) {
            Question q = quizSet.get(i);
            System.out.println("\nQ" + (i + 1) + ": " + q.question);
            System.out.println("A. " + q.optionA);
            System.out.println("B. " + q.optionB);
            System.out.println("C. " + q.optionC);
            System.out.print("👉 Your answer: ");
            String ans = sc.nextLine().trim().toUpperCase();
            if (ans.equals(q.correctAnswer.toUpperCase())) {
                score++;
            }
        }

        long endTime = System.currentTimeMillis();
        long timeTaken = (endTime - startTime) / 1000;

         soundPlayer.stop();
         soundPlayer.playOnce("success.wav");

        System.out.println("\n✅ Quiz Completed!");
        System.out.println("🧠 You scored " + score + " out of " + quizSet.size());
        System.out.println("⏱ Time taken: " + timeTaken + " seconds");

        String subject = "QuizBuzz Pro - Your Quiz Score 🎯";
        String message = "Hi " + userName + ",\n\n" +
                "You scored " + score + " out of " + quizSet.size() + " in the " + getCategoryName(category) + " quiz.\n" +
                "Time taken: " + timeTaken + " seconds.\n\nKeep learning!\n\n- QuizBuzz Pro Team";

        // MailSender.sendMail(email, subject, message); // Uncomment if MailSender is implemented
    }

    private List<Question> loadQuestions(String categoryCode) {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(QUESTION_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 6);
                if (parts.length == 6 && parts[0].equals(categoryCode)) {
                    questions.add(new Question(parts[1], parts[2], parts[3], parts[4], parts[5]));
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading CSV: " + e.getMessage());
        }
        return questions;
    }

    private String getCategoryName(String code) {
        return switch (code) {
            case "1" -> "Java";
            case "2" -> "Python";
            case "3" -> "SQL";
            default -> "Unknown";
        };
    }

    static class Question {
        String question, optionA, optionB, optionC, correctAnswer;

        public Question(String question, String a, String b, String c, String correct) {
            this.question = question;
            this.optionA = a;
            this.optionB = b;
            this.optionC = c;
            this.correctAnswer = correct;
        }
    }
}
