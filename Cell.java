
public class Cell {
	public int i;
	public int k;
	
	private Cell left;
	private Cell right;
	private Cell up;
	private Cell down;
	
	private int set;
	
	Cell ( int i , int k ) {
		this.i = i;
		this.k = k;
		set = -1;
	}
	
	public void connect(String dir , Cell neighbour ) {
		if ( dir.charAt(0) == 'l' ) {
			left = neighbour;
			neighbour.right = this;
		}
		if ( dir.charAt(0) == 'r' ) {
			right = neighbour;
			neighbour.left = this;
		}
		if ( dir.charAt(0) == 'u' ) {
			up = neighbour;
			neighbour.down = this;
		}
		if ( dir.charAt(0) == 'd' ) {
			down = neighbour;
			neighbour.up = this;
		}
	}
	public void connect(int dir , Cell neighbour ) {
		if ( dir == 0 ) {
			connect("up",neighbour);
		}
		if ( dir == 1 ) {
			connect("down",neighbour);
		}
		if ( dir == 2 ) {
			connect("left",neighbour);
		}
		if ( dir == 3 ) {
			connect("right",neighbour);
		}
	}
	
	public void setSet( int set ) { this.set = set; }
	public int getSet() { return this.set; }
	

	public String toString( int t ) {
		String res = "";
		if ( t == 0 ) {
			res += "R"+(right==null?"W":"B");
		}
		else {
			res += (down==null?"W":"B")+"W";
		}
		return res;
	}
}