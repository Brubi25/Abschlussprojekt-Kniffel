package kniffel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class KniffelGUI extends JFrame{
	
	public void WuerfelMalen(int num) {
		
	}
	
	private KniffelGUIDaten daten;
	private Game spiel;
	
	//Tabelle mit Werten
	private JPanel Panel1;
	private JTable Tabelle;
	private DefaultTableModel Data;
	private TableColumnModel tcm;
	private JScrollPane ScrollPane;
	private String[] SpaltenBeschriftung = {" ", " "};
	private Object[][] ReihenBeschriftung = {
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
	
	//Würfel und Hinzufügen von Spielern
	private JPanel Panel2;
	private JButton Hinzufuegen;
	private JTextField Textfeld;
	private JButton Entfernen;
	private JTextField Textfeld2;
	
	
		
	public KniffelGUI(KniffelGUIDaten model, Game spiel){
		this.daten = model;
		this.spiel = spiel;
		this.setSize(700, 700);
		this.setLocation(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Kniffel");
		this.setLayout(new GridLayout(2,1));
		
		Panel1 = new JPanel();
		Panel1.setLayout(new BorderLayout());
		
		Panel2 = new JPanel();
		Panel2.setLayout(null);
		
		this.add(Panel1);
		this.add(Panel2);
		
		//Panel1 -> Tabelle
		Data = new DefaultTableModel(ReihenBeschriftung, SpaltenBeschriftung);
		Tabelle = new JTable(Data);
		tcm = Tabelle.getColumnModel();
		ScrollPane = new JScrollPane(Tabelle);
		Tabelle.getTableHeader().setReorderingAllowed(false);
		Panel1.add(ScrollPane, BorderLayout.CENTER);

		//Panel2
		Panel2.setBackground(Color.blue);
		Hinzufuegen = new JButton("Spieler hinzufügen");
		Hinzufuegen.setBounds(130, 20, 150, 20);
		Panel2.add(Hinzufuegen);
		Hinzufuegen.addActionListener(e -> spielerdazu());
		
		Textfeld = new JTextField();
		Textfeld.setBounds(10, 20, 100, 20);
		Panel2.add(Textfeld);
		
		Entfernen = new JButton("Spieler entfernen");
		Entfernen.setBounds(430, 20, 150, 20);
		Panel2.add(Entfernen);
		Entfernen.addActionListener(e -> spielerweg());
		
		Textfeld2 = new JTextField();
		Textfeld2.setBounds(320, 20, 100, 20);
		Panel2.add(Textfeld2);
	}



	private void spielerweg() {
		String name = Textfeld2.getText();
		int spielerpos = spiel.removePlayer(name); //wo gelöschter spieler war
		int pos_tabelle = 0;
		if(spielerpos != Integer.MAX_VALUE) {
			pos_tabelle = spielerpos + 2;
		}
		System.out.println("[gui] PosTabelle: " + pos_tabelle);
		System.out.println("[gui] data.getColumnName: " + Data.getColumnName(pos_tabelle));
		System.out.println("[gui] columnindex" + tcm.getColumnIndex(Data.getColumnName(pos_tabelle)));
		
		//Tabelle.setModel(Data);
		Textfeld.setText("");
		/*
		for(;pos_tabelle < Data.getColumnCount()+2-1; pos_tabelle++) {
			int location  = tcm.getColumnIndex(Data.getColumnName(pos_tabelle));
			tcm.moveColumn(location, pos_tabelle);
		}
		*/
	}



	private void spielerdazu() {
		String name = Textfeld.getText();
		spiel.addPlayer(name);
		Data.addColumn(name);
		Tabelle.setModel(Data);
		Textfeld.setText("");
	}
}
