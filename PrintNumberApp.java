package Study;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class PrintNumberApp {
    public static void main(String[] args) {
        boolean running = true;
        List<PrintNumber> printNumberTasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        try (ExecutorService threadPool = Executors.newFixedThreadPool(5)) {

            for (int i = 0; i < 20; i++) {
                PrintNumber printNumber = new PrintNumber(i);
                printNumberTasks.add(printNumber);
                threadPool.execute(printNumber);
            }

            System.out.println("Nhấn số của luồng để dừng, hoặc 'e' để thoát.");
            while (running) {
                String input = scanner.nextLine();
                if (input.equals("e")) {
                    for (PrintNumber printNumber : printNumberTasks) {
                        printNumber.stopRunning();
                    }
                    running = false;
                    threadPool.shutdown();
                    System.out.println("Chương trình đã thoát.");
                } else {
                    try {
                        int index = Integer.parseInt(input);
                        if (index >= 0 && index < printNumberTasks.size()) {
                            printNumberTasks.get(index).stopRunning();
                            System.out.println("Đã ngắt luồng " + index);
                        } else {
                            System.out.println("Vui lòng nhập số hợp lệ.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Vui lòng nhập số hợp lệ hoặc 'e' để thoát.");
                    }
                }
                boolean allStopped = true;
                for (PrintNumber printNumber : printNumberTasks) {
                    if (printNumber.isRunning()) {
                        allStopped = false;
                        break;
                    }
                }
                if (allStopped) {
                    running = false;
                    threadPool.shutdown();
                    System.out.println("Tất cả đã chạy xong. Chương trình sẽ dừng");
                }
            }
        }
    }
}