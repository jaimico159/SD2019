package dicegame;

public class App {
	public static void main(String[] args) throws InterruptedException {
		DiceHistory h1 = new DiceHistory();
		DiceHistory h2 = new DiceHistory();
		h1.start();
		h2.start();
		h1.join();
		h2.join();
		DiceHistory.displayWinner(h1, h2);
	}
}
