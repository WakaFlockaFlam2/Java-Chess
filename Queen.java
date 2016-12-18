import java.util.TreeSet;

public class Queen extends Piece {
	
	public Queen(String team) {
		super(team);
		this.type = "queen";
		this.worth = 9;
	}
	
	@Override
	public TreeSet<Cpoint> extent(Board b) {
		int cur1 = b.pos(this)[0];
		int cur2 = b.pos(this)[1];
		TreeSet<Cpoint> tgt = new TreeSet<Cpoint>();
		outerloop1:
		for (int i = cur1 + 1; i < 8; i ++) {
			for (int j = cur2 + 1; j < 8; j++) {
				if (Math.abs(cur2 - j) == Math.abs(cur1 - i)) {
					if (b.state[i][j] != null) {
						if (!this.team.equals(b.state[i][j].team)) { //opposite teams
							tgt.add(new Cpoint(i, j));
						}
						break outerloop1;
					}
					tgt.add(new Cpoint(i, j));
				}
			}
		}
		
		outerloop2:
			for (int i = cur1 -1; i >= 0; i--) {
				for (int j = cur2 +1; j < 8; j++) {
					if (Math.abs(cur2 - j) == Math.abs(cur1 - i)) {
						if (b.state[i][j] != null) {
							if (!this.team.equals(b.state[i][j].team)) { //opposite teams
								tgt.add(new Cpoint(i, j));
							}
							break outerloop2;
						}
						tgt.add(new Cpoint(i, j));
					}
				}
			}
		
		outerloop3:
			for (int i = cur1 -1; i >= 0; i--) {
				for (int j = cur2 -1; j >= 0; j--) {
					if (Math.abs(cur2 - j) == Math.abs(cur1 - i)) {
						if (b.state[i][j] != null) {
							if (!this.team.equals(b.state[i][j].team)) { //opposite teams
								tgt.add(new Cpoint(i, j));
							}
							break outerloop3;
						}
						tgt.add(new Cpoint(i, j));
					}
				}
			}
			
		outerloop4:
			for (int i = cur1 +1; i < 8; i ++) {
				for (int j = cur2 -1; j >= 0; j--) {
					if (Math.abs(cur2 - j) == Math.abs(cur1 - i)) {
						if (b.state[i][j] != null) {
							if (!this.team.equals(b.state[i][j].team)) { //opposite teams
								tgt.add(new Cpoint(i, j));
							}
							break outerloop4;
						}
						tgt.add(new Cpoint(i, j));
					}
				}
			}
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
		else if (Math.abs(cur2 - des2) == Math.abs(cur1 - des1)) { //i.e. movement is 45 degrees
			//check to make sure pieces aren't in the way of the chosen diagonal
			if (des1 > cur1) {
				for (int i = 1; i < des1 - cur1; i++) {
					if (des2 > cur2) {
						if (b.state[cur1 + i][cur2 + i] != null) {;
							return; 
						}
					} else {
						if (b.state[cur1 + i][cur2 - i] != null) {;
							return; 
						}
					}
				}
			} else { // i.e. des1 < cur1
				for (int i = 1; i < cur1 - des1; i++) {
					if (des2 > cur2) {
						if (b.state[cur1 - i][cur2 + i] != null) {
							return;
						}
					} else {
						if (b.state[cur1 - i][cur2 - i] != null) {
							return;
						}
					}
				}
			}
			super.move(clicked, b, xcord, ycord);
			
		}
	}
}

