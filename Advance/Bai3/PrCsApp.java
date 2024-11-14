package Advance.Bai3;

public class PrCsApp {
    public static void main(String[] args) {
        MyQueue<Message> queue = new MyQueue<>(3);
        Producer producer = new Producer(queue, "Producer 1");
        Consumer consumer = new Consumer(queue, "Consumer 1");
        producer.start();
        consumer.start();
    }
}