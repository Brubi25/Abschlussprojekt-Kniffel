package kniffel;

public class KleineStrasse extends Hand {
	private static KleineStrasse instance;
	private KleineStrasse() {
	}
	
	public static KleineStrasse getInstance() {
		if(instance == null) {
			instance = new KleineStrasse();
		}
		return instance;
	}
	
	public boolean isValid(Wurf W) {
		int[] anzahlen = new int[]{0,0,0,0,0,0};
		for(int i = 0; i < 5; i++) {
			anzahlen[W.get(i) - 1]++;
		}
		
		int laengsteReihe = 1;
		int curReihe = 1;
		for(int i = 0; i < 5; i++) {
			if(anzahlen[i] >= 1 && anzahlen[i+1] >= 1) {
				curReihe++;
				if(curReihe > laengsteReihe) {
					laengsteReihe = curReihe;
				}
			}else {
				curReihe = 1;
			}
		}
		return laengsteReihe >= 4;
	}

	@Override
	public int getValue(Wurf W) {
		return this.isValid(W) ? 30 : 0;
	}
}