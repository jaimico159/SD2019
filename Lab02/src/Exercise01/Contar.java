package Exercise01;

public class Contar extends Thread {
	private int val;
	
	public Contar(int val) {
		this.val = val;
	}
	
	public int getContar() {
		return this.val;
	}
	
	public void run() {
		while(true) {
			this.val++;
		}
	}
}
