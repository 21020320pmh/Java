package Study;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class RandomNumber implements Runnable {

    private final static Object lock = new Object();

    @Override
    public void run() {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("output.txt", true))) {
            while (RandomNumberApp.isRunning()) {
                synchronized (lock) {
                    try {
                        lock.wait(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted: " + Thread.currentThread().getName());
                        Thread.currentThread().interrupt(); // Restore the interrupted status
                    }
                    if (!RandomNumberApp.isRunning()) {
                        break;
                    }
                    bufferedOutputStream.write("Số ngẫu nhiên từ thread ".getBytes());
                    bufferedOutputStream.write(Thread.currentThread().getName().getBytes());
                    bufferedOutputStream.write(" là: ".getBytes());
                    bufferedOutputStream.write(String.valueOf((int) (Math.random() * 1000)).getBytes());
                    bufferedOutputStream.write("\n".getBytes());
                    bufferedOutputStream.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi ghi và file", e);
        }
    }
}
