import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Board {
	protected Piece[][] state;
	public boolean isWhiteTurn;
	public boolean pieceSelected;
	public Piece currentPiece;
	public int bscore;
	public int wscore;
	public boolean bInCheck;
	public boolean wInCheck;
	public boolean wLost;
	public boolean bLost;
	public Board() {
		isWhiteTurn = true;
		pieceSelected = false;
		currentPiece = null;
		bscore = 0;
		wscore = 0;
		bInCheck = false;
		wInCheck = false;
		wLost = false;
		bLost = false;
		state = new Piece[][] //set up the board in the standard way
			{
			{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
				new Knight("b"), new Rook("b")},
			{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),
					new Pawn("b")},
			{null,null,null,null,null,null,null,null},
			{null,null,null,null,null,null,null,null},
			{null,null,null,null,null,null,null,null},
			{null,null,null,null,null,null,null,null},
			{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),
				new Pawn("w")},
			{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
				new Knight("w"), new Rook("w")}
			};

	}
	public void redrawBoard(Graphics g)  throws NullPointerException{
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				BufferedImage image = null;
				if (state[i][j] != null) {
				String imgPath = (state[i][j].team) + "_" + (state[i][j].type) + ".png";
					try {
						image = ImageIO.read(new File(imgPath));
						
					} catch (IOException e) {
						System.out.println("Images are missing from the file path");
					}
				}
				if (image != null) {
					g.drawImage(image, 100*j+ 5, 100*i + 5, 90, 90, null);
					
				}
				if (pieceSelected) {
					g.setColor(Color.GREEN);
					g.drawRect(pos(currentPiece)[1]*100, pos(currentPiece)[0]*100, 100, 100);
				}
			}
		}
		
		
		
	}
	//NOTE: this functions doesn't work for null piece inputs
	public int[] pos(Piece p) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (state[i][j] == p) {
					return new int[]{i, j};
				}
			}
		}
		System.out.println("pos function returned null");
		return null;
	}
	
	public int[] pos(int xcord, int ycord) {
		int d1 = (int) Math.floor((double) ycord / 100.0);
		int d2 = (int) Math.floor((double) xcord / 100.0);
		return new int[]{d1, d2};
	}
	//this function checks if black is in check
	public boolean blackCheck () {
		for (int i =0; i < 8; i ++) {
			for (int j = 0; j < 8; j ++) {
				if (this.state[i][j] != null) {
					if (state[i][j].team.equals("w")) {
						for (Cpoint point : state[i][j].extent(this)) {
							try {
								if (state[point.x][point.y].team.equals("b") 
										&& state[point.x][point.y].type.equals("king")) {
									this.bInCheck = true;
									return true;
								} 
							} catch (Exception e) {} //this could be a nullpointer or arrayindexoutofbounds
						}
					}
				}
			}
		}
		this.bInCheck = false;
		return false;
		
		//now for checkmate...
		
	}
	
	public boolean blackCheckmate () {
		if (this.bInCheck) {
			for (int i =0; i < 8; i ++) {
				for (int j = 0; j < 8; j ++) {
					if (this.state[i][j] != null) {
						if (state[i][j].team.equals("b")) {
							for (Cpoint point : state[i][j].extent(this)) {
								Piece old = state[i][j];
								Piece killed = state[point.x][point.y];
								state[i][j] = null;
								state[point.x][point.y] = old;
								if (!blackCheck()) {
									state[i][j] = old;
									state[point.x][point.y] = killed;
									this.bInCheck = true;
									bLost = false;
									return false;
								} else {
									state[i][j] = old;
									state[point.x][point.y] = killed;
									this.bInCheck = true;
								}
							}
						}
					}	
				}
			}
		bLost = true; //this is reachable only if for every black piece there is no possible move to get out of check
		return true;
		}
		bLost = false;
		return false;
	}
	
	public boolean whiteCheck () {
		for (int i =0; i < 8; i ++) {
			for (int j = 0; j < 8; j ++) {
				if (this.state[i][j] != null) {
					if (state[i][j].team.equals("b")) {
						for (Cpoint point : state[i][j].extent(this)) {
							try {
								if (state[point.x][point.y].team.equals("w") 
										&& state[point.x][point.y].type.equals("king")) {
									this.wInCheck = true;
									return true;
								} 
							} catch (Exception e) {} //this could be a nullpointer or arrayindexoutofbounds
						}
					}
				}
			}
		}
		this.wInCheck = false;
		return false;
	}
	
	public boolean whiteCheckmate () {
		if (this.wInCheck) {
			for (int i =0; i < 8; i ++) {
				for (int j = 0; j < 8; j ++) {
					if (this.state[i][j] != null) {
						if (state[i][j].team.equals("w")) {
							for (Cpoint point : state[i][j].extent(this)) {
								Piece old = state[i][j];
								Piece killed = state[point.x][point.y];
								state[i][j] = null;
								state[point.x][point.y] = old;
								if (!whiteCheck()) {
									state[i][j] = old;
									state[point.x][point.y] = killed;
									this.wInCheck = true;
									wLost = false;
									return false;
								} else {
									state[i][j] = old;
									state[point.x][point.y] = killed;
									this.wInCheck = true;
								}
							}
						}
					}	
				}
			}
		wLost = true; //this is reachable only if for every white piece there is no possible move to get out of check
		return true;
		}
		wLost = false;
		return false;
	}
	
	public Piece getPiece(int xcord, int ycord) {
		if (xcord > 800 || ycord > 800) {
			return null;
		} else {
			  int d1 = (int) Math.floor((double) ycord / 100.0);
			  int d2 = (int) Math.floor((double) xcord / 100.0);
			  return state[d1][d2];
		}
	}
	
	public void removePiece (Piece p) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (state[i][j] == p) {
					state[i][j] = null;
				}
			}
		}
	}
	
	public boolean isOppositeTeam (Piece p) {
		if (isWhiteTurn) {
			return p.team.equals("b");
		} else {
			return p.team.equals("w");
		}
	}
	public void scoreIncrement(Piece clicked) {
		if (isWhiteTurn) {
			wscore += clicked.worth;
		} else {
			bscore += clicked.worth;
		}
	}
	public void handleEvent(int xcord, int ycord) {
		Piece clicked = this.getPiece(xcord, ycord);
		if (pieceSelected == true) {
			if (clicked == null) {
				//then move the selected piece to the new position
				currentPiece.move(clicked, this, xcord, ycord);
				return;
			}
			else if (isOppositeTeam(clicked)) {
				//then check if the piece is in the extent of the currentPiece - if so
				//then remove the other piece from the board, if not then do nothing
				//now remove red box around the old piece if the move succeeded
				currentPiece.move(clicked, this, xcord, ycord);
				return;
			}
			else if (!isOppositeTeam(clicked)) {
				//then the user has clicked another of their own pieces. we must check for one special
				//case (casteling). If not a castle then change the currentPiece to the clicked pience and do nothing
				currentPiece = clicked;
				return;
			}
		} else { //i.e. there was no piece selected
			if (clicked == null) {
				return; //it doesnt make sense to try and move a null space
			}
			if (isWhiteTurn) {
				if (clicked.team.equals("w")) {
					pieceSelected = true;
					currentPiece = clicked;
				} else {
					return; // if the user clicks on the opposite team's piece we do nothing
				}
			} else { //it's black's turn
				if (clicked.team.equals("b")) {
					pieceSelected = true;
					currentPiece = clicked;
				} else {
					return; // if the user clicks on the opposite team's piece we do nothing
				}
			}
		}
	}
	
	public void reset() {
		state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		isWhiteTurn = true;
		pieceSelected = false;
		currentPiece = null;
		bscore = 0;
		wscore = 0;
		bInCheck = false;
		wInCheck = false;
		wLost = false;
		bLost = false;
		
	}
	
}
