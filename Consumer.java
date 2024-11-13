package Study;

public class Consumer extends Thread {
    private final MyQueue queue;
    private final String name;
    boolean running = true;
    public Consumer(MyQueue queue, String name) {
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