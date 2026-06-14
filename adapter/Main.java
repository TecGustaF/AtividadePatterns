interface Logger {
    void info(String message);
    void error(String message);
}

class LegacyLogger {
    public void log(String level, String text) {
        System.out.println("[" + level + "] " + text);
    }
}

class LegacyLoggerAdapter implements Logger {
    private final LegacyLogger legacy;

    public LegacyLoggerAdapter(LegacyLogger legacy) {
        this.legacy = legacy;
    }

    public void info(String message) {
        legacy.log("INFO", message);
    }

    public void error(String message) {
        legacy.log("ERROR", message);
    }
}

class Application {
    private Logger logger;

    public Application(Logger logger) {
        this.logger = logger;
    }

    public void run() {
        logger.info("Iniciando aplicação");
        logger.error("Falha ao conectar no banco");
    }
}

public class Main {
    public static void main(String[] args) {
        LegacyLogger legacy = new LegacyLogger();

        Logger logger = new LegacyLoggerAdapter(legacy);

        Application app = new Application(logger);
        app.run();
    }
}
