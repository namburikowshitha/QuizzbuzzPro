package quizzbuzzpro;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class MailSender {

    public static void sendMail(String recipientEmail, String subject, String messageText) {
        final String senderEmail = "22ht1a05a1@city.ac.in"; // use your email
        final String appPassword = "tncb oorm wzbv jjqf"; // paste your app password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, appPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(messageText);
            Transport.send(message);
            System.out.println("📧 Mail sent to " + recipientEmail);
        } catch (MessagingException e) {
            System.out.println("❌ Mail failed: " + e.getMessage());
        }
    }
}
