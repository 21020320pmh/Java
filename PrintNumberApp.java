package Study;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintNumberApp {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
            for (int i = 0; i < 30; i++) {
                threadPool.execute(new PrintNumber());
            }
        threadPool.shutdown();
            while (!threadPool.isTerminated()) {
                if(threadPool.isTerminated()){
                    System.out.println("Tất cả các luồng đã hoàn thành");
                }
            }
    }
}


