package Study;

import java.util.Random;

public class PrintNum implements Runnable {
    public boolean isRunning = true;

    @Override
    public void run() {
        int cnt = 1;
            while (isRunning) {
                Random random = new Random();
                synchronized (this) {
                    try {
                        wait(2000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread đã bị ngắt");
                    }
                    System.out.println("Số random thứ " + cnt + ": của luồng thứ "+ Thread.currentThread().getName() +" là: "+ random.nextInt(1000));
                    cnt++;
                }
            }
    }

    public synchronized void stopRunning(){
        isRunning = false;
    }
    
}
