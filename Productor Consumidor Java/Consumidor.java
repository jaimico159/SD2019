package prodconsu;

import java.lang.Thread;

public class Consumidor extends Thread {

	/*
	 * private String word ; public Consumidor(){ word = "C"; } public void
	 * run(){ synchronized (getClass()) {
	 * 
	 * for (int i = 0; i < 300; i++) {
	 * 
	 * System.out.print(word); System.out.flush(); getClass().notifyAll();
	 * 
	 * try { getClass().wait(); } catch (java.lang.InterruptedException e) { } }
	 * getClass().notifyAll(); } }
	 */
	
	private Contenedor contenedor;

	public Consumidor(Contenedor c) {
		contenedor = c;
	}

	public void run() {
		int value = 0;
		for (int i = 1; i <= 10; i++) {
			
			value = contenedor.get();
			System.out.println("Consumidor, put " + value);
			System.out.flush();
			
			
			try {
				sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
