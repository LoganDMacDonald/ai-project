package aiproject.game;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    private GameModel model;

    public GameView(GameModel model) {
        this.model = model;
    }

    public void setModel(GameModel model) {
        this.model = model;
    }

    public GameModel getModel() {
        return model;
    }

    @Override
    protected void paintComponent(Graphics g) {
        final int width = Math.min(getWidth(), getHeight());
        final int height = Math.min(getWidth(), getHeight());

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, width, height);
        g2d.setStroke(new BasicStroke(3));

        for (Entity entity : model.getAgents()) {
            int x = width * entity.getX() / model.getWidth();
            int y = width * entity.getY() / model.getHeight();
            int radarRange = width * model.getRadarRange() / model.getWidth();
            g2d.setColor(Color.RED);
            g2d.fillOval(x, y, 5, 5);
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(entity.getID()), x - 2, y - (radarRange / 3));
            g2d.drawOval(x - radarRange, y - radarRange, 2 * radarRange, 2 * radarRange);
        }

        g2d.setColor(Color.GREEN);
        synchronized (model.getTargets()) {
            for (Entity entity : model.getTargets()) {
                int x = width * entity.getX() / model.getWidth();
                int y = width * entity.getY() / model.getHeight();
                g2d.drawString(String.valueOf(entity.getID()), x - 3, y - 5);
                g2d.fillOval(x - 3, y - 3, 6, 6);
            }
        }
    }
}
