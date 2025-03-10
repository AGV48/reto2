import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Generar 10 logs
        List<LogEntry> logs = LogGenerate.generateLogEntries(10);

        // Imprimir los logs generados
        for (LogEntry log : logs) {
            System.out.println("IP: " + log.ip +
                    ", Timestamp: " + log.timestamp +
                    ", Request Type: " + log.requestType +
                    ", Response: " + log.response);
        }
    }
}