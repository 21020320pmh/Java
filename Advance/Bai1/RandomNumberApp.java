package Advance.Bai1;

public class RandomNumberApp {
    private static boolean running = true;

    public static void main(String[] args) {
        System.out.println("Chương trình đang ghi vào file output.txt");
        for (int i = 0; i < 5; i++) {
            new Thread(new RandomNumber()).start();
        }
        InputThread a = new InputThread();
        a.start();
    }

    public static void setRunning(boolean running) {
        RandomNumberApp.running = running;
    }
    public static boolean isRunning(){
        return running;
    }
}