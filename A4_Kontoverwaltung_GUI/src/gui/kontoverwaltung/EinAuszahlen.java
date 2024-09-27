package gui.kontoverwaltung;

import javax.swing.*;

public class EinAuszahlen {
    private JRadioButton einzahlungRB;
    private JRadioButton auszahlungRB;
    private JTextField BetragTF;
    private JButton abbrechenBTN;
    private JButton bestaetigenBTN;
    private JPanel jpanel;
    private JComboBox<Kontoklasse> kontoCB;


    public EinAuszahlen(Kontoverwaltung_GUI kontoverwaltung_gui) {
        JFrame frame = new JFrame("Ein-/Auszahlung");
        frame.setContentPane(jpanel);
        frame.setSize(1200, 1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        einzahlungRB.setSelected(true);

        for (Kontoklasse konto : kontoverwaltung_gui.getKontoList()) {
            kontoCB.addItem(konto);
        }

        einzahlungRB.addActionListener(e -> {
            auszahlungRB.setSelected(false);
        });

        auszahlungRB.addActionListener(e -> {
            einzahlungRB.setSelected(false);
        });


        bestaetigenBTN.addActionListener(e -> {
            try {
                Kontoklasse konto = (Kontoklasse) kontoCB.getSelectedItem();
                if (konto == null) {
                    JOptionPane.showMessageDialog(null, "Bitte wählen Sie ein Konto aus.");
                    return;
                }
                double betrag = Double.parseDouble(BetragTF.getText());
                if (einzahlungRB.isSelected()) {
                    double alterKontostand = konto.kontostand;
                    konto.einzahlen(betrag);
                    JTextArea textArea1 = kontoverwaltung_gui.getTextArea1();
                    textArea1.append("Einzahlung " + konto.kontonummer + " - " +  konto.kontoinhaber + ": " + betrag + " €\n");
                    textArea1.append("Kontostand: " + alterKontostand + " + " + betrag + " = " + konto.kontostand  + " €\n\n");
                    kontoverwaltung_gui.setTextArea1(textArea1);
                    JOptionPane.showMessageDialog(null, "Einzahlung: " + betrag);
                } else if (auszahlungRB.isSelected()) {
                    double alterKontostand = konto.kontostand;
                    konto.auszahlen(betrag);
                    JTextArea textArea1 = kontoverwaltung_gui.getTextArea1();
                    textArea1.append("Auszahlung " + konto.kontonummer + " - " +  konto.kontoinhaber + ": " + betrag + " €\n");
                    textArea1.append("Kontostand: " + alterKontostand + " - " + betrag + " = " + konto.kontostand  + " €\n\n");
                    JOptionPane.showMessageDialog(null, "Auszahlung: " + betrag);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Bitte geben Sie einen gültigen Betrag ein.");
            }
        });
        abbrechenBTN.addActionListener(e -> {
            frame.dispose();
        });
        frame.pack();
        frame.setVisible(true);
    }
}
