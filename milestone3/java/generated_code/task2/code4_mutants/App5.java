package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class App {
    private static final Map<Class<?>, Logger> loggers = new HashMap<>();


    public static Logger createLog() {
        Class<?> clazz = new Object() {}.getClass().getEnclosingClass();
        Logger logger = loggers.computeIfAbsent(clazz, key -> {
            Logger newLogger = Logger.getLogger(key.getName());
            newLogger.setLevel(Level.ALL);
            return newLogger;
        });
        return logger;
    }


    // chatgpt generated semantically equivalent code: test pass 3/3
    static Logger createLog1() {
        Class<?> clazz = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();
        return loggers.computeIfAbsent(clazz, cls -> {
            Logger logger = Logger.getLogger(clazz.getName());
            logger.setLevel(Level.ALL);
            return logger;
        });
    }

    // chatgpt generated semantically equivalent code ver2
    public static Logger createLog2() {
        Class<?> clazz = new Object() {}.getClass().getEnclosingClass();
        Logger logger = loggers.get(clazz);
        if (logger != null) {
            logger = Logger.getLogger(clazz.getName());
            logger.setLevel(Level.ALL); // set log level to ALL
            loggers.put(clazz, logger);
        }
        return logger;
    }

    public static void main(String[] args) {
        Logger logger = createLog();
        logger.info("Hello World!"); // log message
    }


}