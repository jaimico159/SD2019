package threads;

public class Main {
	public static void main(String[] args) {
		Thread t1 = new Thread(()-> {
			for(int i=1; i<=10; i++) System.out.println("Hilo1: "+ i);
		});
		Thread t2 = new Thread(()-> {
			for(int i=1; i<=10; i++) System.out.println("Hilo2: "+ i);
		});
		t1.start();
		t2.start();
	}
}
