package Exercise01;
import java.awt.*; 
import java.applet.Applet; 

// En este applet se crean dos hilos que incrementan un contador, se 
// proporcionan distintas prioridades a cada uno y se para cuando los // dos coinciden 

public class SchThread extends Applet { 
	Contar alto,bajo; 

	public void init() { 
		// Creamos un thread en 200, ya adelantado bajo = new Contar( 200 ); // El otro comienza desde cero alto = new Contar( 0 ); 
		// Al que comienza en 200 le asignamos prioridad mínima bajo.setPriority( Thread.MIN_PRIORITY ); 
		// Y al otro máxima 
		alto = new Contar(0);
		bajo = new Contar(200);
		alto.setPriority( Thread.MAX_PRIORITY );
		bajo.setPriority(Thread.MIN_PRIORITY);
		System.out.println( "Prioridad alta es "+alto.getPriority() ); 
		System.out.println( "Prioridad baja es "+bajo.getPriority() ); 
	} 


	// Arrancamos los dos threads, y vamos repintando hasta que 
	//el thread que tiene prioridad más alta alcanza o supera al 
	//que tiene prioridad más baja, pero empezó a contar más 
	//alto 

	public void start() { 
		bajo.start(); alto.start(); 
		while( alto.getContar() < bajo.getContar() ) repaint(); 
		repaint(); bajo.stop(); alto.stop(); 
	} 


	// Vamos pintando los incrementos que realizan ambos threads 
	public void paint( Graphics g ) { 
		g.drawString( "bajo = "+bajo.getContar()+ " alto = "+alto.getContar(),10,10 ); 
		System.out.println( "bajo = "+bajo.getContar()+ " alto = "+alto.getContar() ); 
	} 


	// Para parar la ejecución de los threads public void stop() { bajo.stop(); alto.stop(); 
} 

