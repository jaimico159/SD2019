/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;


public class UtcClock extends Thread {
	Clock cl = new Clock();
	Random rand = new Random();

	public UtcClock() {
		cl.setClock(0);
	}

	public void run() {
		Reloj rn = new Reloj("Sujeto 1");
		Reloj rn1 = new Reloj("Sujeto 2");
		Reloj r = new Reloj("TiempoProm");
		int num = 0;
		r.start();
		rn.start();
		rn1.start();
		while (num < 2) {
			cl.countClock();
			int x = rand.nextInt(4);
			if (x == 2) {
				try {
					rn.sleep(300);
					rn1.sleep(300);
					r.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// actualizacion de relojes
				int temp =r.getTime();
				System.out.println("Reloj final actualizado a :  " +temp);
				rn.setTime(temp);
				rn1.setTime(temp);
				rn.resume();
				rn1.resume();
				r.resume();
				num++;
			}
		}
		rn.stop();
		rn1.stop();
		r.stop();
	}
}

