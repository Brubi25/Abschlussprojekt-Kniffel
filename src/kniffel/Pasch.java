package kniffel;

public abstract class Pasch extends Hand {
	int anzahl;
	
	public boolean isValid(Wurf W) {
		int[] anzahlen = new int[]{0,0,0,0,0,0};
		for(int i = 0; i < 5; i++) {
			anzahlen[W.get(i) - 1]++;
		}
		for(int i : anzahlen) {
			if(i >= anzahl) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getValue(Wurf W) {
		return this.isValid(W) ? W.sumOfAll() : 0;
	}
}