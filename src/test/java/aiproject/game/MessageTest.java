package aiproject.game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RunWith(Parameterized.class)
public class MessageTest {

    private final Scenario scenario;
    private Game game;
    private TestAgent sender;
    private TestAgent receiver;
    private List<Agent> agents;

    public MessageTest(final Scenario scenario) {
        this.scenario = scenario;
    }

    @Before
    public void setup() {
        GameModel model = new GameModel(40, 40, 0, 5);

        model.addAgent(new Entity(0, 0, 0));
        model.addAgent(new Entity(1, 0, 0));

        game = new Game(model, scenario);
        sender = new TestAgent(0, "hello world", null, -1);
        receiver = new TestAgent(1, null, "hello world", 0);
        agents = new LinkedList<>();
        agents.add(sender);
        agents.add(receiver);
    }

    @Test
    public void testPublicMessage() {
        game.takeTurn(agents);
        game.takeTurn(agents);
        Assert.assertNotNull("No messaged was passed!", receiver.getMessage());
        Assert.assertEquals(0, receiver.getMessage().getSender());
        Assert.assertEquals(-1, receiver.getMessage().getReceiver());
        Assert.assertEquals("hello world", receiver.getMessage().getMessage());
    }

    @Test
    public void testPrivateMessage() {
        sender.setReceiver(1);
        game.takeTurn(agents);
        game.takeTurn(agents);

        if (scenario == Scenario.COMPETITION) {
            Assert.assertEquals(null, receiver.getMessage());
        } else {
            Assert.assertNotNull("No messaged was passed!", receiver.getMessage());
            Assert.assertEquals(0, receiver.getMessage().getSender());
            Assert.assertEquals(1, receiver.getMessage().getReceiver());
            Assert.assertEquals("hello world", receiver.getMessage().getMessage());
        }
    }

    @Parameterized.Parameters(name = "{0}")
    public static List getParameters() {
        return Arrays.asList(new Object[]{Scenario.COLLABORATION},
                new Object[]{Scenario.COMPETITION}, new Object[]{Scenario.COMPASSION});
    }

    private class TestAgent extends Agent {

        private final String toSend;
        private final String toReceive;
        private Message message = null;
        private int recv;

        TestAgent(int id, String toSend, String toReceive, int recv) {
            super(id);
            this.recv = recv;
            this.toSend = toSend;
            this.toReceive = toReceive;
        }

        @Override
        public Move takeTurn(AgentContext ctx) {
            if (toReceive != null && !ctx.getMessages().isEmpty()) {
                message = ctx.getMessages().get(0);
            }

            if (toSend != null) {
                ctx.sendMessage(toSend, recv);
            }

            return Move.NO_MOVE;
        }

        public void setReceiver(int recv) {
            this.recv = recv;
        }

        public Message getMessage() {
            return message;
        }
    }
}
