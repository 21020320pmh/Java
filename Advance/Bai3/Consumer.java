package Advance.Bai3;

public class Consumer extends Thread {
    private final MyQueue<Message> queue;
    private final String name;
    boolean running = true;
    public Consumer(MyQueue<Message> queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Message message = queue.take();
                Thread.sleep(3000);
                System.out.println(this.name + " got: " + message.getInfo());
            } catch (InterruptedException e) {
                System.out.println("Consumer was interrupted");
            }
        }
    }
}