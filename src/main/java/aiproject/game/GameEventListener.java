package aiproject.game;

import java.util.Map;

public interface GameEventListener {

    /**
     * Called after the each turn
     *
     * @param steps A map where the agent Id is the key, and the number of steps is the value
     * @param targetsCollected A map where the agent is the key, and number of targets collected is the value
     */
    void turnComplete(Map<Integer, Integer> steps, Map<Integer, Integer> targetsCollected);

    /**
     * Called when the game is completed
     *
     * @param turnCount The number of turns it took to complete the game
     * @param winner The Id of the winning agent, or Integer.MAX_VALUE in collaboration mode
     */
    void gameComplete(int turnCount, int winner);
}
