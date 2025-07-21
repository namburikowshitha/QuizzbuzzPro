package quizzbuzzpro;

public class Main {
    public static void main(String[] args) {
        SoundPlayer player = new SoundPlayer();
        player.playLoop("backgroundmusic.wav");

        try {
            Thread.sleep(10000); // Let it play for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        player.stop(); // Stop after 10 seconds
    }
}
