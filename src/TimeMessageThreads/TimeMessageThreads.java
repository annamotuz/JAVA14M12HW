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
                System.out.print("Elapsed time: " + seconds + " seconds");
                seconds++;
                TimeUnit.SECONDS.sleep(1);
                System.out.print("\r"); // Move the cursor back to the beginning of the line
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
                TimeUnit.SECONDS.sleep(5);
                System.out.println("\nHave passed 5 seconds.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
