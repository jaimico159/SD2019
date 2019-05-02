/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


public class Sincronizador {
       private int reloj;
//INICIALIZAR RELOJ
	public Sincronizador() {
		reloj = 0;
	}
//SINCRONIZAR RELOJ
	public synchronized void setContador(int c) {
		this.reloj = c;
	}
// OBTENER RELOJ
	public synchronized int getContador() {
		return this.reloj;
	}
// INCREMENTO DE RELOJ SIMULACION
	public synchronized void contar() {
		this.reloj++;
	}

    
}
