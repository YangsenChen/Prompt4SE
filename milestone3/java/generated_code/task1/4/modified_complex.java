private Logger createLog(Class<?> clazz, ConcurrentMap<Class<?>, Logger> loggers)
   {
      // Check if the class object is null
      if (clazz == null) {
         throw new IllegalArgumentException("Class cannot be null.");
      }

      // Initialize variables
      Logger logger = loggers.get(clazz);
      Executor executor = Executors.newSingleThreadExecutor();

      // Use CompletableFuture to create logger object asynchronously
      CompletableFuture<Logger> future = CompletableFuture.supplyAsync(() ->
            Logger.getLogger(clazz), executor);

      try {
         // Wait for future to complete with a timeout of 5 seconds
         logger = future.get(5, TimeUnit.SECONDS);
         loggers.put(clazz, logger);
      } catch (TimeoutException e) {
         // If future does not complete within 5 seconds, cancel it
         future.cancel(true);
         logger = Logger.getLogger(clazz);
      } catch (InterruptedException | ExecutionException e) {
         // Handle exceptions
         logger = Logger.getLogger(clazz);
         logger.severe(e.getMessage());
      }

      // Shutdown executor
      executor.shutdown();

      // Return the logger object
      return logger;
   }