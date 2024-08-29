package kniffel;

public class FesterWert implements DarstellbarerWert {
	private int wert;
	
	FesterWert(int wert){
		this.wert = wert;
	}
	
	public boolean isPlayable() {
		return false;
	}

	public int getValue(Wurf W) {
		return wert;
	}

}
