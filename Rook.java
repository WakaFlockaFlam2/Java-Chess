import java.util.TreeSet;

public class Rook extends Piece {
	
	public Rook(String team) {
		super(team);
		this.type = "rook";
		this.worth = 5;
	}
	
	@Override
	public TreeSet<Cpoint> extent(Board b) {
		int cur1 = b.pos(this)[0];
		int cur2 = b.pos(this)[1];
		TreeSet<Cpoint> tgt = new TreeSet<Cpoint>();
		for (int i = cur1 + 1; i < 8; i ++) {
			if (b.state[i][cur2] != null) {
				if (!this.team.equals(b.state[i][cur2].team)) { //opposite teams
					tgt.add(new Cpoint(i, cur2));
				}
				break;
			}
			tgt.add(new Cpoint(i, cur2));
		}
		
		for (int i = cur2 + 1; i < 8; i ++) {
			if (b.state[cur1][i] != null) {
				if (!this.team.equals(b.state[cur1][i].team)) { //opposite teams
					tgt.add(new Cpoint(cur1, i));
				}
				break;
			}
			tgt.add(new Cpoint(cur1, i));
		}
		
		for (int i = cur1 - 1; i >= 0; i --) {
			if (b.state[i][cur2] != null) {
				if (!this.team.equals(b.state[i][cur2].team)) { //opposite teams
					tgt.add(new Cpoint(i, cur2));
				}
				break;
			}
			tgt.add(new Cpoint(i, cur2));
		}
		
		for (int i = cur2 - 1; i >= 0; i --) {
			if (b.state[cur1][i] != null) {
				if (!this.team.equals(b.state[cur1][i].team)) { //opposite teams
					tgt.add(new Cpoint(cur1, i));
				}
				break;
			}
			tgt.add(new Cpoint(cur1, i));
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
		//constrain rook to line movement
		if ((cur1 - des1 != 0) && (cur2 - des2 == 0)) {
			if (cur1 > des1) {
				for (int i = 1; i < cur1 - des1; i++) {
					if (b.state[cur1 - i][cur2] != null) {
						return; //piece in the way
					}
				}
			}
			if (cur1 < des1) {
				for (int i = 1; i < des1 - cur1; i++) {
					if (b.state[cur1 + i][cur2] != null) {
						return; 
					}
				}
			}
			super.move(clicked, b, xcord, ycord);
		} else if ((cur1 - des1 == 0) && (cur2 - des2 != 0)) {
			if (cur2 > des2) {
				for (int i = 1; i < cur2 - des2; i++) {
					if (b.state[cur1][cur2 - i] != null) {
						return;
					}
				}
			}
			if (cur2 < des2) {
				for (int i = 1; i < des2 - cur2; i++) {
					if (b.state[cur1][cur2 + i] != null) {
						return; 
					}
				}
			}
			super.move(clicked, b, xcord, ycord);
		}
	}
}
