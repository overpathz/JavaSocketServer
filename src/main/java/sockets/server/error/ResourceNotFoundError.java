package sockets.server.error;

public class ResourceNotFoundError extends RuntimeException {
    public ResourceNotFoundError(String message) {
        super(message);
    }
}
