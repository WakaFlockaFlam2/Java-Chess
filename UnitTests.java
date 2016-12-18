import static org.junit.Assert.*;

import java.util.TreeSet;

import org.junit.Test;

public class UnitTests {
	
	/*	-------PAWN TESTS-------	*/
	@Test
	public void testInitialPawnMove() {
		Board b = new Board();
		Piece p = b.state[6][7];
		b.state[6][7].move(b.state[5][7], b, 750, 550);
		assertEquals(b.state[5][7], p); //single jump
		b.reset();
		Piece p2 = b.state[6][7];
		b.state[6][7].move(b.state[4][7], b, 750, 450);
		assertEquals(b.state[4][7], p2); //double jump
	}
	@Test
	public void testPawnTake() {
		Board b = new Board();
		Piece p = b.state[6][7];
		b.state[6][7].move(b.state[5][7], b, 750, 550);
		assertEquals(b.state[5][7], p); //single jump
		b.reset();
		Piece p2 = b.state[6][7];
		b.state[6][7].move(b.state[4][7], b, 750, 450);
		assertEquals(b.state[4][7], p2); //double jump
	}
	
	@Test
	public void testPawnIllegalMoves() {
		Board b = new Board();
		Piece[][] oldstate = b.state;
		b.state[6][7].move(b.state[5][7], b, 250, 550);
		b.state[1][3].move(b.state[5][7], b, 250, 550);
		assertEquals(b.state, oldstate);
	}
	
