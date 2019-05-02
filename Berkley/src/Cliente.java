


import java.util.*;

public class Cliente extends Thread {
    private Sincronizador cl = new Sincronizador();
	private Random rand;
	private String name;
	private boolean conectado;

	public Cliente(String n) {
		cl.setContador(0);
		rand = new Random();
		this.name = n;
		conectado=true;
	}

	public void detener(){
		conectado = false;
	}
	
	public void run() {

		while (conectado) {
			cl.contar();
			System.out.println(name + " : " + cl.getContador());
			try {
				Thread.sleep(rand.nextInt(100));
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}

	public synchronized void setTime(int t) {
		cl.setContador(t);
	}

	public synchronized int getTime() {
		return cl.getContador();
	}

    
}
