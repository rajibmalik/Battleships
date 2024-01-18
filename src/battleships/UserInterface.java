package battleships;
import java.util.Scanner;

public class UserInterface { // abstraction of what would be in main, to make cleaner
	private Player playerOne;
	private Player playerTwo;
	private Scanner scanner = new Scanner(System.in);
	
	public UserInterface(Player playerOne, Player playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}
	
	public void start() { // runs the game
		getPlayerNames(); 
		
		while(playerOne.takeTurn() == false && playerTwo.takeTurn() == false) { // continues the game until there is no remaining battleships
//			playerOne.getBoardObject().printBattleshipLocations(); // testing purposes, prints battleships locations
			
			playerOneTakesTurn();
			System.out.print("\n");
			printPlayerScores();
			System.out.print("\n");
			
//			playerTwo.getBoardObject().printBattleshipLocations(); // testing purposes, prints battleships locations
			playerTwoTakesTurn();
			System.out.print("\n");
			printPlayerScores();
			System.out.print("\n");
			
		}
		declareWinner();
	}
	
	public void getPlayerNames() { // gets player names by user input
		System.out.println("Player one, please enter your name:");
		String playerOneName = this.scanner.nextLine();
		System.out.println("Player two, please enter your name:");
		String playerTwoName = this.scanner.nextLine();
		
		this.playerOne.setName(playerOneName);
		this.playerTwo.setName(playerTwoName);
	}
	
	public void printPlayerScores() { // prints currents scores of both players
		System.out.println(playerOne.getName() + "'s score:" + playerOne.getScore());
		System.out.println(playerTwo.getName() + "'s score:" + playerTwo.getScore());
	}
	
	public boolean playerOneTakesTurn() { // player one fires at the board
		System.out.println(playerOne.getBoardObject().toString());
		System.out.println(playerOne.getName() + " pick a row to fire at:");
		int row = Integer.valueOf(this.scanner.nextLine());
		
		System.out.println(playerOne.getName() + " pick a column to fire at:");
		int column = Integer.valueOf(this.scanner.nextLine());
			
		return playerOne.takeTurn(row, column);
	}
	
	public boolean playerTwoTakesTurn() { // player two fires at the board
		System.out.println(playerTwo.getBoardObject().toString()); 
		System.out.println(playerTwo.getName() + " pick a row to fire at:");
		int row = Integer.valueOf(this.scanner.nextLine());
		
		System.out.println(playerTwo.getName() + " pick a column to fire at:");
		int column = Integer.valueOf(this.scanner.nextLine());
		
		return playerTwo.takeTurn(row, column);
	}
	
	public void declareWinner() { // calculates the scores of both players and determines the winner
		System.out.println(playerOne.getBoardObject().toString());
		if(playerOne.getScore() > playerTwo.getScore()) {
			System.out.println("Congratulations " + playerOne.getName() +
		    " has won the game with a score of " + playerOne.getScore());
		} else if (playerTwo.getScore() > playerOne.getScore()) {
			System.out.println("Congratulations " + playerTwo.getName() +
		    " has won the game with a score of " + playerTwo.getScore());
		} else {
			System.out.println("Both players have equal points, you have drawn");
		}
	}
}
