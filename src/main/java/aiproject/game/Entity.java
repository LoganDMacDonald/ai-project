package aiproject.game;

public class Entity {

    private final int id;
    private int x;
    private int y;

    Entity(final int id, final int x, final int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        synchronized (this) {
            return x;
        }
    }

    public int getY() {
        synchronized (this) {
            return y;
        }
    }

    public int getID() {
        return id;
    }

    public double distanceTo(Entity e) {
        return Math.sqrt(Math.pow(e.getX() - getX(), 2) + Math.pow(e.getY() - getY(), 2));
    }

    void setX(final int x) {
        synchronized (this) {
            this.x = x;
        }
    }

    void setY(final int y) {
        synchronized (this) {
            this.y = y;
        }
    }
}
