package Study;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class randomNum implements Runnable {
    @Override
    public void run() {
        Random random = new Random();
        System.out.println("Đang ghi số random vào file...");
        try {
            while (true) {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt", true));
                bufferedWriter.write(random.nextInt(100) + "\n");
                bufferedWriter.close();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thread đã bị ngắt");
                    System.exit(0);
                }
            }
            } catch(IOException e){
                System.out.println("Lỗi ghi file");
                System.exit(0);
            }
        }
    public static void main(String[] args) {
        Thread randomNumThread = new Thread(new randomNum());
        randomNumThread.start();
        Scanner input = new Scanner(System.in);
        if(input.nextLine().equals("stop")&&randomNumThread.isAlive()){
            System.out.println("Chương trình đã kết thúc.");
            System.exit(0);
        }
    }
}
