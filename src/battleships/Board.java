package battleships;
import java.util.Random;

public class Board {
	private Square [][] board;
	private Battleship [] battleships = new Battleship[10]; // an array of all the squares containing battleships
	private int generatedBattleships; // an index for the last element not containing a battleship, defaults to 0
	
	public Board(int row, int column) {
		setBoard(row, column);
		generateSquares(row, column);
		generateAllBattleships();
	}
	 
	public void setBoard(int row, int column) {
		this.board = new Square [row][column];
	}
	
	public Square[][] getBoard() {
		return this.board;
	}
	
	public Battleship[] getBattleships() {
		return this.battleships;
	}
	
	private void generateSquares(int row, int column) { // generates squares on the board
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				this.board[i][j] = new Square(i,j);
			}
		}
	}
	
	public Battleship getBattleshipSize(int size) { // returns the type of battleship depending on the size
		Battleship battleship = null;						   // allows differentiation for toString method for testing, not necessary for current game
		
		if (size == 1) {
			battleship = new SmallBattleship();
		} else if (size == 2) {
			battleship = new MediumBattleship();
		} else if (size == 3) {
			battleship = new LargeBattleship();
		}
		
		return battleship;
	}
	
	private void generateSmallBattleships() { // while there is less ships then allowed small ships on board, generates small ships randomly
		while(this.generatedBattleships < SmallBattleship.getShipsAllowed()) {
			int battleshipRow = randomBoardNumber(); 
			int battleshipColumn = randomBoardNumber(); 
			
			if (noHorizontalBattleship(battleshipRow, battleshipColumn, SmallBattleship.getSizeOfShip())) { // if the squares do not contain a small battleship 
				horizontalBattleship(SmallBattleship.getSizeOfShip(), battleshipRow, battleshipColumn);	        // generate a small battleship
			}	
		}		
	}
		
	private void generateMediumBattleships() { // while there is less ships then allowed medium ships on board, generates allowed medium ships randomly
		while(this.generatedBattleships - SmallBattleship.getShipsAllowed() 
			  < MediumBattleship.getShipsAllowed() * MediumBattleship.getSizeOfShip()) {
			int battleshipRow = randomBoardNumber();
			int battleshipColumn = randomBoardNumber();
			boolean spawnShipVertically = spawnVerticalBattleShip();
			
			if (spawnShipVertically && noVerticalBattleship(battleshipRow, battleshipColumn, MediumBattleship.getSizeOfShip())) { // generate vertical medium battleship if
				verticalBattleship(MediumBattleship.getSizeOfShip(), battleshipRow, battleshipColumn); 								  // spawn vertical and does not contain battleship
				
			} else if(noHorizontalBattleship(battleshipRow, battleshipColumn, MediumBattleship.getSizeOfShip())) {				  // generate horizontal medium battleship if 
				horizontalBattleship(MediumBattleship.getSizeOfShip(), battleshipRow, battleshipColumn);							  // does not contain medium battleship
			}	
		}		
	}
	
	private void generateLargeBattleships() { // while there is less ships then allowed large ships on board, generates allowed large ships randomly
		while(this.generatedBattleships - SmallBattleship.getShipsAllowed()
				- MediumBattleship.getShipsAllowed()< LargeBattleship.getShipsAllowed() 
				* LargeBattleship.getSizeOfShip()) {
			int battleshipRow = randomBoardNumber();
			int battleshipColumn = randomBoardNumber();
			boolean spawnShipVertically = spawnVerticalBattleShip();
			
			if (spawnShipVertically && noVerticalBattleship(battleshipRow, battleshipColumn, LargeBattleship.getSizeOfShip())) {
				verticalBattleship(LargeBattleship.getSizeOfShip(), battleshipRow, battleshipColumn); 
				
			} else if(noHorizontalBattleship(battleshipRow, battleshipColumn, LargeBattleship.getSizeOfShip())) {
				horizontalBattleship(LargeBattleship.getSizeOfShip(), battleshipRow, battleshipColumn);	
			}	
		}		
	}
	
	public void generateAllBattleships() { // helper method to call all ship generation methods
		generateSmallBattleships();
		generateMediumBattleships(); 
		generateLargeBattleships();
	}
	
	public void horizontalBattleship(int battleshipSize, int battleshipRow, int battleshipColumn) { // generates horizontal battleship according to size
		Battleship battleship = getBattleshipSize(battleshipSize); 
		
		for (int i = 0; i < battleshipSize; i++) {
			this.board[battleshipRow][battleshipColumn + i].addBattleship(battleship);
			this.battleships[generatedBattleships] = battleship;
			this.generatedBattleships ++;
		}
	}
		
	private boolean noVerticalBattleship(int battleshipRow, int battleshipColumn, int sizeOfShip) { // checks if horizontal squares contain battleship
		boolean noBattleship = true; 																
		
		for (int i = 0; i < sizeOfShip; i++) {
			if (!(this.board[battleshipRow + i][battleshipColumn].getBattleShip() == null)) {
				noBattleship = false;
			}
		}
		
		return noBattleship;
	}
	
	public void verticalBattleship(int battleshipSize, int battleshipRow, int battleshipColumn) { // generates vertical battleship
		Battleship battleship = getBattleshipSize(battleshipSize); 
		
		for (int i = 0; i < battleshipSize; i++) {
			this.board[battleshipRow + i][battleshipColumn].addBattleship(battleship);
			this.battleships[generatedBattleships] = battleship;
			this.generatedBattleships ++;
		}
	}
	
	private boolean spawnVerticalBattleShip() { // coin flip if battleship should be generated vertically
		Random r = new Random();
		boolean spawnShipVertically = r.nextBoolean();
		
		return spawnShipVertically;
	}
			
	private boolean noHorizontalBattleship(int battleshipRow, int battleshipColumn, int sizeOfShip) { // checks if there is a horizontal battleship 
		boolean doesNotContainBattleship = true;                                                                  
		
		for (int i = 0; i < sizeOfShip; i++) {
			if (!(this.board[battleshipRow][battleshipColumn + i].getBattleShip() == null)) {
				doesNotContainBattleship = false;
			}
		}
		
		return doesNotContainBattleship;
	}
		
	private int randomBoardNumber() { // generates a random number from 0 - 7 for battleship generation
		Random r = new Random();			  // to make more random, each size battleship should change random number generated
											  // eg medium r.nextInt(9), small r.nextInt(10)
		return r.nextInt(8);
	}
	
	@Override
	public String toString() {
		String stringRepresentation = "";
		stringRepresentation += "   "; // adds spacing to make toString neater
		int row = 1; // the rows are printed from 1 - 10 instead of 0 - 9
		int column = 1; // the columns are printed from 1 - 10 instead of 0 - 9
		
		for (int i = 0; i < 10; i ++) { // prints the row numbers
			stringRepresentation += String.format("%3s", row);
			row ++;
		}
		
		stringRepresentation += "\n";
		
		for (int i = 0; i < 10; i++) { // prints the column numbers
			stringRepresentation += String.format("%3s", column);
			column++;
			for (int j = 0; j < 10; j++) { // prints the Square.toString()
				stringRepresentation += this.board[i][j].toString(); 
			}
			stringRepresentation += "\n";
		}
		
		return stringRepresentation;
	}
	
	// testing purposes, spawns all battleship locations
	public void printBattleshipLocations() {
		for (int i = 0; i < 10; i ++) {
			for (int j = 0; j < 10; j++) {
				if (this.board[i][j].getHasShip()) {
					System.out.println(this.board[i][j].getBattleShip().toString() + ", " + (i + 1) + ", " + (j + 1));	
				}
			}
		}
	}
	
}

