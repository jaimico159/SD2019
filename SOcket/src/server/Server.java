package server;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		ServerSocket mi_servicio = null;
		String linea_recibida;
		DataInputStream entrada;
		PrintStream salida;
		Socket socket_conectado = null;
		try {
			mi_servicio = new ServerSocket(2019);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		while(true) {
			try {
				socket_conectado = mi_servicio.accept();
				entrada = new DataInputStream(socket_conectado.getInputStream());
				salida = new PrintStream(socket_conectado.getOutputStream());
				linea_recibida = entrada.readLine();
				
				//System.out.println(fibo(10000));
				salida.println("Te reenvio lo que recibo: "+ linea_recibida);
				salida.close();
				entrada.close();
				socket_conectado.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public static long fibo(int n) {
		if(n == 0) return 1;
		if(n == 1) return 1; 
		return fibo(n-1) + fibo(n-2);
	}
}
