package kniffel;

public abstract class ZahlenHand extends Hand {
	protected int zahl;

	public boolean isValid(Wurf W) {
		return W.includes(zahl);
	}
	
	public int getValue(Wurf W) {
		return W.count(zahl) * zahl;
	}
}