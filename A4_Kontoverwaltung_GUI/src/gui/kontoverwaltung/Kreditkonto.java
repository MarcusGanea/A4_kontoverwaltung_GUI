package gui.kontoverwaltung;

public class Kreditkonto extends Kontoklasse {
    private double zinsen;

    public Kreditkonto(String kontoinhaber, String bankleitzahl, String kontonummer, double kreditlimit, double kontostand, double zinsen) {
        super(kontoinhaber, bankleitzahl, kontonummer, kreditlimit, 0, kontostand, "Kreditkonto");
        this.zinsen = zinsen;
    }

    @Override
    public double getGebuehrenZinsen() {
        return zinsen;
    }
}