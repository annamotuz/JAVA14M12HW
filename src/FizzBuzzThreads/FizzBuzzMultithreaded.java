package FizzBuzzThreads;
import java.util.concurrent.Semaphore;

public class FizzBuzzMultithreaded {
    private int n;
    private Semaphore fizzSemaphore;
    private Semaphore buzzSemaphore;
    private Semaphore fizzBuzzSemaphore;
    private Semaphore numberSemaphore;

    public FizzBuzzMultithreaded(int n) {
        this.n = n;
        this.fizzSemaphore = new Semaphore(0);
        this.buzzSemaphore = new Semaphore(0);
        this.fizzBuzzSemaphore = new Semaphore(0);
        this.numberSemaphore = new Semaphore(1);
    }

    public void fizz() throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 5 != 0) {
                fizzSemaphore.acquire();
                System.out.print("fizz ");
                numberSemaphore.release();
            }
        }
    }

    public void buzz() throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 3 != 0) {
                buzzSemaphore.acquire();
                System.out.print("buzz ");
                numberSemaphore.release();
            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            fizzBuzzSemaphore.acquire();
            System.out.print("fizzbuzz ");
            numberSemaphore.release();
        }
    }

    public void number() throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            numberSemaphore.acquire();
            if (i % 3 == 0 && i % 5 == 0) {
                fizzBuzzSemaphore.release();
            } else if (i % 3 == 0) {
                fizzSemaphore.release();
            } else if (i % 5 == 0) {
                buzzSemaphore.release();
            } else {
                System.out.print(i + " ");
                numberSemaphore.release();
            }
        }
    }

    public static void main(String[] args) {
        int n = 33;
        FizzBuzzMultithreaded fizzBuzz = new FizzBuzzMultithreaded(n);

        Thread threadA = new Thread(() -> {
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadD = new Thread(() -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
            threadD.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
