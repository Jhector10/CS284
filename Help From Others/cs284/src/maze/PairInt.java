package maze;
/*
 * "I pledge my Honor that I have abided by the Stevens Honor System."
 * -Simrun Heir
 */
public class PairInt {
	//encodes pairs of integers that represent coordinates
	
	//data fields
	private int x;
	private int y;
	
	//constructor
	PairInt(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	//methods
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Object p) {
		return p.equals(new PairInt(this.x, this.y));
	}
	/**
	 * returns a new copy of a coordinate
	 * @return
	 */
	public PairInt copy() {
		PairInt c = new PairInt(x,y);
		return c;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append('(');
		result.append(getX());
		result.append(',');
		result.append(getY());
		result.append(')');
		return result.toString();
	}
}
