import java.io.IOException;
import java.util.Random;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogGenerate {
    private static volatile boolean running = true;
    public static final String[] METHODS = {"GET", "POST", "PUT", "DELETE"}; // Métodos Http
    public static final int[] STATUS_CODES = {200, 404, 500, 403, 201}; // Códigos de respuesta

    public static void main(String[] args){
        // Metodo para generar logs
        List<LogEntry> logs = new ArrayList<>();
        Random random = new Random();

        // Crear un hilo que escuche la entrada del usuario
        Thread inputThread = new Thread(() -> {
            try {
                // Esperar a que el usuario presione una tecla
                System.in.read();
                running = false; // Cambiar el estado de running a false para detener el bucle
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        inputThread.start();

        while (running) {
            // Generamos IP aleatoria
            String ip = random.nextInt(256) + "." + random.nextInt(256) + "." +
                    random.nextInt(256) + "." + random.nextInt(256);

            // Generamos fecha y hora actual
            LocalDateTime timestamp = LocalDateTime.now();

            // Seleccionamos un metodo HTTP aleatorio
            String requestType = METHODS[random.nextInt(METHODS.length)];

            // Seleccionamos un código de estado aleatorio
            int response = STATUS_CODES[random.nextInt(STATUS_CODES.length)];

            // Creamos un objeto LogEntry y lo agregamos a la lista
            logs.add(new LogEntry(ip, timestamp, requestType, response));
            System.out.println("IP: " + ip +
                    ", Timestamp: " + timestamp +
                    ", Request Type: " + requestType +
                    ", Response: " + response);

            try {
                // Pausar el hilo principal por 1 segundo
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int successfulLogs = (int) logs.stream().filter(log -> log.response == 200 || log.response == 201).count();
        int failedLogs = (int) logs.stream().filter(log -> log.response == 403 ||
                log.response == 404 || log.response == 500).count();

        System.out.println("\n Cantidad de logs registrados: " + logs.size());
        System.out.println("Cantidad de logs exitosos: " + successfulLogs);
        System.out.println("Cantidad de logs fallidos: " + failedLogs);
    }
}