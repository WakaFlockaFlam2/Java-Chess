import java.awt.Point;

public class Cpoint extends Point implements Comparable<Cpoint> {
	
	/**
	 * I made this class because I ran into a problem trying to create TreeSet<Point> because for some reason
	 * Java's Point class does not implement the Comparable interface
	 */
	private static final long serialVersionUID = 1L;
	public Cpoint(int x, int y) {
		super(x, y);
	}
	@Override
	public int compareTo(Cpoint pnt) {
		//the integer must be unique on the 8 by 8 domain. I'm no mathematician but the numbers I wrote below seem
		//to be good enough (overkill, tbh) for the given grid size (and the function is asymmetric)
		int unique = 17 * (int) (this.getX() - 13*pnt.getX()) + 3 * (int) (2*this.getX() - pnt.getX());
		return unique;
	}
	@Override
	public boolean equals (Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.getClass() != obj.getClass()) {
			return false;  
		}
		Cpoint pnt = (Cpoint) obj;
		if ((this.getX() == pnt.getX()) && (this.getY() == pnt.getY())) {
			return true;
		}
		return false;
	}
}


