package Study;

import java.util.Random;
import java.util.Scanner;

public class printNum implements Runnable {
public static void main(String[] args) {
        Thread printNumThread = new Thread(new printNum());
        printNumThread.start();

    }

    @Override
    public void run() {
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập số phút: ");
        int n = input.nextInt();
        int i = 0;
        int cnt = 1;
        while (i < n*60){
            try {
                Thread.sleep(1000L *n);
            } catch (InterruptedException e) {
                System.out.println("Thread đã bị ngắt");
                System.exit(0);
            }
            Random random = new Random();
            System.out.println("Số random thứ "+cnt+": " + random.nextInt(100));
            i += n;
            cnt++;
        }
        System.out.println("Chương trình đã kết thúc sau "+n+" phút.");

    }
}
