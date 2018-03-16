package aiproject.game;

import java.util.LinkedList;
import java.util.List;

public class AgentContext {

    private final List<Entity> nearbyAgents;
    private final List<Entity> nearbyTargets;
    private final List<Message> inboundMessages;
    private final List<Message> outboundMessages;
    private final Scenario scenario;
    private final int targetsCollected;
    private final int id;
    private final int x;
    private final int y;

    AgentContext(List<Entity> nearbyAgents, List<Entity> nearbyTargets, Scenario scenario,
                        int targetsCollected, int id, int x, int y) {
        this.nearbyAgents = nearbyAgents;
        this.nearbyTargets = nearbyTargets;
        this.targetsCollected = targetsCollected;
        this.inboundMessages = new LinkedList<>();
        this.outboundMessages = new LinkedList<>();
        this.scenario = scenario;
        this.id = id;
        this.x = x;
        this.y = y;
    }

    void addInboundMessages(List<Message> messages) {
        inboundMessages.addAll(messages);
    }

    List<Message> getOutboundMessages() {
        return outboundMessages;
    }

    public void sendMessage(final String message) {
        sendMessage(message, -1);
    }

    public void sendMessage(final String message, final int receiver) {
        outboundMessages.add(new Message(message, receiver, id));
    }

    public List<Message> getMessages() {
        return inboundMessages;
    }

    public List<Entity> getNearbyAgents() {
        return nearbyAgents;
    }

    public List<Entity> getNearbyTargets() {
        return nearbyTargets;
    }

    public int getTargetsCollected() {
        return targetsCollected;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
