package quizzbuzzpro;

public class TestMail {
    public static void main(String[] args) {
        String to = "kowi.friend@gmail.com"; // ✅ Replace with recipient email
        String subject = "🎉 QuizBuzz Pro Notification";
        String message = "Hey Kowi! You’ve completed your quiz successfully. 📚✨\nKeep up the great work! 💪";

        MailSender.sendMail(to, subject, message);
    }
}

