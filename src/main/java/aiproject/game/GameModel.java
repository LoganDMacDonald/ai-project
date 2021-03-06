package aiproject.game;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private final int width;
    private final int height;
    private final int radarRange;
    private final int targetsPerAgent;
    private final List<Entity> agents;
    private final List<Entity> targets;
    private Scenario scenario = null;

    public GameModel(final int width, final int height, final int radarRange, final int targetsPerAgent) {
        this.agents = new ArrayList<>();
        this.targets = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.radarRange = radarRange;
        this.targetsPerAgent = targetsPerAgent;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void addAgent(Entity agent) {
        synchronized (agents) {
            agents.add(agent);
        }
    }

    public void addTarget(Entity target) {
        synchronized (targets) {
            targets.add(target);
        }
    }

    /**
     * Removes all targets and agents from this model
     */
    public void clear() {
        synchronized (targets) {
            synchronized (agents) {
                agents.clear();
                targets.clear();
            }
        }
    }

    /**
     * Modification of this list must be synchronised
     *
     * @return The list of agent entities part of this game model
     */
    public List<Entity> getAgents() {
        return agents;
    }

    /**
     * Modification of this list must be synchronised
     *
     * @return The list of target entities part of this game model
     */
    public List<Entity> getTargets() {
        return targets;
    }

    public int getRadarRange() {
        return radarRange;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTargetsPerAgent() {
        return targetsPerAgent;
    }

    public void moveAgent(final int id, final int x, final int y) {
        for (Entity agent : agents) {
            if (agent.getID() == id) {
                moveAgent(agent, x, y);
            }
        }
    }

    public void moveAgent(final Entity agent, final int x, final int y) {
        synchronized (agent) {
            agent.setX(x);
            agent.setY(y);
        }
    }

    public void removeTarget(final Entity entity) {
        synchronized (targets) {
            targets.remove(entity);
        }
    }

    public void removeTarget(final int id) {
        synchronized (targets) {
            for (int i = 0; i < targets.size(); i++) {
                if (targets.get(i).getID() == id) {
                    removeTarget(targets.get(i));
                    break;
                }
            }
        }
    }
}
