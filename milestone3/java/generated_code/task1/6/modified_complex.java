public class DaemonThreadFactory {
    private static ConcurrentHashMap<String, AtomicInteger> counts = new ConcurrentHashMap<>();

    private static class DaemonThread implements Runnable {
        private final Runnable task;
        private final String name;
        private final int priority;

        public DaemonThread(final Runnable task, final String name, final int priority) {
            this.task = task;
            this.name = name;
            this.priority = priority;
        }

        @Override
        public void run() {
            try {
                Thread.currentThread().setName(name + "-" + counts.get(name).incrementAndGet());
                Thread.currentThread().setDaemon(true);
                Thread.currentThread().setPriority(priority);
                task.run();
            } catch (Throwable t) {
                System.err.println("Exception in thread " + Thread.currentThread().getName() + ": " + t);
            } finally {
                counts.get(name).decrementAndGet();
            }
        }
    }

    public static ThreadFactory daemonThreadFactory(final String name, final int priority) {
        return new ThreadFactory() {
            @Override
            public Thread newThread(final Runnable task) {
                counts.putIfAbsent(name, new AtomicInteger(0));
                return new DaemonThread(task, name, priority);
            }
        };
    }
}