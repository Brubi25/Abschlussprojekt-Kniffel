package kniffel;

public class FullHouse extends Hand {
	private static FullHouse instance;
	private FullHouse() {
		
	}
	
	public static FullHouse getInstance() {
		if(instance == null) {
			instance = new FullHouse();
		}
		return instance;
	}

	public boolean isValid(Wurf W) {
		int[] anzahlen = new int[] {0,0,0,0,0,0};
		for(int i = 0; i < 5; i++) {
			anzahlen[W.get(i) - 1]++;
		}
		boolean hasThree = false, hasTwo = false;
		for(int i : anzahlen) {
			if(i == 3) {
				hasThree = true;
				continue;
			}
			if(i == 2) {
				hasTwo = true;
			}
		}
		return hasThree && hasTwo;
	}

	public int getValue(Wurf W) {
		return this.isValid(W) ? 25 : 0;
	}
	
}
