package kniffel;

import java.util.Hashtable;
import java.util.Iterator;

public class Spieler {
	private String name;
	private Hashtable<String, DarstellbarerWert> Haende; 
	
	public Spieler(String name) {
		this.name = name;
		
		Haende = new Hashtable<String, DarstellbarerWert>();
		Haende.put("Einsen", Einsen.getInstance());
		Haende.put("Zweien", Zweien.getInstance());
		Haende.put("Dreien", Dreien.getInstance());
		Haende.put("Vieren", Vieren.getInstance());
		Haende.put("Fünfen", Fuenfen.getInstance());
		Haende.put("Sechsen", Sechsen.getInstance());
		Haende.put("Dreierpasch", DreierPasch.getInstance());
		Haende.put("Viererpasch", ViererPasch.getInstance());
		Haende.put("Full House", FullHouse.getInstance());
		Haende.put("Kleine Straße", KleineStrasse.getInstance());
		Haende.put("Große Straße", GrosseStrasse.getInstance());
		Haende.put("Kniffel", Kniffel.getInstance());
		Haende.put("Chance", Chance.getInstance());
	}
	
	public void print(Wurf W) {
		System.out.println(name + ": " + "\t" + W);
		
		Iterator<String> HandName = Haende.keySet().iterator();
		for(DarstellbarerWert Wert : Haende.values()) {
			System.out.println("\t" + HandName.next() + " (" + (Wert.isPlayable() ? "Spielbar" : "Nicht Spielbar") + "): " + (Wert.getValue(W) == 0 ? "---" : Wert.getValue(W)));
		}
	}
	
	public boolean play(String hand, Wurf W) {
		DarstellbarerWert Wert;
		if((Wert = Haende.get(hand)) != null && Wert.isPlayable()) {
			Haende.put(hand, new GespielteHand(Wert.getValue(W)));
			return true;
		}
		return false;
	}
	
	public int getWert(String hand, Wurf W) {
		return Haende.get(hand).getValue(W);
	}
	
	public String getGespielterWert(String hand) {
		DarstellbarerWert Wert;
		if((Wert = Haende.get(hand)) != null && !Wert.isPlayable()) {
			return "" + Wert.getValue(null);
		}
		return "";
	}
	
	public boolean isPlayeble(String hand) {
		return Haende.get(hand).isPlayable();
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isFinished() {
		for(DarstellbarerWert Wert : Haende.values()) {
			if(Wert.isPlayable()) {
				return false;
			}
		}
		return true;
	}
}
