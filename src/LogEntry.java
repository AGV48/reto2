import java.time.LocalDateTime;

public class LogEntry {

    public String ip;
    public LocalDateTime timestamp;
    public String requestType;
    public int response;

    public LogEntry(String ip, LocalDateTime timestamp, String requestType, int response){
        this.ip = ip;
        this.timestamp = timestamp;
        this.requestType = requestType;
        this.response = response;
    }
}
