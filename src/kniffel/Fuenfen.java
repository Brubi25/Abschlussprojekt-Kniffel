package kniffel;

public class Fuenfen extends ZahlenHand {
	private static Fuenfen instance;
	
	private Fuenfen() {
		super();
		this.zahl = 5;
	}
	
	public static Fuenfen getInstance() {
		if(instance == null) {
			instance = new Fuenfen();
		}
		return instance;
	}
}