package aiproject.game;

public abstract class Agent {

    private int id;

    public Agent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract Move takeTurn(final AgentContext ctx);
}