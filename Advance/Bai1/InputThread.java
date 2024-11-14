package Advance.Bai1;

import Study.RandomNumberApp;

import java.util.Scanner;

public class InputThread extends Thread{
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Nhập 'stop' để thoát chương trình");
            String input = scanner.nextLine();
            if (input.equals("stop")) {
                System.out.println("Chương trình đã dừng");
                RandomNumberApp.setRunning(false);
                break;
            }
        }
        scanner.close();
    }
}
