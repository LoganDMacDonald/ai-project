package ai_project;

import java.util.ArrayList;

//GAME MASTER
// first targetcount to hit 0 is the winner

public class GM {
	
	public static boolean gameover = false;
	public static ArrayList<Agent> agents = new ArrayList();
	public static ArrayList<Target> targets = new ArrayList();
	public static int winner = -1;

	public static void main(String args[]) {
		// Initialize lists and game state

		boolean gameOver = false;
		int turnNum = 1;

		System.out.println("initializing objects");
		// Initialize all Class objects
		for (int i = 0; i < 5; i++) {
			Agent a = new Agent(i); // 5 agents are made
			agents.add(a);
			System.out.println("agent " + i + "'s starting location is : " + a.getLocationx() + "," + a.getLocationy());
			System.out.println("");
			for (int j = 0; j < 5; j++) {
				Target t = new Target(a, 5 * i + j);
				// 5 targets are made for each agent and each target is given a
				// unique id
				targets.add(t);
				System.out.println(
						"target " + t.getID() + "'s location is : " + t.getLocationx() + "," + t.getLocationy());
				System.out.println("and belongs to agent #" + a.id);
			}
			System.out.println("\n*****");
		}

		System.out.println("Starting game");
		// Run game until finished
		while (!gameover) {
			for (int i = 0; i < agents.size(); i++) {
				Agent a = agents.get(i);
				a.run();
		}
			winner = checkWin();		
			if (winner != -1){gameover = true;}
			System.out.println(" Targets remaining total: " + targets.size());
			System.out.println(" *************  turn " + turnNum++ + " *************");
		}
		System.out.println("\nWinner is agent " + winner);

	}
	

	
	public static int checkWin(){
		//does not account for 2 agents winning at the same time
		Agent a;
		for(int i = 0; i < agents.size();i++){
			a = agents.get(i);
			if (a.targetCount <5){
				return i;
			}
		}
		return -1;
	}



}
