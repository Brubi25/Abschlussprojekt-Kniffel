package kniffel;

public class Chance extends Hand {
	private static Chance instance;
	
	private Chance() {
	}
	
	public boolean isValid(Wurf W) {
		return true;
	}

	@Override
	public int getValue(Wurf W) {
		return W.sumOfAll();
	}

	public static Chance getInstance() {
		if(instance == null) {
			instance = new Chance();
		}
		return instance;
	}
}
