import java.util.TreeSet;

public class King extends Piece {
	
	public King(String team) {
		super(team);
		this.type = "king";
		//king has no worth
	}
	
	@Override
	public TreeSet<Cpoint> extent(Board b) {
		int cur1 = b.pos(this)[0];
		int cur2 = b.pos(this)[1];
		TreeSet<Cpoint> tgt = new TreeSet<Cpoint>();
		for (int i = cur1 - 1; i <=1 + cur1; i++) {
			for (int j = cur2-1; j <= 1 + cur2; j++) {
				if ((i != cur1) || (j != cur2)) {
					try {
						if (b.state[i][j] != null) {
							if (!this.team.equals(b.state[i][j].team)) {
								tgt.add(new Cpoint(i, j));
							}
						} else if (b.state[i][j] == null) {
							tgt.add(new Cpoint(i, j));
						}
					} catch (Exception e) {} //out of bounds
				}
			}
		}
		return tgt;
	}
	
	@Override
	public void move(Piece clicked, Board b, int xcord, int ycord) {
		this.extent(b);
		int des1 = (int) Math.floor((double) ycord / 100.0);
		int des2 = (int) Math.floor((double) xcord / 100.0);
		int cur1 = b.pos(this)[0];
		int cur2 = b.pos(this)[1];
		//TODO: don't let the king move into check(mate)
		if ((Math.abs(cur1 - des1) == 1) ||  (Math.abs(cur1 - des1) == 0)) {
			if ((Math.abs(cur2 - des2) == 1) || (Math.abs(cur2 - des2) == 0)) {
				super.move(clicked, b, xcord, ycord);
			}
		} 
	}
		
}
