package Exercise02;

class CubbyHole { 
	private int contents; 
	
	public int get() {
		int last = this.contents;
		this.contents = 0;
		return last; 
	} 

	public void put(int value) { 
		contents = value; 
		
	} 
} 