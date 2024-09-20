public class Sparkonto extends Kontoklasse {
    public Sparkonto(String kontoinhaber, String bankleitzahl, String kontonummer, double kontostand, double kontofuehrungsgebueren) {
        super(kontoinhaber, bankleitzahl, kontonummer, 0,kontofuehrungsgebueren, kontostand, "Sparkonto");
    }
}