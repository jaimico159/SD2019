package prodconsu;

import java.lang.Thread;

public class Productor extends Thread {

	/*
	private String word;

	public void run(){
		synchronized (getClass()) {

			for (int i = 0; i < 300; i++) {

				System.out.print(word);
				System.out.flush();
				getClass().notifyAll();

				try {
					getClass().wait();
				} catch (java.lang.InterruptedException e) {
				}
			}
			getClass().notifyAll();
		}
	}
	public void producir(){
		synchronized (getClass()) {

			for (int i = 0; i < 300; i++) {

				System.out.print(word);
				System.out.flush();
				getClass().notifyAll();

				try {
					getClass().wait();
				} catch (java.lang.InterruptedException e) {
				}
			}
			getClass().notifyAll();
		}
	}
	*/
	
	private Contenedor contenedor;
	public Productor(Contenedor c){
		contenedor = c; 
		setPriority(MAX_PRIORITY);
	}
	public void run(){
		
		for (int i = 1; i <= 10; i++) {
			
			
			contenedor.put(i);
			System.out.println("Productor, put "+ i);
			System.out.flush();

			try {
				sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
