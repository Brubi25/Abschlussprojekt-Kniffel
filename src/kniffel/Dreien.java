package kniffel;

public class Dreien extends ZahlenHand {
	private static Dreien instance;
	
	private Dreien() {
		super();
		this.zahl = 3;
	}
	
	public static Dreien getInstance() {
		if(instance == null) {
			instance = new Dreien();
		}
		return instance;
	}
}