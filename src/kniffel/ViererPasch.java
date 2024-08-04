package kniffel;

public class ViererPasch extends Pasch {
	private static ViererPasch instance;
	private ViererPasch() {
		super();
		this.anzahl = 4;
	}

	public static ViererPasch getInstance() {
		if(instance == null) {
			instance = new ViererPasch();
		}
		return instance;
	}
}
