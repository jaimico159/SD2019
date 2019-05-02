package prodconsu;

import java.lang.Thread;

public class Contenedor extends Thread{

	private int dato;
	private boolean haydato = false;
	public synchronized void put (int valor){
	
		while (haydato){
			
			try {
				wait();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		dato  = valor;
		haydato = true;
		notify();
	}
	
	public synchronized int get(){
		while (!haydato){
			
			try {
				wait();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		haydato = false;
		notify();
		
		return dato;
	}
	/*
	public int get(){
		synchronized (getClass()) {
			while (!haydato){
				
				try {
					wait();
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			
			haydato = false;
			notify();
			
			
		}
		return dato;
	}
	*/
}
