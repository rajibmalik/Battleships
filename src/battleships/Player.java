package battleships;

public class Player {
	private Board board;
	private String name;
	private int score; // set to 0 by default
	
	public Player(Board board, String playerName) {
		this.board = board;
		this.name = playerName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean destroyedLastBattleship() { // if the last battleship has been destroyed, returns true
		for (Battleship battleship:board.getBattleships()) {
			if (battleship.getIsSunk() == false) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean takeTurn(int row, int column) {
		row -= 1; // set to -1 to be able to play with board of 1 -10 instead of 0 - 9
		column -= 1; // set to -1 to be able to play with board of 1 -10 instead of 0 - 9
		assessTurn(row, column); // prints result of attacking the specific square before the squares values are altered
		
		if (getBoard()[row][column].getHasShip() && getBoard()[row][column].getHasBeenFiredAt() == false ) { // if it has not been fired at and contains a ship
			getBoard()[row][column].setHasBeenFiredAt();													 // set to fired at 
			getBoard()[row][column].BattleshipTakesDamage();												 // and the ship takes damage
			sunkShip(row, column);																			 // check if the ship has sunk
			
		} else {
			getBoard()[row][column].setHasBeenFiredAt();
		}
		
		return destroyedLastBattleship();																	 // returns true if last ship destroyed
	}
	
	public boolean takeTurn() {																			     // returns true if last ship destroyed
		return destroyedLastBattleship();
	}
	
	public void assessTurn (int row, int column) {
		if (destroyedLastBattleship()) {
			System.out.println(this.name + " has destroyed the last battleship, this ends the game");					// if last ship has been destroyed, prints end game
			} else if (getBoard()[row][column].getHasBeenFiredAt() == true) {
				System.out.println(this.name + "'s turn has been skipped, this square has already been under fire");      // if square already fired at, skips turn 
			} else if (getBoard()[row][column].getHasShip() == true) {
				System.out.println(this.name + " has hit a battleship");
			} else {
				System.out.println(this.name + " has missed, this square does not have a battleship");
		}
	}
	
	public Square[][] getBoard() {
		return this.board.getBoard();
	}
	
	public Board getBoardObject() {
		return this.board;
	}
	
	public void gainScore() {
		this.score ++;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void sunkShip(int row, int column) { // increments score when ship destroyed
		Square[][] gameBoard = board.getBoard();
		
		if (destroyedLastBattleship()) {
			gainScore();
			System.out.println("and has destroyed the last battleship");		// lets players known when last ship has been destroyed
		} else if (gameBoard[row][column].getBattleShip().getIsSunk() == true) {
			gainScore();
			System.out.println("and has sunk the battleship");
			
		}	
	}
}
