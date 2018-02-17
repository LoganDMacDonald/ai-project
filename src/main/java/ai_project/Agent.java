package ai_project;

import java.util.ArrayList;

public class Agent extends Entity {
    int targetCount = 5;
    boolean[][] visited = new boolean[1000][1000];
    ArrayList<int[][]> DMs = new ArrayList<>();
    ArrayList<int[][]> locations = new ArrayList<>();

    public Agent(int id, int x, int y) {
        super(id, x, y);

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                visited[i][j] = false;
            }
        }

    }

    public void sendDM(int id, int x, int y) {
        Agent a = Game.agents.get(id);
        int[][] b = new int[1][1];
        b[0][0] = x;
        b[0][1] = y;
        a.DMs.add(b);
    }

    public void readDMs() {
        // moves all contents of the DMs to the locations
        if (DMs.size() > 0) {
            for (int i = 0; i < DMs.size(); i++) {
                locations.add(DMs.get(i));
            }
        }
        DMs.clear(); // removes all DMs after checking them
    }

    public void run() {
        System.out.println("agent " + id + " location is " + x + " " + y);
        // listen to direct messages
        // System.out.println("agent " + this.id + " reading DMs");
        readDMs();
        // scan area for targets and agents;
        // System.out.println("agent " + this.id + " scanning");
        scan();

        // move about the lands

        int movedir = (int) (Math.random() * 4); // randomly moves the lad
        // around the place

        switch (movedir) {
            case 0:
                if (legalSpot(x, y + 1)) {
                    moveup();
                    break;
                }
            case 1:
                if (legalSpot(x + 1, y)) {
                    moveright();
                    break;
                }
            case 2:
                if (legalSpot(x, y - 1)) {
                    movedown();
                    break;
                }
            case 3:
                if (legalSpot(x - 1, y)) {
                    moveleft();
                    break;
                }
        }

        // check win
        if (targetCount == 0) {
            broadcast();
        }

    }

    public void broadcast() {
        Game.gameover = true;

    }

    public void scan() {
        checkCircle(this.x, this.y);
    }

    public void avoid() {
        // TODO algorithm to avoid collision with agent
    }

    public void pickupTarget(Target t) {
        Game.targets.remove(t);// removes target from array list of targets
        System.out.println("agent " + this.id + " removed Target");
        Game.gameover = true; // ENDS GAME TO SEE IF ANY TARGET IS PICKED UPs
    }

    private void checkCircle(int startX, int startY) {

        for (int x1 = startX - 10; x1 <= startX + 10; x1++) {
            for (int y1 = startY - 10; y1 <= startY + 10; y1++) {
                if (((x1 - startX) * (x1 - startX) + (y1 - startY) * (y1 - startY)) <= 10 && legalSpot(x1, y1)) {
                    visited[x1][y1] = true;

                    // may have to change to an iterator to compensate for
                    // deleting targets changing the size !!!
                    for (int i = 0; i < Game.targets.size(); i++) {
                        Target tar = Game.targets.get(i);
                        int x = tar.getX();
                        int y = tar.getY();
                        if (x1 == x && y1 == y && tar.getID() == id) { //if target is MINE, pick it up
                            pickupTarget(tar);
                            System.out.println("Agent " + this.id + " picked up target " + tar.getID());
                        } else if (x1 == x && y1 == y && tar.getID() != id) { // if target is anothers': tell them
                            ///directMessage(tar.ownerID, tar.getLocationx(), tar.getLocationy());
                            System.out.println("Agent " + this.id + " picked up target " + tar.getID());
                        }
                    }

                    // check if any agents or targets lie in the circle;
                    for (int i = 0; i < Game.agents.size(); i++) {
                        Agent agent = Game.agents.get(i);
                        int x = agent.getX();
                        int y = agent.getY();
                        if (x1 == x && y1 == y) {
                            avoid();
                        }
                    }

                }
            }
        }
    }

    private void directMessage(int ownerID, int locationx, int locationy) {
        // TODO Auto-generated method stub
        int[][] a = new int[1][1];
        a[0][0] = locationx;
        a[0][1] = locationy;

        Agent ag = Game.agents.get(ownerID);
        ag.DMs.add(a);
    }

    // increments in correct direction towards goal
    // attempts to move in approximated straight line
    public void pathFind(int x2, int y2) {
        int x1 = this.x;
        int y1 = this.y;
        int a = y2 - y1;
        int b = x2 - x1;

        if (b == 0) {
            if (y1 < y2) {
                moveup();
            } else {
                movedown();
            }
        } else if (a == 0) {
            if (x1 < x2) {
                moveright();
            } else {
                moveleft();
            }
        } else if (a < b) {
            if (a < 0) {
                moveup();
            } else {
                movedown();
            }
        } else if (b < a) {
            if (b < 0) {
                moveright();
            } else {
                moveleft();
            }
        }

    }

    public void moveup() {
        this.y++;
    }

    public void movedown() {
        this.y--;
    }

    public void moveright() {
        this.x++;
    }

    public void moveleft() {
        this.x--;
    }

    // checks if given point lies in the map
    public boolean legalSpot(int x, int y) {
        if (x < 0 || x >= 1000 || y < 0 || y >= 1000) {
            return false;
        }
        return true;
    }

}
