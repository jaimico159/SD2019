package Exercise02;

class Consumidor extends Thread { 
	private CubbyHole cubbyhole; 
	private int numero; 
	
	public Consumidor(CubbyHole c, int numero) { 
		cubbyhole = c; 
		this.numero = numero; 
	} 
	
	public void run() { 
		int value = 0; 
		for (int i = 0; i < 10; i++) { 
			value = cubbyhole.get(); 
			System.out.println("Consumidor #"+this.numero+" obtiene: "+value); 
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
} 