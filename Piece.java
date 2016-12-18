import java.awt.Point;
import java.util.TreeSet;

public abstract class Piece {
	public String team;
	public String type;
	private String image; //hold the image path
	public int worth;
	public Piece(String team) {
		this.team = team;
	}
	
	public abstract TreeSet<Cpoint> extent(Board b);
	
	public void move(Piece clicked, Board b, int xcord, int ycord) {
		//Common: can't move to own piece, can't take enemy's king
		int[] toDelete = b.pos(this);
		b.state[toDelete[0]][toDelete[1]] = null;
		int[] position = b.pos(xcord, ycord);
		Piece taken = b.state[position[0]][position[1]];
		b.state[position[0]][position[1]] = this;
		
		//check to see if the player moved themself into check and revert their move
		b.whiteCheck();
		b.blackCheck();
		if (b.whiteCheckmate()) {
			System.out.println("White has lost");
		}
		if (b.blackCheckmate()) {
			System.out.println("Black has lost");
		}
		
		
		if (b.isWhiteTurn) {
			if (b.wInCheck) {
				b.state[position[0]][position[1]] = taken;
				b.state[toDelete[0]][toDelete[1]] = this;
				b.wInCheck = b.whiteCheck();
				return;
			}
		} else if (!b.isWhiteTurn){
			if (b.bInCheck) {
				b.state[position[0]][position[1]] = taken;
				b.state[toDelete[0]][toDelete[1]] = this;
				b.bInCheck = b.blackCheck();
				return;
			}
		}
		
		if (this.type.equals("pawn")) {
			if (b.pos(this)[0] == 0 || b.pos(this)[0] == 7) {
				int[] oldPos = b.pos(this);
				b.state[b.pos(this)[0]][b.pos(this)[1]] = new Queen(this.team);
				System.out.println(b.state[oldPos[0]][oldPos[1]].extent(b));
				b.wInCheck = b.whiteCheck();
				b.bInCheck = b.blackCheck();
				System.out.print(b.wInCheck);
				System.out.print(b.bInCheck);
				if (b.whiteCheckmate()) {
					System.out.println("White has lost");
				}
				if (b.blackCheckmate()) {
					System.out.println("Black has lost");
				}
			}
		}
		
		
		if (clicked != null) {
			b.scoreIncrement(clicked);
		}
		
		b.whiteCheck();
		b.blackCheck();
		
		b.isWhiteTurn = ! b.isWhiteTurn;
		b.pieceSelected = false;
		

	}
	
	
}
 