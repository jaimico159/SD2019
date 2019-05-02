import java.lang.Thread;

public class S12  extends Thread{

	public S12(){
		
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
		S12 tarea1 = new S12();
		S12 tarea2 = new S12();
		tarea1.start();
		tarea2.start();
	}
}
