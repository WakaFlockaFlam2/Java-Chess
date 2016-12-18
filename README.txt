=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: jbecke
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an approprate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2d arrays: The Board has a 2D array, state[][], of Piece objects. In positions where
	there is a piece, an object that is a subclass of piece is provided (e.g. a Queen).
	The absence of a piece is represented by holding a null. The array is 8 by 8, holding
	32 pieces and 32 nulls at the beginning of the game. For each piece removed, there is
	one less piece and one more null element.

  2. Collections (NOTE: I substituted this for I.O.). To determine whether a player is in check
  	 I iterate over all the enemy piece's and check their extent(), which returns a TreeSet<Cpoint>
  	 that holds all the possible spaces they could move. If the current player turn's king is within
  	 the extent then he is in check. For example, if it is white's turn we iterate over all the
  	 black pieces and call their extent function. Nested in this, we iterate over all the Cpoints
  	 in the TreeSet returned by extent(). If we find that white's king's square's (x, y) value
  	 corresponds to the Cpoint then white is in check. Why a TreeSet? 1) It has efficient lookup
  	 times which is important because we are iterating every turn. 2) We only want to hold each
  	 square once so we do not want duplicates. Cpoint is a class I made that extends the java Point
  	 class and implements the Comparable<Cpoint> interface so that it can be held in a collection
  	 (which for some reason unknown to me, java Point does not implement).

  3. Inheritance/Subtyping for Dynamic Dispatch: 
  	Since Piece is an abstract class the state[][] won’t hold any Piece objects.
	Instead, a subtype of Piece corresponding to King, Queen, Rook, Bishop, Knight, or
	Pawn class is given. Dynamic dispatch comes in when a Board object calls the move()
	method of a Piece subtype. Each subtype must have it’s own move() method
	corresponding to chess’s constraints on different types of pieces. Which move() to call
	is determined at run-time, hence dynamic dispatch. The same is true of the extent() function.

  4. Testable Component: I implemented JUnit testing as per the TA feedback on my proposal. I 
  	 made sure that each Piece subclass was constrained properly (i.e. can make moves that it
  	 should be able to, can't make moves it shouldn't be able to). I checked how the pieces interact
  	 with each other, such as checking that they can take one another and that they can be removed
  	 from the board. I tested that the board has the fields that it is supposed to have filled. I
  	 tested the reset, check, and check mate functions of the Board class. I ensured that white
  	 and black have identical privileges, save the fact that white must go first. As an aside,
  	 I found that JUnit testing wasn't all that helpful because it was so easy for me to just launch
  	 the GUI and test what I had in mind, rather than writing a new test every time. However, for
  	 the sake of having well-documented and readable test cases, I have gone back and added
  	 tests for everything that I tested using the GUI.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

Piece is an abstract class that is the superclass of the different types of pieces. It has fields
type, team, and worth, an unimplemented method extent, and a implemented method move(). The move()
method of Piece is called by its subclasses after the subclasses have ensured that the attempted
move was within the constraints of how it is allowed to move. The extent method returns a TreeSet
of Cpoints (a new class I added which I'll discuss in the next question) corresponding to squares 
where the piece is allowed to move.

Board handles the back end. It has a 2D array of size 8 by 8 corresponding to the chess board. It
holds subclasses of Piece or null if there is no Piece in the square. When GameCourt (yes, I still
use GameCourt for the visual swing stuff and Board for the back end) detects a mouseclick, the
location is routed to the Board instance which calls the appropriate Piece's move() method if a 
Piece has been selected, or selects a piece if the player cicks on their own piece, or
doesn't do anything if the given square is null or if they clicked an enemy piece without having
selected their own piece first. Board handles reset, check, and checkmate.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
Yes- I was surprised to find out that the built-in java Point class doesn't implement Comparable,
and so can't be put in a TreeSet. I had to create a subclass of Point called Cpoint to implement
Comparable<Cpoint> and the compareTo function. For the compareTo function, I initially was returning
the difference between the getX values. This meant that if the square I was moving to had the same
x-value, a 0 was returned and so the square wasn't added to the extent(). This took time to debug.
I fixed this by making the compareTo function use both x and y, be asymmetric, and use large
coefficients.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

Perhaps I could have created a subclass of Piece called None which represented an empty space,
instead of using nulls. In the end, nulls worked fine, but NullPointerExceptions caused me many
headaches. In addition, I could refactor the move() methods of the Piece subclasses; instead of
doing all the boolean logic themselves, they could check to see whether the attempted move fell
within its extent(). This would be less efficient because all possible moves for that piece would
need to be checked (instead of just one), but it would make the code cleaner. I could also refactor
GameCourt by merging it with Board, though I kind of like how GameCourt handles all the Swing stuff
and Board is just the back end. All these design choices stem from the fact that while I had a plan
in mind before I started coding, my actual implementation differed and was continuously revised
and refactored as I was coding.

Also, I'm sure there must be some way to more efficiently detect check and checkmate rather than
checking the extent of all the pieces every turn. Something like only iterating over the extents
that could have changed and seeing if that put the enemy into check would be shorter. To make an
analogy, like how git will just update changes to a file rather than overwrite the whole file.


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.

I got the icons for the pieces from some free-license image site that I can't remember the name
of. I then edited and resized them in paint.net. All the code is my own. I didn't watch tutorials.
I used Math for Math.abs().

