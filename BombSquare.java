import java.util.*;

public class BombSquare extends GameSquare
{
	private boolean thisSquareHasBomb = false; // TO CHECK IF THE CURRENT SQUARE(S) HAVE BOMB OR NOT.
	public static final int MINE_PROBABILITY = 10;
	public boolean opened; // I MADE THIS BOOLEAN VARIABLE TO CHECK IF THE SQUARE IS OPENED/REVEALED OR NOT.

	/**
	 * Getter method that sees if the square has bomb.
	 * @return Returns whether the square has bomb or not.
	 */
	public boolean isThisSquareHasBomb() { // THIS IS JUST A GETTER TO SEE IF THIS SQUARE HAS BOMBS.
		return thisSquareHasBomb;
	}

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png", board);
		Random r = new Random();
		thisSquareHasBomb = (r.nextInt(MINE_PROBABILITY) == 0);
	}

	/**
	 * This method looks, if the current square has a bomb or not.  If so, reveal the bomb, or call the "empty_expansion" method.
	 */
	public void clicked()
	{
		if (thisSquareHasBomb == true){
			opened = true; // MARKING THE SQUARE IS OPENED.
			setImage("images/bomb.png"); // SETTING THE IMAGE OF THE SQUARE TO A BOMB.
		}
		if (opened == true){
			return; // THIS STOPS THE PROCESS, IF THE SQUARE HAS BEEN PREVIOUSLY OPENED.
		}
		else if (thisSquareHasBomb == false) { // THIS IS A CHECK FOR IF THE SQUARE DOES NOT HAVE ANY BOMB.
			opened = true; // MARKING THE SQUARE IS OPENED.
			int bombs_number = count_bombs(xLocation, yLocation); // THIS CALCULATES THE NUMBER OF BOMBS IN THE SURROUNDING SQUARES.
			
			if (bombs_number > 0){
				setImage("images/"+bombs_number+".png");
			} else if (bombs_number == 0) {
				empty_expansion(); // CALLING THE "empty_expansion()" FUNCTION WHENEVER A SQUARE WITH NO BOMBS IS CLICKED.  THIS IS A KIND OF INDIRECT RECURSION AS "empty_expansion" CALLS CLICKED, WHICH CALLS THE METHOD.
			}
		}


	}

	/**
	 *  HERE, I HAVE INDIVIDUALLY CHECKED FOR EVERY POSSIBLE 8 DIRECTIONS FROM A SQUARE: TOP(X,Y-1), TOP-LEFT(X-1,Y-1), TOP-RIGHT(X+1,Y-1), LEFT(X-1,Y),
	 * 	BOTTOM-LEFT(X-1,Y+1), BOTTOM(X,Y+1), BOTTOM-RIGHT(X+1,Y+1), RIGHT(X+1,Y)
	 * @param x_coordinate This is the X-coordinate of the square.
	 * @param y_coordinate This is the Y-coordinate of the square.
	 * @return Returns the total number of bombs.
	 */
	/*
	JUST AS THE NAME SUGGESTS, THIS FUNCTION COUNTS THE NUMBER OF BOMBS IN THE SURROUNDING SQUARES.
	HERE, I HAVE INDIVIDUALLY CHECKED FOR EVERY POSSIBLE 8 DIRECTIONS FROM A SQUARE: TOP(X,Y-1), TOP-LEFT(X-1,Y-1), TOP-RIGHT(X+1,Y-1), LEFT(X-1,Y),
	BOTTOM-LEFT(X-1,Y+1), BOTTOM(X,Y+1), BOTTOM-RIGHT(X+1,Y+1), RIGHT(X+1,Y)
	 */
	public int count_bombs(int x_coordinate, int y_coordinate){
		int count_bombs = 0; // CREATING A INT VARIABLE THAT COUNTS THE NUMBER OF BOMBS.
		if (board.getSquareAt(x_coordinate, y_coordinate-1) != null){ // TOP(X,Y-1)
			BombSquare surrounding_squares = (BombSquare)board.getSquareAt(x_coordinate, y_coordinate-1); // GETTING THE SQUARE TOP OF THE CURRENT SQUARE, AND MAKING IT A BOMB SQUARE.
			if (surrounding_squares.isThisSquareHasBomb()){ // CHECKS IF THE SURROUNDING SQUARE HAS BOMB.
				count_bombs++; // IF SO, INCREMENTING THE COUNTER.
			}
		}
		if (board.getSquareAt(x_coordinate, y_coordinate+1) != null){ // BOTTOM(X,Y+1)
			BombSquare surrounding_squares = (BombSquare)board.getSquareAt(x_coordinate, y_coordinate+1); // GETTING THE SQUARE BELOW OF THE CURRENT SQUARE, AND MAKING IT A BOMB SQUARE.
			if (surrounding_squares.isThisSquareHasBomb()){ // CHECKS IF THE SURROUNDING SQUARE HAS BOMB.
				count_bombs++; // IF SO, INCREMENTING THE COUNTER.
			}
		}
		if (board.getSquareAt(x_coordinate-1, y_coordinate) != null){ // LEFT(X-1,Y)
			BombSquare surrounding_squares = (BombSquare)board.getSquareAt(x_coordinate-1, y_coordinate); // GETTING THE SQUARE LEFT OF THE CURRENT SQUARE, AND MAKING IT A BOMB SQUARE.
			if (surrounding_squares.isThisSquareHasBomb()){ // CHECKS IF THE SURROUNDING SQUARE HAS BOMB.
				count_bombs++; // IF SO, INCREMENTING THE COUNTER.
			}
		}
		if (board.getSquareAt(x_coordinate+1, y_coordinate) != null){ // RIGHT(X+1,Y)
			BombSquare surrounding_squares = (BombSquare)board.getSquareAt(x_coordinate+1, y_coordinate); // GETTING THE SQUARE RIGHT OF THE CURRENT SQUARE, AND MAKING IT A BOMB SQUARE.
			if (surrounding_squares.isThisSquareHasBomb()){ // CHECKS IF THE SURROUNDING SQUARE HAS BOMB.
				count_bombs++; // IF SO, INCREMENTING THE COUNTER.
			}
		}
		if (board.getSquareAt(x_coordinate-1, y_coordinate-1) != null){ // TOP-LEFT(X-1,Y-1)
			BombSquare surrounding_squares = (BombSquare)board.getSquareAt(x_coordinate-1, y_coordinate-1); // GETTING THE SQUARE TOP-LEFT OF THE CURRENT SQUARE, AND MAKING IT A BOMB SQUARE.
			if (surrounding_squares.isThisSquareHasBomb()){ // CHECKS IF THE SURROUNDING SQUARE HAS BOMB.
				count_bombs++; // IF SO, INCREMENTING THE COUNTER.
			}
		}
		if (board.getSquareAt(x_coordinate-1, y_coordinate+1) != null){ // BOTTOM-LEFT(X-1,Y+1)
			BombSquare surrounding_squares = (BombSquare)board.getSquareAt(x_coordinate-1, y_coordinate+1); // GETTING THE SQUARE DOWN-LEFT OF THE CURRENT SQUARE, AND MAKING IT A BOMB SQUARE.
			if (surrounding_squares.isThisSquareHasBomb()){ // CHECKS IF THE SURROUNDING SQUARE HAS BOMB.
				count_bombs++; // IF SO, INCREMENTING THE COUNTER.
			}
		}
		if (board.getSquareAt(x_coordinate+1, y_coordinate-1) != null){ // TOP-RIGHT(X+1,Y-1)
			BombSquare surrounding_squares = (BombSquare)board.getSquareAt(x_coordinate+1, y_coordinate-1); // GETTING THE SQUARE TOP-RIGHT OF THE CURRENT SQUARE, AND MAKING IT A BOMB SQUARE.
			if (surrounding_squares.isThisSquareHasBomb()){ // CHECKS IF THE SURROUNDING SQUARE HAS BOMB.
				count_bombs++; // IF SO, INCREMENTING THE COUNTER.
			}
		}
		if (board.getSquareAt(x_coordinate+1, y_coordinate+1) != null){ // BOTTOM-RIGHT(X+1,Y+1)
			BombSquare surrounding_squares = (BombSquare)board.getSquareAt(x_coordinate+1, y_coordinate+1); // GETTING THE SQUARE DOWN-RIGHT OF THE CURRENT SQUARE, AND MAKING IT A BOMB SQUARE.
			if (surrounding_squares.isThisSquareHasBomb()){ // CHECKS IF THE SURROUNDING SQUARE HAS BOMB.
				count_bombs++; // IF SO, INCREMENTING THE COUNTER.
			}
		}
		return count_bombs; // HERE, I AFTER COUNTING THE NUMBER OF BOMB SQUARE, I AM RETURNING IT.

	}

	/**
	 * This method is responsible to expand the empty space when an empty square is clicked.
	 */
	/*
	JUST AS THE NAME OF THE METHOD SUGGESTS, THIS FUNCTION EXPANDS THE EMPTY SPACE, WHEN AN EMPTY SQUARE IS CLICKED.
	 */
	public void empty_expansion(){
		setImage("images/0.png");
		for (int x = -1; x <= 1; x++){ // HERE, I AM LOOPING THROUGH THE SURROUNDING HORIZONTAL SQUARES.
			for (int y = -1; y <= 1; y++){ // HERE, I AM LOOPING THROUGH THE SURROUNDING VERTICAL SQUARES.
				int travel_x = xLocation+x; // KEEPING A CHECK OF X-COORDINATE OF THE SURROUNDING SQUARES.
				int travel_y = yLocation+y; // KEEPING A CHECK OF Y-COORDINATE OF THE SURROUNDING SQUARES.
				if (board.getSquareAt(travel_x, travel_y) instanceof BombSquare){ // THIS IF CONDITION CHECKS IF THE SURROUNDING SQUARE IS A BOMB SQUARE.
					BombSquare empty_square = (BombSquare)board.getSquareAt(travel_x, travel_y);
					if (empty_square.opened == false && empty_square != null){
						empty_square.clicked(); // HERE, I USED RECURSION TO OPEN THE SURROUNDING SQUARES WITH NO BOMBS.
					}
				}
			}
		}
	}
}