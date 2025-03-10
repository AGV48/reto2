import java.util.Random;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogGenerate {
    public static final String[] METHODS = {"GET", "POST", "PUT", "DELETE"}; // Métodos Http
    public static final int[] STATUS_CODES = {200, 404, 500, 403, 201}; // Códigos de respuesta

    // Método para generar logs
    public static List<LogEntry> generateLogEntries(int numLogs) {
        List<LogEntry> logs = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numLogs; i++) {
            // Generamos IP aleatoria
            String ip = random.nextInt(256) + "." + random.nextInt(256) + "." +
                    random.nextInt(256) + "." + random.nextInt(256);

            // Generamos fecha y hora aleatoria
            LocalDateTime timestamp = LocalDateTime.now()
                    .minusHours(random.nextInt(24))
                    .minusMinutes(random.nextInt(60))
                    .minusSeconds(random.nextInt(60));

            // Seleccionamos un método HTTP aleatorio
            String requestType = METHODS[random.nextInt(METHODS.length)];

            // Seleccionamos un código de estado aleatorio
            int response = STATUS_CODES[random.nextInt(STATUS_CODES.length)];

            // Creamos un objeto LogEntry y lo agregamos a la lista
            logs.add(new LogEntry(ip, timestamp, requestType, response));
        }

        // Devolvemos la lista de logs generados
        return logs;
    }
}