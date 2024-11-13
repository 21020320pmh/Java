package Study;

import java.util.Random;

public class Producer extends Thread {
    private final MyQueue queue;
    private final String name;
    boolean running = true;
    private int id = 1;
    public Producer(MyQueue queue, String name) {
        this.queue = queue;
        this.name = name;
    }
    Random msg = new Random();

    @Override
    public void run() {
        while (running) {
            try {
                Message message = new Message(" message: " + msg.nextInt(1000), id++ );
                    queue.put(message);
                    Thread.sleep(1000);
                    System.out.println(this.name + " put: " + message.getInfo());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}