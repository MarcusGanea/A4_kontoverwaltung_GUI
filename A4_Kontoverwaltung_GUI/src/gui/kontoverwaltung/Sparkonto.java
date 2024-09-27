package gui.kontoverwaltung;

public class Sparkonto extends Kontoklasse {
    private double zinsen;

    public Sparkonto(String kontoinhaber, String bankleitzahl, String kontonummer, double kontostand, double zinsen) {
        super(kontoinhaber, bankleitzahl, kontonummer, 0, zinsen, kontostand, "Sparkonto");
        this.zinsen = zinsen;
    }

    @Override
    public double getGebuehrenZinsen() {
        return zinsen;
    }
}