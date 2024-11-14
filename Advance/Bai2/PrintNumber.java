package Advance.Bai2;

import java.util.Random;

public class PrintNumber implements Runnable{

    private final static Object lock = new Object();

    @Override
    public void run() {
        Random random = new Random();
        synchronized(lock){
            try {
                lock.wait(1000);
            } catch (InterruptedException e) {
                System.out.println("Luồng bị gián đoạn: " + Thread.currentThread().getName());
            }
            System.out.println("Số random của luồng " + Thread.currentThread().getName() + " là: " + random.nextInt(1000));
        }
    }
}
