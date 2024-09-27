package gui.kontoverwaltung;

import javax.swing.*;
import java.util.ArrayList;

public class Kontoverwaltung_GUI {
    private JTextField kontostandTF;
    private JButton bestaetigenBTN;
    private JTextArea textArea1;
    private JComboBox<Kontoklasse> kontenCB;
    private JTextArea textArea2;
    private JComboBox<String> kontoartCB; // Added declaration
    private JTextField kontonummerTF; // Added declaration
    private JTextField kontoinhaberTF; // Added declaration
    private JTextField gzTF; // Added declaration
    private JPanel kontoGUI; // Added declaration
    private  JButton einauszahlenBTN;
    private JButton ueberweisenBTN;

    private ArrayList<Kontoklasse> kontenListe = new ArrayList<>();

    public Kontoverwaltung_GUI() {
        kontoartCB.addItem("Girokonto");
        kontoartCB.addItem("Sparkonto");
        kontoartCB.addItem("Kreditkonto");





        bestaetigenBTN.addActionListener(e -> {
            try {
                String kontoart = (String) kontoartCB.getSelectedItem();
                String kontonummer = kontonummerTF.getText();
                String kontoinhaber = kontoinhaberTF.getText();
                double gebuehrenZinsen = Double.parseDouble(gzTF.getText());
                double kontostand = Double.parseDouble(kontostandTF.getText());

                if (isKontonummerExists(kontonummer)) {
                    JOptionPane.showMessageDialog(null, "Kontonummer ist bereits vergeben.");
                    return;
                }

                Kontoklasse konto = null;
                if (kontoart.equals("Girokonto")) {
                    konto = new Girokonto(kontoinhaber, "10020030", kontonummer, 500.0, gebuehrenZinsen, kontostand);
                } else if (kontoart.equals("Sparkonto")) {
                    konto = new Sparkonto(kontoinhaber, "10020030", kontonummer, kontostand, gebuehrenZinsen);
                } else if (kontoart.equals("Kreditkonto")) {
                    konto = new Kreditkonto(kontoinhaber, "10020030", kontonummer, 3000.0, kontostand, gebuehrenZinsen);
                }

                if (konto != null) {
                    kontenListe.add(konto);
                    updateKontenCB();
                    textArea1.append("Konto erstellt: " + kontoart + " - " + kontoinhaber + "\n");
                    textArea1.append("Kontonummer: " + kontonummer + "\n");
                    textArea1.append("Kontostand: " + kontostand + "\n");
                    textArea1.append("Gebühren/Zinsen: " + gebuehrenZinsen + "\n\n");
                }
            } catch (NullPointerException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ein Fehler ist aufgetreten: " + ex.getMessage());
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Bitte geben Sie gültige Zahlenwerte ein.");
            }
        });

        einauszahlenBTN.addActionListener(e -> {
            EinAuszahlen einAuszahlen = new EinAuszahlen(this);
        });

        ueberweisenBTN.addActionListener(e -> {
            Ueberweisen ueberweisen = new Ueberweisen(this);
        });

        kontenCB.addActionListener(e -> {
            Kontoklasse selectedKonto = (Kontoklasse) kontenCB.getSelectedItem();
            if (selectedKonto != null) {
                textArea2.setText(selectedKonto.kontoauszug());
            }
        });
    }

    public ArrayList<Kontoklasse> getKontoList() {
        return kontenListe;
    }

    private boolean isKontonummerExists(String kontonummer) {
        for (Kontoklasse konto : kontenListe) {
            if (konto.getKontonummer().equals(kontonummer)) {
                return true;
            }
        }
        return false;
    }

    private void updateKontenCB() {
        kontenCB.removeAllItems();
        for (Kontoklasse konto : kontenListe) {
            kontenCB.addItem(konto);
        }
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }

    public void setTextArea1(JTextArea textArea1) {
        this.textArea1 = textArea1;
    }

    public JPanel getPanel() {
        return this.kontoGUI;
    }
    //make a getter function that returns the JComboBox kontoartCB
    public JComboBox<String> getKontoartCB() {
        return kontoartCB;
    }

    public static void main(String[] args) {
        Kontoverwaltung_GUI gui = new Kontoverwaltung_GUI();
        JFrame frame = new JFrame("Kontoverwaltung");
        frame.setContentPane(gui.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // size it 1200x1000
        frame.setSize(1200, 1000);
        frame.pack();
        frame.setVisible(true);
    }
}