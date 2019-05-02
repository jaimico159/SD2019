package modanexo2;

import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;
class Persona extends Thread {
	protected Socket sk;
	protected DataOutputStream dos;
	protected DataInputStream dis;
	private int id;
	public Persona(int id) {
		this.id = id;
	}
	@Override
	public void run() {
		try {
			sk = new Socket("127.0.0.1", 10578);
			dos = new DataOutputStream(sk.getOutputStream());
			dis = new DataInputStream(sk.getInputStream());
		
			String respuesta = fromConsole(dos, dis);
			System.out.println(id + " Servidor devuelve saludo: " + respuesta);
			dis.close();
			dos.close();
			sk.close();
		} catch (IOException ex) {
			Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public String fromConsole(DataOutputStream dos, DataInputStream dis) throws IOException {
		System.out.println("Ingresa saludo");
		System.out.println(id + " envía saludo");
		Scanner sc = new Scanner(System.in);
		dos.writeUTF(sc.nextLine());
		String respuesta="";
		respuesta = dis.readUTF();
		return respuesta;
	}
}