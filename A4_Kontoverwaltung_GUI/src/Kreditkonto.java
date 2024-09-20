public class Kreditkonto extends Kontoklasse {
    public Kreditkonto(String kontoinhaber, String bankleitzahl, String kontonummer, double ueberziehungsrahmen, double kontostand, double kontofuehrungsgebuehren) {
        super(kontoinhaber, bankleitzahl, kontonummer, ueberziehungsrahmen,kontofuehrungsgebuehren, kontostand, "Kreditkonto");
    }
}