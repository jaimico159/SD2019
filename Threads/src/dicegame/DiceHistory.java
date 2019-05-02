package dicegame;

import java.util.ArrayList;
import java.util.List;

public class DiceHistory extends Thread{
	private Dice dice;
	private List<Integer> results;
	
	public DiceHistory() {
		this.dice = Dice.getInstance();
		this.results = new ArrayList<>();
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 6; i++) {
			results.add(this.dice.roll());
			System.out.println("Player with pid: "+this.getId()+" got "+results.get(i));
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<Integer> getResults() {
		return this.results;
	}
	
	public static void displayWinner(DiceHistory h1, DiceHistory h2) {
		int player1 = 0;
		int player2 = 0;
		for(int i = 0; i < 6; i++) {
			if(h1.getResults().get(i) > h2.getResults().get(i)) {
				player1++;
			} else if (h1.getResults().get(i) < h2.getResults().get(i)) {
				player2++;
			}
		}
		if(player1 > player2) {
			System.out.println("Player 1 won with pid: " + h1.getId());
			return;
		} else if(player1 < player2) {
			System.out.println("Player 2 won with pid: " + h2.getId());
			return;
		}
		System.out.println("TIEE!!!");
	}
	
}
