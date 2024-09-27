package gui.kontoverwaltung;

public class Girokonto extends Kontoklasse {
    private double gebuehren;

    public Girokonto(String kontoinhaber, String bankleitzahl, String kontonummer, double dispo, double gebuehren, double kontostand) {
        super(kontoinhaber, bankleitzahl, kontonummer, dispo, gebuehren, kontostand, "Girokonto");
        this.gebuehren = gebuehren;
    }

    @Override
    public double getGebuehrenZinsen() {
        return gebuehren;
    }
}