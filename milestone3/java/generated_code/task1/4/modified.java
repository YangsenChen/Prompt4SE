private Logger createLog()
   {
      // Get the class object
      Class<?> clazz = getClass();
      
      // Check if logger is already created
      Logger logger = loggers.get(clazz);
      
      // If logger is not created, create a new one and put it into the map
      if (logger == null)
      {
         logger = Logger.getLogger(clazz);
         loggers.put(clazz, logger);
      }
      
      // Return the logger object
      return logger;
   }