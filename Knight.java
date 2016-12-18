import java.util.TreeSet;

public class Knight extends Piece {
	
	public Knight(String team) {
		super(team);
		this.type = "knight";
		this.worth = 3;
	}
	@Override
	public TreeSet<Cpoint> extent(Board b) {
		int cur1 = b.pos(this)[0];
		int cur2 = b.pos(this)[1];
		TreeSet<Cpoint> tgt = new TreeSet<Cpoint>();
		for (int i = cur1 - 2; i <=2 + cur1; i++) {
			for (int j = cur2-2; j <= 2 + cur2; j++) {
				if ((i != cur1) && (j != cur2) && (Math.abs(i - cur1) != Math.abs(j - cur2))) {
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
		
		//contrain knight to "L" shape
		
		if (Math.abs(cur1 - des1) == 1) {
			if (Math.abs(cur2 - des2) == 2) {
				super.move(clicked, b, xcord, ycord);
			}
		} else if (Math.abs(cur1 - des1) == 2) {
			if (Math.abs(cur2 - des2) == 1) {
				super.move(clicked, b, xcord, ycord);
			}
		}
	}
}
