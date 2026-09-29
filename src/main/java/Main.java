import java.util.concurrent.*;
import java.util.logging.Logger;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(
                0,
                8,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        executorService.execute(()->{
            Logger.getAnonymousLogger().info("Thread 1 start");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Logger.getAnonymousLogger().info("Thread 1 End");
        });

        executorService.execute(()->{
            Logger.getAnonymousLogger().info("Thread 2 start");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Logger.getAnonymousLogger().info("Thread 2 End");
        });

        executorService.execute(()->{
            Logger.getAnonymousLogger().info("Thread 3 start");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Logger.getAnonymousLogger().info("Thread 3 End");
        });
    }
}