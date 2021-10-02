package sockets.server.error;

public class PageNotFoundError extends RuntimeException {
    public PageNotFoundError(String message) {
        super(message);
    }
}
