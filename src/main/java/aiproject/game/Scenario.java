package aiproject.game;

public enum Scenario {
    COMPETITION(1),
    COLLABORATION(2),
    COMPASSION(3);

    private final int num;

    Scenario(final int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
