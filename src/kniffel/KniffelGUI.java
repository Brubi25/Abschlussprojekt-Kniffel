package kniffel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class KniffelGUI extends JFrame{
	
	public void WuerfelMalen(int num) {
		
	}
	
	private Game spiel;
	
	//Tabelle mit Werten
	private JPanel Panel1;
	private JTable Tabelle;
	private TableColumn col;
	private JScrollPane ScrollPane;
	private Object[] SpaltenBeschriftung = {"", " ", " "};
	private Object[][] ReihenBeschriftung = {
			{"", "1er", "nur Einser zaehlen"},
			{"", "2er", "nur Zweier zaehlen"},
			{"", "3er", "nur Dreier zaehlen"},
			{"", "4er", "nur Vierer zaehlen"},
			{"", "5er", "nur Fuenfer zaehlen"},
			{"", "6er", "nur Sechser zaehlen"},
			{"", "gesamt", "          -------->"},
			{"", "Bonus bei 63 oder mehr", "plus 35"},
			{"", "gesamt oberer Teil", "          -------->"},
			{"", "Dreierpasch", "Alle Augen zaehlen"},
			{"", "Viererpasch", "Alle Augen zaehlen"},
			{"", "Full House", "25 Punkte"},
			{"", "Kleine Straße", "30 Punkte"},
			{"", "Große Straße", "40 Punkte"},
			{"", "Kniffel", "50 Punkte"},
			{"", "Chance", "alle Augen zaehlen"},
			{"", "gesamt unterer Teil", "          -------->"},
			{"", "gesamt oberer Teil", "          -------->"},
			{"", "Endsumme", "          -------->"},
			};
	
	//vor Spielstart mit Hinzufügen/Entfernen von Spielern und Startbutton
	private JPanel Panel2;
	private JButton Hinzufuegen;
	private JTextField HTextfeld;
	private JButton Entfernen;
	private JTextField ETextfeld;
	private JButton Start;
	
	//während Spiel
	private JPanel Panel3;
	private GridBagLayout Gridbaglayout;
	private GridBagConstraints Gridbagconstraints;
	private Insets Insets;
	private boolean[] WuerfelReRoll = {true, true, true, true, true}; //true  == reroll
	private JButton Wuerfel1;
	private JButton Wuerfel2;
	private JButton Wuerfel3;
	private JButton Wuerfel4;
	private JButton Wuerfel5;
	private JButton Wuerfeln;
	private Color toggleOff = new Color(110, 106, 105);
	private Color toggleOn = new Color(222, 106, 216);		
	private Wurf wurf;

	public KniffelGUI(Game spiel){
		this.spiel = spiel;
		this.setSize(800, 800);
		this.setLocation(500, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Kniffel");
		this.setLayout(new GridLayout(2,1));
		
		Panel1 = new JPanel();
		Panel1.setLayout(new BorderLayout());
		
		Panel2 = new JPanel();
		Panel2.setLayout(null);
		
		Panel3 = new JPanel();
		Gridbaglayout = new GridBagLayout();
		Gridbagconstraints = new GridBagConstraints();
		Gridbaglayout.setConstraints(Panel3, Gridbagconstraints);
		Insets = new Insets(20,20,20, 20);
		Gridbagconstraints.insets = Insets;
		Panel3.setLayout(Gridbaglayout);
		
		this.add(Panel1);
		this.add(Panel2);
		
		//Panel1 -> Tabelle
		Tabelle = new JTable(ReihenBeschriftung, SpaltenBeschriftung);
		//für hinzufügen von leerer Spalte wird 0. Spalte genutzt aber nicht final hoffentlich
		Tabelle.getColumnModel().getColumn(0).setMinWidth(0);
		Tabelle.getColumnModel().getColumn(0).setMaxWidth(0);
		Tabelle.getColumnModel().getColumn(0).setWidth(0);
		ScrollPane = new JScrollPane(Tabelle);
		Tabelle.getTableHeader().setReorderingAllowed(false);
		Panel1.add(ScrollPane, BorderLayout.CENTER);
		

		//Panel2 -> vor Spielstart Interface
		Panel2.setBackground(Color.blue);
				
		HTextfeld = new JTextField();
		HTextfeld.setBounds(10, 20, 100, 20);
		Panel2.add(HTextfeld);
		
		Hinzufuegen = new JButton("Spieler hinzufügen");
		Hinzufuegen.setBounds(130, 20, 150, 20);
		Panel2.add(Hinzufuegen);
		Hinzufuegen.addActionListener(e -> spielerdazu());	
		
		ETextfeld = new JTextField();
		ETextfeld.setBounds(320, 20, 100, 20);
		Panel2.add(ETextfeld);
		
		Entfernen = new JButton("Spieler entfernen");
		Entfernen.setBounds(430, 20, 150, 20);
		Panel2.add(Entfernen);
		Entfernen.addActionListener(e -> spielerweg());
		
		Start = new JButton("Spiel starten");
		Start.setBounds(600, 20, 150, 20);
		Panel2.add(Start);
		Start.addActionListener(e -> spielstarten());
		
		//Panel3 -> während Spiel Interface
		Panel3.setBackground(Color.green);
		Wuerfel1 = new JButton(" ");
		Gridbagconstraints.gridx = 0;
		Gridbagconstraints.gridy = 0;
		Panel3.add(Wuerfel1, Gridbagconstraints);
		Wuerfel1.setBackground(toggleOn);
		Wuerfel1.addActionListener(e -> ToggleOnOff(Wuerfel1, 0, WuerfelReRoll));
	
		Wuerfel2 = new JButton(" ");
		Gridbagconstraints.gridx = 1;
		Gridbagconstraints.gridy = 0;
		Panel3.add(Wuerfel2, Gridbagconstraints);
		Wuerfel2.setBackground(toggleOn);
		Wuerfel2.addActionListener(e -> ToggleOnOff(Wuerfel2, 1, WuerfelReRoll));
		
		Wuerfel3 = new JButton(" ");
		Gridbagconstraints.gridx = 2;
		Gridbagconstraints.gridy = 0;
		Panel3.add(Wuerfel3, Gridbagconstraints);
		Wuerfel3.setBackground(toggleOn);
		Wuerfel3.addActionListener(e -> ToggleOnOff(Wuerfel3, 2, WuerfelReRoll));
		
		Wuerfel4 = new JButton(" ");
		Gridbagconstraints.gridx = 3;
		Gridbagconstraints.gridy = 0;
		Panel3.add(Wuerfel4, Gridbagconstraints);
		Wuerfel4.setBackground(toggleOn);
		Wuerfel4.addActionListener(e -> ToggleOnOff(Wuerfel4, 3, WuerfelReRoll));
		
		Wuerfel5 = new JButton(" ");
		Gridbagconstraints.gridx = 4;
		Gridbagconstraints.gridy = 0;
		Panel3.add(Wuerfel5, Gridbagconstraints);
		Wuerfel5.setBackground(toggleOn);
		Wuerfel5.addActionListener(e -> ToggleOnOff(Wuerfel5, 4, WuerfelReRoll));
		
		Wuerfeln = new JButton("Würfeln");
		Gridbagconstraints.gridx = 5;
		Gridbagconstraints.gridy = 0;
		Panel3.add(Wuerfeln, Gridbagconstraints);
		Wuerfeln.addActionListener(e -> GUI_wuerfeln(WuerfelReRoll));
	
	}

	

	/*
	 * Nimmt String aus Textfeld und fügt Tabelle + Array hinzu, wenn Spieleranzahl < 6
	 */
	private void spielerdazu() {
		String name = HTextfeld.getText();
		if(name.equals("")) {
			JOptionPane.showMessageDialog(null, "Keinen Namen vergeben.");
		}else if(spiel.getAnzahlSpieler() < 6) {
			spiel.addPlayer(name); //added Player in Backend
			col = new TableColumn();
			col.setHeaderValue(name);
			Tabelle.addColumn(col); //added in Spalte in Frontend
			HTextfeld.setText("");
			JOptionPane.showMessageDialog(null, "Spieler " + name + " hinzugefügt");
		}else {
			JOptionPane.showMessageDialog(null, "Maximale Spieleranzahl erreicht.");
		}
	}
	
	/*
	 * Entfernt Spieler aus Tabelle + Array, sofern vorhanden in Array
	 * 
	 */
	private void spielerweg() {
		String name = ETextfeld.getText();
		int spielerpos = spiel.removePlayer(name); // löscht Spieler und returnt position in Backend Array
		if(spielerpos != Integer.MAX_VALUE) {
			spielerpos += 3;
			col = Tabelle.getColumnModel().getColumn(spielerpos);
			Tabelle.removeColumn(col); // Spalte in Frontend removed
			ETextfeld.setText("");
			JOptionPane.showMessageDialog(null, "Spieler " + name + " gelöscht.");
		} else {
			JOptionPane.showMessageDialog(null, "Spieler nicht vorhanden.");
		}
	}
	
	/*
	 * Entfernt Panel für Hinzufügen/Entfernen von Spielern und Starten
	 * Fügt Spielpanel mit Würfeln etc hinzu
	 */ 
	private void spielstarten() {
		if(spiel.getAnzahlSpieler() != 0) {
			remove(Panel2);
			add(Panel3);
			revalidate();
		} else {
			JOptionPane.showMessageDialog(null, "Es müssen mehr als Null Spieler teilnehmen.");
		}
	}

	private void GUI_wuerfeln(boolean[] wuerfel) {
		wurf = spiel.wurfeln(wuerfel);
		System.out.println(wurf);
		System.out.println(wurf.sumOfAll());
		
		Wuerfel1.setText(Integer.toString(wurf.get(0)));
		Wuerfel2.setText(Integer.toString(wurf.get(1)));
		Wuerfel3.setText(Integer.toString(wurf.get(2)));
		Wuerfel4.setText(Integer.toString(wurf.get(3)));
		Wuerfel5.setText(Integer.toString(wurf.get(4)));
		
	}

	private void ToggleOnOff(JButton button, int index, boolean[] wuerfel) {
		if(wuerfel[index]) {
			wuerfel[index] = false;
			button.setBackground(toggleOff);
		} else {
			wuerfel[index] = true;
			button.setBackground(toggleOn);
		}
	}
	
	private void GameLoop() {
		
	}
}
