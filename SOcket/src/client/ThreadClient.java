package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadClient {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		Callable<String> task = () -> {
			Socket cliente = null;
			DataInputStream entrada = null;
			DataOutputStream salida = null;
			
			String ip_servidor = "127.0.0.1";
			
			try {
				cliente = new Socket(ip_servidor, 2019);
				entrada = new DataInputStream(cliente.getInputStream());
				salida = new DataOutputStream(cliente.getOutputStream());
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			
			try {
				String linea_recibida;
				salida.writeBytes("Frase que envío al servidor\n");
				linea_recibida = entrada.readLine();
				System.out.println("SERVIDOR DICE: "+ linea_recibida);
				salida.close();
				entrada.close();
				cliente.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return "TERMINADO";
		};
		
		Runnable runatask = () -> {
			Socket cliente = null;
			DataInputStream entrada = null;
			DataOutputStream salida = null;
			
			String ip_servidor = "127.0.0.1";
			
			try {
				cliente = new Socket(ip_servidor, 2019);
				entrada = new DataInputStream(cliente.getInputStream());
				salida = new DataOutputStream(cliente.getOutputStream());
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			
			try {
				String linea_recibida;
				salida.writeBytes("Frase que envío al servidor\n");
				linea_recibida = entrada.readLine();
				System.out.println("SERVIDOR DICE: "+ linea_recibida);
				salida.close();
				entrada.close();
				cliente.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		};
		List<Callable<String>> tasks = new ArrayList<>();
		for(int i=0;i<2;i++) {
			tasks.add(task);
		}
		
		try {
			List<Future<String>> futures = executor.invokeAll(tasks);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		executor.shutdown();
		
		Thread t1 = new Thread(runatask);
		Thread t2 = new Thread(runatask);
		Thread t3 = new Thread(runatask);
		
		/*t1.start();
		t2.start();
		t3.start();*/
		
	}
	
	public static long fibo(int n) {
		if(n == 0) return 1;
		if(n == 1) return 1; 
		return fibo(n-1) + fibo(n-2);
	}
}
