package kniffel;

public class Vieren extends ZahlenHand {
	private static Vieren instance;
	
	private Vieren() {
		super();
		this.zahl = 4;
	}
	
	public static Vieren getInstance() {
		if(instance == null) {
			instance = new Vieren();
		}
		return instance;
	}
}