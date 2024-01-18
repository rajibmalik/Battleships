package battleships;

public class MediumBattleship extends Battleship {
	private static int shipsAllowed = 2;
	private static int sizeOfShip = 2;
	
	public MediumBattleship() {
		super(2);
	}
	
	public static int getShipsAllowed() {
		return MediumBattleship.shipsAllowed;
	}
	
	public static int getSizeOfShip() {
		return MediumBattleship.sizeOfShip;
	}
		
	@Override // test 
	public String toString() {
		return ("MEDIUM BATTLESHIP");
	}

}
