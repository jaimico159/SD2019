/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

public class Reloj extends Thread{
	private Clock cl = new Clock();
	private Random rand;
	private String name;
	public Reloj(String n){
		cl.setClock(0);
		rand = new Random();
		this.name = n;
	}
	public void run(){

		while(true){
			cl.countClock();
			System.out.println(name + " : " + cl.getClock());
			try {
				Thread.sleep(rand.nextInt(100));
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
	public synchronized void setTime(int t){
		cl.setClock(t);
	}
	public synchronized int getTime(){
		return cl.getClock();
	}
}

