public class Main {
    public static void main(String[] args) {
        Resource resource = new RemoteResource();
        RateLimitingStrategy strategy = new SlidingWindowStrategy(3, 4);
        RemoteResourceProxy proxy = new RemoteResourceProxy(strategy, resource);

        for (int i = 0; i < 10; i++) {
            try {
                proxy.getResponse();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
