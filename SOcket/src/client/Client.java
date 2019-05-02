package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
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
	}
	
	public static long fibo(int n) {
		if(n == 0) return 1;
		if(n == 1) return 1; 
		
		
		return fibo(n-1) + fibo(n-2);
	}
}
