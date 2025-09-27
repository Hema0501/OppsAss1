import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactoryDemo {

    // Custom ThreadFactory implementation
    static class CustomThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        private final boolean daemon;

        public CustomThreadFactory(String prefix, boolean daemon) {
            this.namePrefix = prefix;
            this.daemon = daemon;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, namePrefix + "-Thread-" + threadNumber.getAndIncrement());

            // Set daemon property
            t.setDaemon(daemon);

            // Set custom exception handler
            t.setUncaughtExceptionHandler((thread, ex) -> {
                System.out.println("Exception in " + thread.getName() + ": " + ex.getMessage());
            });

            return t;
        }
    }

    public static void main(String[] args) {
        // Create ExecutorService with custom ThreadFactory
    	System.out.println("HEMAPRIYA R");
    	System.out.println("2117240070115");
    	ExecutorService executor = Executors.newFixedThreadPool(
                3, new CustomThreadFactory("Worker", false)
        );

        // Submit tasks
        executor.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " is running (Daemon: "
                    + Thread.currentThread().isDaemon() + ")");
        });

        // Task that throws an exception
        executor.submit(() -> {
            throw new RuntimeException("Something went wrong!");
        });

        // Shutdown executor
        executor.shutdown();
        
    }
}
