package kniffel;

public class GrosseStrasse extends Hand {
	private static GrosseStrasse instance;
	private GrosseStrasse() {
		
	}
	
	public static GrosseStrasse getInstance() {
		if(instance == null) {
			instance = new GrosseStrasse();
		}
		return instance;
	}
	

	public boolean isValid(Wurf W) {
		int[] anzahlen = new int[]{0,0,0,0,0,0};
		for(int i = 0; i < 5; i++) {
			if(anzahlen[W.get(i) - 1]++ != 0) {
				return false;
			}
		}
		return anzahlen[0] == 0 || anzahlen[5] == 0;
	}

	public int getValue(Wurf W) {
		return this.isValid(W) ? 40 : 0;
	}
}
