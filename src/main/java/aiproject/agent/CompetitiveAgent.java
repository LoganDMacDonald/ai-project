package aiproject.agent;

import aiproject.game.Agent;
import aiproject.game.AgentContext;
import aiproject.game.Move;

import java.util.Random;

public class CompetitiveAgent extends Agent {

    private final Random r = new Random();

    public CompetitiveAgent(int id) {
        super(id);
    }

    @Override
    public Move takeTurn(AgentContext ctx) {
        return Move.values()[r.nextInt(4)];
    }
}
