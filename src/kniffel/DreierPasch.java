package kniffel;

public class DreierPasch extends Pasch {
	private static DreierPasch instance;
	private DreierPasch() {
		super();
		this.anzahl = 3;
	}

	public static DreierPasch getInstance() {
		if(instance == null) {
			instance = new DreierPasch();
		}
		return instance;
	}
}
