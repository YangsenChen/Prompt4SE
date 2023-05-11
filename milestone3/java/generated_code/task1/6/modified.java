public static ThreadFactory daemonThreadFactory2(final String name, final int priority, final AtomicInteger count) {
    Thread thread = new Thread();
    thread.setName(name + '-' + count.incrementAndGet());
    thread.setDaemon(true);
    thread.setPriority(priority);
    return new ThreadFactory() {
        @Override
        public Thread newThread(final Runnable r) {
            Thread resultThread = thread;
            thread = new Thread();
            return resultThread;
        }
    };
}