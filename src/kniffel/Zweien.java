package kniffel;

public class Zweien extends ZahlenHand {
	private static Zweien instance;
	
	private Zweien() {
		super();
		this.zahl = 2;
	}
	
	public static Zweien getInstance() {
		if(instance == null) {
			instance = new Zweien();
		}
		return instance;
	}
}