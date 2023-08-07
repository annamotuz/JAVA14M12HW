package TimeMessageThreads;
import java.util.concurrent.TimeUnit;

public class TimeMessageThreads {
    public static void main(String[] args) {

        Thread timeThread = new Thread(new TimeDisplay());
        Thread messageThread = new Thread(new MessageDisplay());

        timeThread.start();
        messageThread.start();
    }
}
class TimeDisplay implements Runnable {
    @Override
    public void run() {
        try {
            int seconds = 0;
            while (true) {
                System.out.println("Elapsed time: " + seconds + " seconds");
                seconds++;
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class MessageDisplay implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Have passed 5 seconds.");
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
