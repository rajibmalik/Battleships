package battleships;

public class LargeBattleship extends Battleship {
	private static int shipsAllowed = 1;
	private static int sizeOfShip = 3;
	
	public LargeBattleship() {
		super(3);
	}
	
	public static int getShipsAllowed() {
		return LargeBattleship.shipsAllowed;
	}
	
	public static int getSizeOfShip() {
		return LargeBattleship.sizeOfShip;
	}
	
	public String toString() {
		return ("LARGE BATTLESHIP");
	}

}
