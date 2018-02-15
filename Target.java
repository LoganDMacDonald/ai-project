package ai_project;

public class Target {

	int ownerID, x, y;

	public Target(Agent a, int i) {
		ownerID = a.getID();
		x = (int) (Math.random() * 1000);
		y = (int) (Math.random() * 1000);

	}

	public int getID() {
		return this.ownerID;
	}

	public int getLocationx() {
		return this.x;
	}
	public int getLocationy() {
		return this.y;
	}
	
	

}
