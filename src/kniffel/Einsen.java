package kniffel;

public class Einsen extends ZahlenHand {
	private static Einsen instance;
	
	private Einsen() {
		super();
		this.zahl = 1;
	}
	
	public static Einsen getInstance() {
		if(instance == null) {
			instance = new Einsen();
		}
		return instance;
	}
}
