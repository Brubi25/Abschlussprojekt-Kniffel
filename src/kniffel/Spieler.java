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
		Haende.put("Fuenfen", Fuenfen.getInstance());
		Haende.put("Sechsen", Sechsen.getInstance());
		Haende.put("DreierPasch", DreierPasch.getInstance());
		Haende.put("ViererPasch", ViererPasch.getInstance());
		Haende.put("FullHouse", FullHouse.getInstance());
		Haende.put("KleineStrasse", KleineStrasse.getInstance());
		Haende.put("GrosseStrasse", GrosseStrasse.getInstance());
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
	
	public String getName() {
		return this.name;
	}
}
