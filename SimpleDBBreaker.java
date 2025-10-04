public class SimpleDBBreaker {

    static int failureCount = 0;
    static long lastFailureTime = 0;
    static boolean breakerOpen = false;

    static void callDatabase() throws Exception {
        System.out.println("Trying DB call...");
        if (Math.random() < 0.7) throw new Exception("DB connection failed!");
        System.out.println("DB call succeeded!");
    }

    static void dbOperationWithRetryAndBreaker() {
        // Check breaker state
        if (breakerOpen && System.currentTimeMillis() - lastFailureTime < 5000) {
            System.out.println("Breaker OPEN, skipping DB call.");
            return;
        } else if (breakerOpen) {
            System.out.println("Breaker HALF-OPEN, testing DB...");
            breakerOpen = false;
        }

        int retries = 3;
        for (int i = 1; i <= retries; i++) {
            try {
                callDatabase();
                failureCount = 0; // reset on success
                return;
            } catch (Exception e) {
                System.out.println("Attempt " + i + " failed: " + e.getMessage());
                if (i < retries) try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            }
        }

        // All retries failed → open breaker
        failureCount++;
        if (failureCount >= 3) {
            breakerOpen = true;
            lastFailureTime = System.currentTimeMillis();
            System.out.println("Breaker OPEN: Too many failures!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
    	System.out.println("Hemapriya R");
        System.out.println("2117240070115");
        for (int i = 1; i <= 5; i++) {
        	System.out.println("\n--- DB Operation " + i + " ---");
            dbOperationWithRetryAndBreaker();
            Thread.sleep(2000);
        }
    }
}