	/*	-------BISHOP TESTS-------	*/
	@Test
	public void testBishopMoves() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),null,new Pawn("w"),null,
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		Piece p = b.state[7][5];
		p.move(p, b, 750, 550);
		assertEquals(b.state[5][7], p);
		
		
	}
	
	@Test
	public void testBishopIllegalMoves() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),null,new Pawn("w"),new Pawn("w"),
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		Piece p = b.state[7][5];
		p.move(p, b, 750, 550);
		assertEquals(b.state[5][7], null);
	}
	
	@Test
	public void testBishopTake() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{new Bishop("w"),null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),null,new Pawn("w"),new Pawn("w"),
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		Piece p = b.state[2][0];
		p.move(p, b, 150, 150);
		assertEquals(b.state[1][1], p);	
		
	}
	
	/*	-------KNIGHT TESTS-------	*/
	@Test
	public void testKnightMoves() {
		Board b = new Board();
		Piece p = b.state[7][6];
		p.move(p, b, 550, 550);
		assertEquals(b.state[5][5], p);
		p.move(p, b, 350, 450);
		assertEquals(b.state[4][3], p);
	}
	
	@Test
	public void testKnightIllegalMoves() {
		Board b = new Board();
		Piece[][] oldstate = b.state;
		Piece p = b.state[7][6];
		p.move(p, b, 550, 100);
		assertEquals(oldstate, b.state);
	}
	
	@Test
	public void testKnightTake() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{new Knight("w"),null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),null,new Pawn("w"),new Pawn("w"),
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		Piece p = b.state[2][0];
		p.move(p, b, 150, 50);
		assertEquals(b.state[0][1], p);
	}
	
	/*	-------ROOK TESTS-------	*/
	@Test
	public void testRookMoves() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{null,null,null,null,null,null,null,null},
				{null,null,null,new Rook("b"),null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		Piece p = b.state[3][3];	
		p.move(p, b, 50, 350);
		assertEquals(b.state[3][0], p);
		p.move(p, b, 50, 450);
		assertEquals(b.state[4][0], p);
	}
	
	@Test
	public void testRookIllegalMoves() {
		Board b = new Board();
		Piece[][] oldstate = b.state;
		Piece p = b.state[7][7];	
		p.move(p, b, 50, 50);
		assertEquals(oldstate, b.state);
	}
	
	@Test
	public void testRookTake() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{null,null,null,null,null,null,null,null},
				{null,null,null,new Rook("b"),null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		Piece p = b.state[3][3];	
		p.move(p, b, 350, 150);
		assertEquals(b.state[1][3], p);
	}
	
	/*	-------KING TESTS-------	*/
	
	@Test
	public void testKingMoves() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{null,null,null,null,null,null,null,null},
				{null,null,null,new Rook("b"),null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),null,new Pawn("w"),new Pawn("w"),
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		Piece p = b.state[7][4];	
		p.move(p, b, 450, 650);
		assertEquals(b.state[6][4], p);
	}
	
	@Test
	public void testKingIllegalMoves() {
		Board b = new Board();
		Piece[][] oldstate = b.state;
		Piece p = b.state[7][4];	
		p.move(p, b, 450, 650);
		assertEquals(oldstate, b.state);
		
	}
	
	@Test
	public void testKingTake() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{null,null,null,null,null,null,null,null},
				{null,null,null,new Rook("b"),null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),null,new Pawn("w"),new Pawn("w"),
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		Piece p = b.state[7][4];	
		p.move(p, b, 450, 650);
		assertEquals(b.state[6][4], p);	
	}
	
	/*	-------QUEEN TESTS-------	*/
	
	@Test
	public void testQueenMoves() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{null,null,null,null,null,null,null,null},
				{null,null,null,new Rook("b"),null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),null,null,new Pawn("w"),new Pawn("w"),
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		Piece p = b.state[7][3];
		p.move(p, b, 350, 450);
		assertEquals(b.state[4][3], p);
		p.move(p, b, 450, 550);
		assertEquals(b.state[5][4], p);
		p.move(p, b, 450, 650);
		assertEquals(b.state[6][4], p);
	}
	
	@Test
	public void testQueenIllegalMoves() {
		Board b = new Board();
		Piece[][] oldstate = b.state;
		Piece p = b.state[7][3];
		p.move(p, b, 350, 450);
		assertEquals(oldstate, b.state);

	}
	
	@Test
	public void testQueenTake() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),null,null,new Pawn("w"),new Pawn("w"),
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		Piece p = b.state[7][3];
		p.move(p, b, 350, 150);
		assertEquals(b.state[1][3], p);	
	}
	
	/*	-------CHECK TESTS-------	*/
	@Test
	public void testSimpleCheck() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),null,new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Rook("w"),new Pawn("w"),new Pawn("w"),
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		assertTrue(b.blackCheck());
	}
	
	@Test
	public void testSlidingCheck() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),null,new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,new Bishop("w"),null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Rook("w"),new Pawn("w"),new Pawn("w"),
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		b.state[3][4].move(b.state[3][4], b, 550, 450);
		assertTrue(b.blackCheck());
	}
	
	
	@Test
	public void testDirectCheck() { //we'll test black this time just to make sure its equal both ways
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,new Knight("b"),null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		assertTrue(b.whiteCheck());	
	}
	
	@Test
	public void testCantMoveIntoCheck() {
		Board b = new Board();
		Piece p = b.state[6][7];
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),null,new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,new Bishop("b"),null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Rook("w"),new Pawn("w"),new Pawn("w"),
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		Piece[][] oldstate = b.state;
		b.state[6][7].move(b.state[4][7], b, 750, 450); //white must move first
		b.state[3][4].move(b.state[3][4], b, 550, 450); //shouldnt go through
		assertEquals(oldstate, b.state);

	}

	
	/*	-------CHECKMATE TESTS-------	*/
	
	@Test
	public void testSimpleCheckmate() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{null,null,null,null,null,null,new Bishop("b"),new King("b")},
				{null,null,null,null,null,null,new Bishop("w"),new Bishop("b")},
				{null,null,null,null,null,null,new Rook("w"),null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				};
		assertTrue(b.blackCheck());
		assertTrue(b.blackCheckmate());
	}
	
	@Test
	public void testFoolsmate() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{new Rook("b"), new Knight("b"),new Bishop("b"),new Queen("b"), new King("b"), new Bishop("b"),
					new Knight("b"), new Rook("b")},
				{new Pawn("b"),new Pawn("b"),new Pawn("b"),new Pawn("b"),null,new Pawn("b"),new Pawn("b"),
						new Pawn("b")},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,new Pawn("b"),null,null,null},
				{null,null,null,null,null,null,new Pawn("w"),new Queen("b")},
				{null,null,null,null,null,new Pawn("w"),null,null},
				{new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),new Pawn("w"),null,null,
					new Pawn("w")},
				{new Rook("w"), new Knight("w"),new Bishop("w"),new Queen("w"), new King("w"), new Bishop("w"),
					new Knight("w"), new Rook("w")}
				};
		assertTrue(b.whiteCheck());
		b.wInCheck = true;
		assertTrue(b.whiteCheckmate());
	}
	
	/*	-------RESET TESTS-------	*/
	@Test
	public void testReset() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{null,null,null,null,null,null,new Bishop("w"),new King("w")},
				{null,null,null,null,null,null,new Bishop("b"),new Bishop("w")},
				{null,null,null,null,null,null,new Rook("b"),null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				};
		b.reset();
		assertTrue(b.state[7][7].type.equals("rook"));
	}
	
	@Test
	public void testResetMoreThanOnce() {
		Board b = new Board();
		b.state = new Piece[][] //set up the board in the standard way
				{
				{null,null,null,null,null,null,new Bishop("w"),new King("w")},
				{null,null,null,null,null,null,new Bishop("b"),new Bishop("w")},
				{null,null,null,null,null,null,new Rook("b"),null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null,null},
				};
		b.reset();
		assertTrue(b.state[7][7].type.equals("rook"));
		b.state[6][7].move(b.state[4][7], b, 750, 450);
		b.reset();
		assertTrue(b.state[6][7].type.equals("pawn"));
	}

}
 