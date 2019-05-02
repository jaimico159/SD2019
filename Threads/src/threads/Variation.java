package threads;

public class Variation {
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
			for(int i=1; i<=10; i++) System.out.println("Hilo2: "+ i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		t1.start();
		t2.start();
	}
}
