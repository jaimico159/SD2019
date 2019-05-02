package prodconsu;

public class ProCons {

	public static void main(String[] args) {
		Contenedor c = new Contenedor();
		Productor p1 = new Productor(c);
		Consumidor c1 = new Consumidor(c);

		p1.start();
		c1.start();
	}

}
