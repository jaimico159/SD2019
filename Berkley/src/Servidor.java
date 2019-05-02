
import java.util.*;

public class Servidor extends Thread{
    private Sincronizador cl = new Sincronizador();
	private Random rand = new Random();
	
	Servidor(){
		cl.setContador(0);
	}
	
	public void run(){
		Cliente cliente1 = new Cliente("Cliente 1");
		Cliente cliente2 = new Cliente("Cliente 2");
		
		int num = 0;
		
		cliente1.start();
		cliente2.start();
		
		
		while(num<2){
			cl.contar();
			int x = rand.nextInt(4);
			if(x == 2){
				try {
					cliente1.sleep(500);
					cliente2.sleep(500);
					
                                        
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				int tiempos[] = new int[2];
				int men,c=1;
				tiempos[0] = cliente1.getTime();
				men = tiempos[0];
				tiempos[1] = cliente2.getTime();
				
				
				for(int i=1;i<2;i++){
					int myT = cl.getContador();
					if(tiempos[i] < myT+2  && tiempos[i] > myT-2){
						men+=tiempos[i];c++;
					}
				}
				int prom = (int)(men/c);
				System.out.println("Cliente 1 : "+tiempos[0]+"  Cliente 2 : "+tiempos[1] );
				System.out.println("Promedio = "+prom);
				cliente1.setTime(prom);
				cliente2.setTime(prom);
				
				
				cliente1.resume();
				cliente2.resume();
				
				
				num++;
			}
		}
		cliente1.detener();
		cliente2.detener();
		
	}

    
}
