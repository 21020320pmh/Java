package Study;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintNumApp {
    public static void main(String[] args) {
        boolean running = true;
        List<PrintNum> printNumTasks = new ArrayList<>();
        List<Thread> printNumThreads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            PrintNum printNum = new PrintNum();
            Thread printNumThread = new Thread(printNum);
            printNumTasks.add(printNum);
            printNumThreads.add(printNumThread);
            printNumThread.start();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhấn số của luồng để dừng (0-4), hoặc 'q' để thoát.");

        while (running) {
            String input = scanner.nextLine();

            if (input.equals("q")) {
                for (PrintNum printNum : printNumTasks) {
                    printNum.stopRunning();
                }
                for(Thread printNumThread : printNumThreads){
                    try {
                        printNumThread.join();
                    } catch (InterruptedException e) {
                       System.out.println("Luồng đã bị ngắt.");
                    }
                }
                running = false;
                System.out.println("Chương trình đã dừng.");
            }
            else {
                try {
                    int index = Integer.parseInt(input);
                    if (index >= 0 && index < printNumTasks.size()) {
                        printNumTasks.get(index).stopRunning();
                        try {
                            printNumThreads.get(index).join();
                        } catch (InterruptedException e) {
                            System.out.println("Luồng đã bị ngắt.");
                        }
                            System.out.println("Đã dừng luồng " + index);
                        boolean allStopped = true;
                        for (PrintNum printNum : printNumTasks) {
                            if (printNum.isRunning()) {
                                allStopped = false;
                                break;
                            }
                        }
                        if (allStopped) {
                            running = false;
                            System.out.println("Tất cả các luồng đã dừng. Chương trình sẽ tự động dừng.");
                        }
                    } else {
                        System.out.println("Vui lòng nhập số hợp lệ từ 0 đến 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Vui lòng nhập số hợp lệ hoặc 'q' để thoát.");
                }
            }
        }
    }
}
