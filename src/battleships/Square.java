package battleships;

public class Square {
	private int row;
	private int column;
	private boolean hasShip; // false by default
	private Battleship battleship; // reference to instance of Battleship
	private boolean hasBeenFiredAt; // false by default
	
	public Square(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public Battleship getBattleShip() {
		return this.battleship;
	}
	
	public boolean getHasBeenFiredAt() {
		return this.hasBeenFiredAt;
	}
	
	public boolean getHasShip() { // if there is a reference to a battleship, returns true
		if (this.battleship == null) {
			return false;
		} 
		
		return true;
	}
	
	public void setHasBeenFiredAt() {
		this.hasBeenFiredAt = true;
	}
	
	public void BattleshipTakesDamage() {
		this.battleship.takeDamage();     // referenced battleship takes damage
	}
	
	public void addBattleship(Battleship battleship) {
		this.battleship = battleship;     // adds reference to an instance of a battleship 
		this.hasShip = true;
	}
	
	@Override 
	public String toString() {
		if (this.hasBeenFiredAt == true && this.getHasShip()){ 
			return String.format("%3s", "X"); // x if the square has a ship and has been fired at
		} else if (this.hasBeenFiredAt == true) { // O if it has been fired at and does not contain a ship
			return String.format("%3s", "O");
		} else {
			return String.format("%3s","-"); // - if it has not been fired at 
		}
	}
}
