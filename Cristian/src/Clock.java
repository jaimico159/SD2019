/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
public class Clock {
private int clock;

	public Clock(){
		clock = 0;
	}

	public synchronized void setClock(int c){
		this.clock = c;
	}

	public synchronized int getClock(){
		return this.clock;
	}

	public synchronized void countClock(){
		this.clock++;
	}
}
