package Study;

import java.util.Random;

public class PrintNumber implements Runnable{
    private final int id;
    private boolean isRunning = true;

    @Override
    public void run() {
        int cnt = 1;
        Random random = new Random();
        while (isRunning) {
            synchronized (this) {
                try {
                    wait(2000);
                    System.out.println("Số random thứ " + cnt + ": của luồng " + id + " là: " + random.nextInt(1000));
                    cnt++;
                    if(cnt > 3) {
                        stopRunning();
                        System.out.println("Đã dừng luồng " + id);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Thread đã bị ngắt");
                }
            }
        }
    }
    public PrintNumber(int  id) {
        this.id = id;
    }

    public synchronized void stopRunning() {
        isRunning = false;
        notify();
    }

    public synchronized boolean isRunning() {
        return isRunning;
    }

}
