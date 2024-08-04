package kniffel;

public class Sechsen extends ZahlenHand {
	private static Sechsen instance;
	
	private Sechsen() {
		super();
		this.zahl = 6;
	}
	
	public static Sechsen getInstance() {
		if(instance == null) {
			instance = new Sechsen();
		}
		return instance;
	}
}