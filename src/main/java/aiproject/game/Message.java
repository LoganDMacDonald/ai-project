package aiproject.game;

public class Message {

    private final String message;
    private final int to;
    private final int from;

    Message(final String message, final int to, final int from) {
        this.message = message;
        this.to = to;
        this.from = from;
    }

    public int getReceiver() {
        return to;
    }

    public int getSender() {
        return from;
    }

    public String getMessage() {
        return message;
    }
}
