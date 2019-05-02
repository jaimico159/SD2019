import java.lang.Thread;

public class S1 extends Thread{

	public S1(){
		
	}
	public void run(){
		for (int i = 1; i <= 10; i++) {
			System.out.println(i);
			
			
		}
	}
	
	public static void main(String [] args){
		S1 tarea1 = new S1();
		S1 tarea2 = new S1();
		tarea1.start();
		tarea2.start();
	}
}
