package aiproject.agent;

import aiproject.game.Agent;
import aiproject.game.AgentContext;
import aiproject.game.Move;
import aiproject.game.Scenario;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class AbstractAgent extends Agent {

    private boolean[][] visited = null;
    private LinkedList<Point> path = new LinkedList<>();
    private Point target = null;

    public AbstractAgent(int id) {
        super(id);
    }

    @Override
    public void init(Scenario scenario, int gameWidth, int gameHeight, int radarRange, int numTargets, int numAgents) {
        super.init(scenario, gameWidth, gameHeight, radarRange, numTargets, numAgents);
        visited = new boolean[gameWidth][gameHeight];
    }

    /**
     * Produces the next move towards the specified target position
     *
     * @param ctx    This agents contextual information
     * @param target The target position in the game space
     * @return The next move towards the target
     */
    protected Move navigate(AgentContext ctx, Point target) {
        if (ctx.getX() == target.x && ctx.getY() == target.y) {
            this.target = null;
            return Move.NO_MOVE;
        }
        if (this.target == null || !this.target.equals(target)) {
            this.target = target;
            path.clear();
            path.addAll(aStar(new Point(ctx.getX(), ctx.getY()), target));
        }

        if (path.isEmpty()) {
            return Move.NO_MOVE;
        }

        Point us = new Point(ctx.getX(), ctx.getY());
        Iterator<Point> pathIterator = path.iterator();

        Point current = pathIterator.next();
        while (pathIterator.hasNext()) {
            Point next = pathIterator.next();
            if (current.equals(us)) {
                int dx = next.x - current.x;
                int dy = next.y - current.y;
                if (dx > 0) {
                    return Move.RIGHT;
                } else if (dx < 0) {
                    return Move.LEFT;
                } else if (dy > 0) {
                    return Move.DOWN;
                } else if (dy < 0) {
                    return Move.UP;
                }
            }
            current = next;
        }

        // must have moved off the path somehow!
        this.target = null;
        return Move.NO_MOVE;
    }

    /**
     * Estimates the travel cost between the start node and the goal. This heuristic will
     * help favour unexplored areas. Since the game is represented in a discrete grid manhattan distance
     * is used to calculate distance and favouritism is applied if there are many unexplored points
     * near the start location
     *
     * @param start The starting point
     * @param goal The goal point
     * @return The heuristic cost estimate (never overestimated)
     */
    private double heuristic(Point start, Point goal) {
        double mDist = manhattanDistance(start, goal);
        int unvisitedCount = 0;
        for (int x = start.x - getRadarRange(); x < start.x + getRadarRange(); x++) {
            for (int y = start.y - getRadarRange(); y < start.y + getRadarRange(); y++) {
                if (x < 0 || y < 0 || x >= getGameWidth() || y >= getGameHeight())
                    continue;
                if (start.distance(x, y) < getRadarRange() && !visited[x][y]) {
                    unvisitedCount++;
                }
            }
        }
        double result = mDist - (unvisitedCount / (Math.PI * getRadarRange() * getRadarRange()));
        result = result - 0.000001 <= 0 ? 0 : result;
        return result;
    }

    private double manhattanDistance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private LinkedList<Point> aStar(Point start, Point goal) {
        HashSet<Point> closedSet = new HashSet<>();
        HashSet<Point> openSet = new HashSet<>();
        openSet.add(start);

        HashMap<Point, Point> cameFrom = new HashMap<>();

        HashMap<Point, Double> gScore = new HashMap<>();
        gScore.put(start, 0.0);

        HashMap<Point, Double> fScore = new HashMap<>();
        fScore.put(start, heuristic(start, goal));

        while (!openSet.isEmpty()) {
            Point current = null;
            double lfScore = Double.POSITIVE_INFINITY;
            for (Point point : openSet) {
                double v = fScore.getOrDefault(point, Double.POSITIVE_INFINITY);
                if (v < lfScore) {
                    lfScore = v;
                    current = point;
                }
            }

            if (current == null) {
                throw new NullPointerException("Current point is null");
            }

            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);
            closedSet.add(current);

            for (Point neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor))
                    continue;
                if (!openSet.contains(neighbor))
                    openSet.add(neighbor);
                double score = gScore.getOrDefault(current, Double.POSITIVE_INFINITY) + manhattanDistance(current, neighbor);

                if (score >= gScore.getOrDefault(neighbor, Double.POSITIVE_INFINITY))
                    continue;

                cameFrom.put(neighbor, current);
                gScore.put(neighbor, score);
                fScore.put(neighbor, score + heuristic(neighbor, goal));
            }
        }
        return null;
    }

    private LinkedList<Point> reconstructPath(Map<Point, Point> cameFrom, Point current) {
        LinkedList<Point> path = new LinkedList<>();
        path.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.addFirst(current);
        }
        return path;
    }

    private List<Point> getNeighbors(Point point) {
        LinkedList<Point> linkedList = new LinkedList<>();
        List<Point> possibleNeighbors = Arrays.asList(new Point(point.x - 1, point.y), new Point(point.x + 1, point.y),
                new Point(point.x, point.y - 1), new Point(point.x, point.y + 1));
        for (Point pt : possibleNeighbors) {
            if (pt.x < 0 || pt.y < 0 || pt.getX() >= getGameWidth() || pt.getY() >= getGameHeight())
                continue;
            linkedList.add(pt);
        }
        return linkedList;
    }
}
