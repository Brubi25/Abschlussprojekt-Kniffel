package kniffel;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class KniffelGUI extends JFrame{
	
	public void WuerfelMalen(int num) {
		
	}
	
	KniffelGUIDaten test = new KniffelGUIDaten();
	String[] SpaltenBeschriftung = {"", "", test.getSpielerName(0), test.getSpielerName(1), test.getSpielerName(2)}; 
	Object[][] ReihenBeschriftung = {
			{"1er", "nur Einser zaehlen"},
			{"2er", "nur Zweier zaehlen"},
			{"3er", "nur Dreier zaehlen"},
			{"4er", "nur Vierer zaehlen"},
			{"5er", "nur Fuenfer zaehlen"},
			{"6er", "nur Sechser zaehlen"},
			{"gesamt", "          -------->"},
			{"Bonus bei 63 oder mehr", "plus 35"},
			{"gesamt oberer Teil", "          -------->"},
			{"Dreierpasch", "Alle Augen zaehlen"},
			{"Viererpasch", "Alle Augen zaehlen"},
			{"Full House", "25 Punkte"},
			{"Kleine Straße", "30 Punkte"},
			{"Große Straße", "40 Punkte"},
			{"Kniffel", "50 Punkte"},
			{"Chance", "alle Augen zaehlen"},
			{"gesamt unterer Teil", "          -------->"},
			{"gesamt oberer Teil", "          -------->"},
			{"Endsumme", "          -------->"},
			};
	
	KniffelGUI(){
		this.setName("Kniffel");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 800);
		this.setBackground(Color.gray);
		
		TableModel data = new DefaultTableModel(ReihenBeschriftung, SpaltenBeschriftung);
		JTable Tabelle = new JTable(data);
		
		JScrollPane scrollPane = new JScrollPane(Tabelle);
		
		this.add(scrollPane);

		this.setVisible(true);
	}
}
