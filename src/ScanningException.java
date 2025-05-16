public class ScanningException extends Exception {
    public ScanningException(String msg, String source, int linePos) {
        super(String.format("Scanning Error: %s", msg));
    }
}
