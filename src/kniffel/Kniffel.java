package kniffel;

public class Kniffel extends Hand {
	private static Kniffel instance;
	
	private Kniffel() {
	}
	
	public boolean isValid(Wurf W) {
		int[] anzahlen = new int[]{0,0,0,0,0,0};
		for(int i = 0; i < 5; i++) {
			anzahlen[W.get(i) - 1]++;
		}
		for(int i : anzahlen) {
			if(i == 5) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getValue(Wurf W) {
		return this.isValid(W) ? 50 : 0;
	}
	
	public static Kniffel getInstance() {
		if(instance == null) {
			instance = new Kniffel();
		}
		return instance;
	}
}