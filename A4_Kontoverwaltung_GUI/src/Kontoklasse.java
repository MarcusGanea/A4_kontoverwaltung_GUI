public class Kontoklasse {
    protected String kontoinhaber;
    protected String bankleitzahl;
    protected String kontonummer;
    protected double ueberziehungsrahmen;
    protected double kontofuehrungsgebuehren;
    protected double kontostand;
    protected String kontoart;

    public Kontoklasse(String kontoinhaber, String bankleitzahl, String kontonummer, double ueberziehungsrahmen, double kontofuehrungsgebuehren, double kontostand, String kontoart) {
        this.kontoinhaber = kontoinhaber;
        this.bankleitzahl = bankleitzahl;
        this.kontonummer = kontonummer;
        this.ueberziehungsrahmen = ueberziehungsrahmen;
        this.kontofuehrungsgebuehren = kontofuehrungsgebuehren;
        this.kontostand = kontostand;
        this.kontoart = kontoart;
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public void einzahlen(double betrag) {
        kontostand += betrag;
    }

    public void abheben(double betrag) {
        if (kontostand - betrag >= -ueberziehungsrahmen) {
            kontostand -= betrag;
        } else {
            System.out.println("Überziehungsrahmen überschritten!");
        }
    }

    public void kontoauszug() {
        System.out.println("================================");
        System.out.println("Kontoinhaber: " + kontoinhaber);
        System.out.println("Bankleitzahl: " + bankleitzahl);
        System.out.println("Kontonummer: " + kontonummer);
        System.out.println("Kontostand: " + kontostand);
        System.out.println("Kontoart: " + kontoart);
        System.out.println("================================");
    }


    public void kontoAufloesen() {
        System.out.println("Konto aufgelöst.");
        kontoinhaber = null;
        bankleitzahl = null;
        kontonummer = null;
        ueberziehungsrahmen = 0;
        kontofuehrungsgebuehren = 0;
        kontostand = 0;
        kontoart = null;
    }

    public void ueberweisen(Kontoklasse empfaenger, double betrag) {
        if (kontostand - betrag >= -ueberziehungsrahmen) {
            this.abheben(betrag);
            empfaenger.einzahlen(betrag);
        } else {
            System.out.println("Überziehungsrahmen überschritten!");
        }
    }
}