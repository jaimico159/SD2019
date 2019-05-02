package threads;

/*
 * Escriba el código para que dos hilos simulen el proceso de productor consumidor,
 * es decir no hay forma que haya consumo si antes no hubo un productor. Podria
 * utilizarse los números del 1 al 10
 * 
 * */

public class Exercise03 {
	public static void main(String[] args) {
		Thread t1 = new Thread(()-> {
			for(int i=1; i<=10; i++) {
				System.out.println("Hilo1: "+ i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(()-> {
			for(int i=1; i<=10; i++) {
				System.out.println("Hilo2: "+ i);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);
		t1.start();
		t2.start();
	}
}
