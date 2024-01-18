package battleships;

public class Main {
	public static void main (String[] args) {
		Board board = new Board(10, 10);
		
		Player playerOne = new Player(board, "PlayerOne");
		Player playerTwo = new Player(board, "PlayerTwo");
		
		UserInterface UI = new UserInterface(playerOne, playerTwo);
		UI.start();
	}
}
