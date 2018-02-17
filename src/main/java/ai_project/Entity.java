package ai_project;

public class Entity {

    protected int id;
    protected int x;
    protected int y;

    public Entity(final int id, final int x, final int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getID() {
        return id;
    }
}
