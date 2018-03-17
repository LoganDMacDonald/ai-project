package aiproject.game;

public abstract class Agent {

    private final int id;

    private Scenario scenario;
    private int gameWidth;
    private int gameHeight;
    private int radarRange;
    private int numTargets;
    private int numAgents;

    public Agent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void init(final Scenario scenario, final int gameWidth, final int gameHeight,
                     final int radarRange, final int numTargets, final int numAgents) {
        this.scenario = scenario;
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.radarRange = radarRange;
        this.numTargets = numTargets;
        this.numAgents = numAgents;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public int getRadarRange() {
        return radarRange;
    }

    public int getNumTargets() {
        return numTargets;
    }

    public int getNumAgents() {
        return numAgents;
    }

    public abstract Move takeTurn(final AgentContext ctx);
}
