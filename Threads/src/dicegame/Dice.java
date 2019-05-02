package dicegame;

import java.util.concurrent.atomic.AtomicInteger;

public class Dice {
	private static AtomicInteger val;
	private static Dice instance = null;
	
	public Dice() {
		Dice.val = new AtomicInteger(0);
	}
	
	public static Dice getInstance() {
		if(instance == null) {
			Dice.instance = new Dice();
			return Dice.instance;
		}
		return instance;
	}
	
	public int roll() {
		int rand = (int)(Math.rint(Math.random()*11)+1);
		Dice.val.set(rand);
		return Dice.val.get();
	}
}
