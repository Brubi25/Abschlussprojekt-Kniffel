package kniffel;

public class GespielteHand implements DarstellbarerWert {
	private int wert;
	
	GespielteHand(int wert){
		this.wert = wert;
	}
	
	public boolean isPlayable() {
		return false;
	}

	public int getValue(Wurf W) {
		return wert;
	}

}
