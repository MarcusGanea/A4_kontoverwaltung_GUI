package gui.kontoverwaltung;

import javax.swing.*;

public class Ueberweisen {
    private JComboBox<Kontoklasse> kontoCB;
    private JComboBox<Kontoklasse> konto2CB;
    private JTextField betragTF;
    private JButton bestaetigenBTN;
    private JButton abbrechenBTN;
    private JPanel jpanel; // Ensure this is the correct name for the JPanel

    public Ueberweisen(Kontoverwaltung_GUI kontoverwaltung_gui) {
        JFrame frame = new JFrame("Überweisen");
        frame.setContentPane(jpanel); // Correct the variable name to jpanel
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (Kontoklasse konto : kontoverwaltung_gui.getKontoList()) {
            kontoCB.addItem(konto);
            konto2CB.addItem(konto);
        }

        bestaetigenBTN.addActionListener(e -> {
            try {
                Kontoklasse konto = (Kontoklasse) kontoCB.getSelectedItem();
                Kontoklasse konto2 = (Kontoklasse) konto2CB.getSelectedItem();
                double betrag = Double.parseDouble(betragTF.getText());

                if (konto == null || konto2 == null) {
                    JOptionPane.showMessageDialog(null, "Bitte wählen Sie ein Konto aus.");
                    return;
                }
                if (konto == konto2) {
                    JOptionPane.showMessageDialog(null, "Konten müssen unterschiedlich sein.");
                    return;
                }
                if (konto.kontostand < betrag) {
                    JOptionPane.showMessageDialog(null, "Kontostand reicht nicht aus.");
                    return;
                }

                double alterKontostand = konto.kontostand;
                double alterKontostand2 = konto2.kontostand;

                this.ueberweisen(konto, konto2, betrag);

                JTextArea textArea1 = kontoverwaltung_gui.getTextArea1();
                textArea1.append("Überweisung von " + konto.kontonummer + " - " + konto.kontoinhaber + " an " + konto2.kontonummer + " - " + konto2.kontoinhaber + ": " + betrag + " €\n");
                textArea1.append("Kontostand " + konto.kontonummer + ": "+konto.kontoinhaber+" : " + alterKontostand + " - " + betrag + " = " + konto.kontostand + " €\n");
                textArea1.append("Kontostand " + konto2.kontonummer + ": "+ konto2.kontoinhaber + " : " + alterKontostand2 + " + " + betrag + " = " + konto2.kontostand + " €\n\n");
                kontoverwaltung_gui.setTextArea1(textArea1);
                JOptionPane.showMessageDialog(null, "Überweisung: " + betrag);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Fehler: " + ex.getMessage());
            }
        });

        abbrechenBTN.addActionListener(e -> {
            frame.dispose();
        });

        frame.setVisible(true); // Make the frame visible after setting everything up
    }

    private void ueberweisen (Kontoklasse sender,  Kontoklasse empfaenger, double betrag) {
        sender.auszahlen(betrag);
        empfaenger.einzahlen(betrag);
    }
}