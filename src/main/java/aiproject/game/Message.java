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

    /**
     * Who should receive this message?
     *
     * @return -1 if the message is public otherwise the specific agent id of the receiver
     */
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
