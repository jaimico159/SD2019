import java.lang.Thread;

public class S13 extends Thread{

	public S13(){
		setPriority(Thread.MAX_PRIORITY);
	}
	public void run(){
		for (int i = 1; i <= 10; i++) {
			System.out.println(i);
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
	
	public static void main(String [] args){
		S13 tarea1 = new S13();
		S13 tarea2 = new S13();
		tarea1.start();
		tarea2.start();
	}
}
