package kniffel;

public abstract class Hand implements DarstellbarerWert{
	public boolean isPlayable() {
		return true;
	}
	
	abstract public boolean isValid(Wurf W);
}