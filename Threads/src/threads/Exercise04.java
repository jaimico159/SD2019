package threads;

/*
 * Modificar el código anterior para que los hilos puedan jugar al lanzamiento de datos
 * 10 lanzamientos despues de lo cual debe haber un ganador
 * En los grupos de 5: Reescribir los códigos de las aplicaciones de hilos realizados
 * en Java en C de Linux
 */

import java.util.concurrent.atomic.AtomicInteger;

public class Exercise04 {
	public static void main(String[] args) {
		Integer stock = new Integer(0);
		Runnable task = () -> {
			while(true) {
				synchronized(stock) {
					
				}
			}
		};
		
		Runnable producer = () -> {
			while(true) {
				if(stock.get() == 0) {
					stock.set(10);
					System.out.println("Charged to 10");
					Thread.currentThread().notifyAll();
				}
				try {
					Thread.currentThread().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Runnable consumer = () -> {
			while(true) {
				if (stock.decrementAndGet() > 0) {
					System.out.println("Consumes 1 resource");
				} else {
					try {
						Thread.currentThread().notifyAll();
						Thread.currentThread().wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		};
		
		Thread t1 = new Thread(producer);
		Thread t2 = new Thread(consumer);
		
		t1.start();
		t2.start();
		
		
	}
}
