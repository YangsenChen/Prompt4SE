private Logger createLog()
   {
      Class<?> clazz = getClass();
      Logger logger = loggers.get(clazz);
      if (logger == null)
      {
         logger = Logger.getLogger(clazz);
         loggers.put(clazz, logger);
      }
      return logger;
   }