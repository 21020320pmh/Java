package Study;

import java.util.Random;

public class Producer extends Thread {
    private final MyQueue<Message> queue;
    private final String name;
    boolean running = true;
    private int id = 1;
    public Producer(MyQueue<Message> queue, String name) {
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
                System.out.println("Producer was interrupted");
            }
        }
    }
}