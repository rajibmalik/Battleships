package battleships;

public class SmallBattleship extends Battleship {
	private static int shipsAllowed = 3;
	private static int sizeOfShip = 1;
	
	public SmallBattleship() {
		super(1);
	}
	
	public static int getShipsAllowed() {
		return SmallBattleship.shipsAllowed;
	}
	
	public static int getSizeOfShip() {
		return SmallBattleship.sizeOfShip;
	}
	
	public String toString() {
		return ("SMALL BATTLESHIP");
		
	}
	
	

}
