package aiproject.game;


import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Game {

    private final GameModel model;
    private final Scenario scenario;

    public Game(final GameModel model, final Scenario scenario) {
        this.scenario = scenario;
        this.model = model;
    }

    public void start(List<Agent> agents) {
        model.clear();
        for (Agent agent : agents) {
            model.addAgent(randomEntity(agent.getId(), model.getWidth(), model.getHeight()));
            for (int i = 0; i < model.getTargetsPerAgent(); i++) {
                model.addTarget(randomEntity(agent.getId(), model.getWidth(), model.getHeight()));
            }
        }

        int turns = 0;
        int winner;
        while ((winner = getWinner()) < 0) {
            for (Agent agent : agents) {
                Entity agentEntity = getAgentEntity(agent.getId());
                Objects.requireNonNull(agentEntity);

                List<Entity> nearbyAgents = getNearbyAgents(agentEntity);
                List<Entity> nearbyTargets = getNearbyTargets(agentEntity);

                AgentContext ctx = new AgentContext(nearbyAgents, nearbyTargets, scenario,
                        countTargets(agent.getId()), agent.getId(), agentEntity.getX(), agentEntity.getY());

                makeMove(agent.takeTurn(ctx), agentEntity);
                collectTargets(nearbyTargets.stream()
                        .filter(e -> e.getID() == agent.getId())
                        .collect(Collectors.toList())
                );
            }
            turns++;
        }
        System.err.println("Game Over, Turns taken: " + turns + ", winner = " + winner);
    }

    private void makeMove(Move move, Entity entity) {
        int nx = entity.getX();
        int ny = entity.getY();
        switch (move) {
            case UP:
                ny--;
                break;
            case DOWN:
                ny++;
                break;
            case LEFT:
                nx--;
                break;
            case RIGHT:
                nx++;
                break;
        }
        if (nx < 0 || ny < 0 || nx >= model.getWidth() || ny >= model.getHeight())
            return;

        entity.setX(nx);
        entity.setY(ny);
    }

    private List<Entity> getNearbyAgents(Entity agent) {
        List<Entity> agents = new LinkedList<>();
        synchronized (model.getAgents()) {
            for (Entity other : model.getAgents()) {
                if (agent.distanceTo(other) <= model.getRadarRange() && agent.getID() != other.getID()) {
                    agents.add(other);
                }
            }
        }
        return agents;
    }

    private List<Entity> getNearbyTargets(Entity agent) {
        List<Entity> targets = new LinkedList<>();
        synchronized (model.getTargets()) {
            for (Entity target : model.getTargets()) {
                if (agent.distanceTo(target) <= model.getRadarRange()) {
                    targets.add(target);
                }
            }
        }
        return targets;
    }

    private void collectTargets(List<Entity> targets) {
        synchronized (model.getTargets()) {
            model.getTargets().removeAll(targets);
        }
    }

    public GameModel getModel() {
        return model;
    }

    private Entity getAgentEntity(final int id) {
        for (Entity entity : model.getAgents()) {
            if (entity.getID() == id)
                return entity;
        }
        return null;
    }

    private int countTargets(final int id) {
        synchronized (model.getTargets()) {
            return model.getTargets().stream().mapToInt(t -> t.getID() == id ? 1 : 0).sum();
        }
    }

    public int getWinner() {
        switch (scenario) {
            case COMPASSION:
            case COMPETITION:
                for (Entity entity : model.getAgents()) {
                    if (countTargets(entity.getID()) == 0)
                        return entity.getID();
                }
                break;
            case COLLABORATION:
                if (model.getTargets().size() == 0)
                    return Integer.MAX_VALUE;
                break;
        }
        return -1;
    }

    private static Entity randomEntity(final int id, final int width, final int height) {
        return new Entity(id, (int) (width * Math.random()), (int) (height * Math.random()));
    }
}
