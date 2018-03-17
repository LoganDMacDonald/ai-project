package aiproject.agent;

import aiproject.game.Agent;
import aiproject.game.AgentContext;
import aiproject.game.Move;

import java.awt.*;

public abstract class AbstractAgent extends Agent {

    public AbstractAgent(int id) {
        super(id);
    }

    /**
     * Produces the next move towards the specified target position
     *
     * @param ctx This agents contextual information
     * @param target The target position in the game space
     * @return The next move towards the target
     */
    protected Move navigate(AgentContext ctx, Point target) {
        int dx = ctx.getX() - (int) target.getX();
        int dy = ctx.getY() - (int) target.getY();

        // todo navigate through path that is least explored
        if (dx > 0) {
            return Move.LEFT;
        } else if (dx < 0) {
            return Move.RIGHT;
        } else if (dy > 0) {
            return Move.UP;
        } else if (dy < 0) {
            return Move.DOWN;
        }
        return Move.NO_MOVE;
    }
}
