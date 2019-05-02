package Exercise03;

public class Main {
	public static void main(String[] args) {
		
		CubbyHole hole = new CubbyHole();
		Productor productor = new Productor(hole, 1);
		Consumidor consumer = new Consumidor(hole, 3);
		
		productor.start();
		consumer.start();
		
	}
}
