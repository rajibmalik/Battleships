package battleships;

public class Battleship {
	private boolean isSunk;
	private int hitPoints;
	private static int sizeOfShip;
	
	public Battleship(int sizeOfShip) {
		this.sizeOfShip = sizeOfShip;
		this.hitPoints = sizeOfShip;
	}
	
	public static int getSizeOfShip() {
		return Battleship.sizeOfShip;
	}
	
	public boolean getIsSunk() {
		if (this.hitPoints == 0) {
			return true;
		}
		
		return false;
	}
	
	public int getHealth() {
		return this.hitPoints;
	}
	
	
	public void takeDamage() {
		this.hitPoints --;
	}
	
	@Override // test 
	public String toString() {
		return ("BATTLESHIP");
	}
}



