import java.util.TreeSet;

public class Pawn extends Piece {
	
	public Pawn(String team) {
		super(team);
		this.type = "pawn";
		this.worth = 1;
	}
	
	@Override
	
	//TODO: edge of the board cases check for nullpointerexceptions
	public TreeSet<Cpoint> extent(Board b) {
		int cur1 = b.pos(this)[0];
		int cur2 = b.pos(this)[1];
		TreeSet<Cpoint> tgt = new TreeSet<Cpoint>();
		if (this.team.equals("w")) {
			try {
			if ((b.state[cur1 - 1][cur2] == null)) {
				tgt.add(new Cpoint(cur1 -1, cur2));
			}
			
			} catch (Exception e) {
				
			}
			if ((cur1 == 6) && (b.state[cur1 - 1][cur2] == null) && (b.state[cur1 - 2][cur2] == null)) {
				tgt.add(new Cpoint(cur1 -2, cur2));
			}
			try{
			if (b.state[cur1 - 1][cur2 -1] != null) {
				if (b.state[cur1 - 1][cur2 -1].team.equals("b")) {
					tgt.add(new Cpoint(cur1 -1, cur2-1));
				}
			}
			}
			catch (Exception e) {}
			try{
			if (b.state[cur1 - 1][cur2+1] != null) {
				if (b.state[cur1 - 1][cur2 +1].team.equals("b")) {
					tgt.add(new Cpoint(cur1 -1, cur2+1));
				}
			}
			}
			catch (Exception e) {
				
			}
		} else { //piece is black
			try {
			if (b.state[cur1 +1][cur2] == null) {
				tgt.add(new Cpoint(cur1 +1, cur2));
			}
			} catch (Exception e) {}
			if ((cur1 == 1) && (b.state[cur1 + 1][cur2] == null) && (b.state[cur1 + 2][cur2] == null)) {
				tgt.add(new Cpoint(cur1 + 2, cur2));
			}
			try {
			if (b.state[cur1 + 1][cur2 + 1] != null) {
				if (b.state[cur1 + 1][cur2 +1].team.equals("w")) {
					tgt.add(new Cpoint(cur1 +1, cur2+1));
				}
			}
			}
			catch (Exception e) {}
			try {
			if (b.state[cur1 + 1][cur2 - 1] != null) {
				if (b.state[cur1 + 1][cur2 -1].team.equals("w")) {
					tgt.add(new Cpoint(cur1 + 1, cur2 - 1));
				}
			}
			}
			catch (Exception e) {}
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

		if (cur2 - des2 == 0) { //constrain to same line
			if (this.team.equals("w")) { //constrain white to only move upboard
				if ((cur1 - des1 == 1) && (b.state[des1][des2] == null)) {
					super.move(clicked, b, xcord, ycord);
				}
				else if ((cur1 - des1 == 2) && (cur1 == 6) && (b.state[5][cur2] == null) 
						&& (b.state[des1][des2] == null)) { //allow 2 moves up when at starting line
					super.move(clicked, b, xcord, ycord);
				}
				
			}
			else if (this.team.equals("b")) { //constrain white to only move upboard
				if ((cur1 - des1 == -1) && (b.state[des1][des2] == null)) {
					super.move(clicked, b, xcord, ycord);
				}
				else if ((cur1 - des1 == -2) && (cur1 == 1) && (b.state[2][cur2] == null)
						&& (b.state[des1][des2] == null)) { //allow 2 moves up when at starting line
					super.move(clicked, b, xcord, ycord);
				}
				
			}
		}
		//let pawns move digonally to take other pieces
		else if (((cur2 - des2 == 1) || (cur2 - des2 == -1)) && b.state[des1][des2] != null) {
			if (this.team.equals("w")) { //constrain white to only move upboard
				if ((cur1 - des1 == 1) && (b.state[des1][des2].team.equals("b"))){
					super.move(clicked, b, xcord, ycord);
				}
			}
			else if (this.team.equals("b")) { //constrain white to only move upboard
				if ((cur1 - des1 == -1) && (b.state[des1][des2].team.equals("w"))) {
					super.move(clicked, b, xcord, ycord);
				}
			}
		}
		//TODO: program En Passant here
		
	}
}
      