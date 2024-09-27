package gui.kontoverwaltung;

public abstract class Kontoklasse {
    protected String kontoinhaber;
    protected String kontonummer;
    protected double kontostand;
    protected String kontoart; // Added field

    public Kontoklasse(String kontoinhaber, String bankleitzahl, String kontonummer, double kreditlimit, double gebuehrenZinsen, double kontostand, String kontoart) {
        this.kontoinhaber = kontoinhaber;
        this.kontonummer = kontonummer;
        this.kontostand = kontostand;
        this.kontoart = kontoart; // Initialize field
    }

    public String getKontoinhaber() {
        return kontoinhaber;
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public double getKontostand() {
        return kontostand;
    }

    public String getKontoart() { // Added getter method
        return kontoart;
    }

    public void einzahlen(double betrag) {
        kontostand += betrag;
    }

    public void auszahlen(double betrag) {
        kontostand -= betrag;
    }

    public abstract double getGebuehrenZinsen();

    public String kontoauszug() {
        return "Kontonummer: " + kontonummer + "\n" +
                "Kontoart: " + getKontoart() + "\n" +
                "Kontoinhaber: " + kontoinhaber + "\n" +
                "Kontostand: " + kontostand + "\n" +
                "Gebühren/Zinsen: " + getGebuehrenZinsen() + "\n";
    }

    @Override
    public String toString() {
        return kontonummer + " - " + kontoinhaber;
    }
}