package bragantialessandroprogettothread;

/*
 * SISTEMASCOMMESSE
 * Gestisce il saldo, la scommessa corrente e lo storico di tutte le gare.
 * Niente grafica qui.
 */
public class SistemaScommesse {

    // --- ATTRIBUTI ---
    private double saldo;
    private String corridoreScelto;    // nome del corridore su cui si è scommesso
    private double importoScommesso;
    private double quota;              // quota assegnata al corridore scelto (es. 2.50x)

    // Storico cumulativo (non si resetta mai)
    private int    totaleGare;
    private int    gareConScommessa;
    private int    vittorie;
    private int    sconfitte;
    private double guadagnoTotale;
    private double saldoIniziale;


    // --- COSTRUTTORE ---
    public SistemaScommesse(double saldoIniziale) {
        this.saldo            = saldoIniziale;
        this.saldoIniziale    = saldoIniziale;
        this.corridoreScelto  = null;
        this.importoScommesso = 0;
        this.quota            = 1.0;
        this.totaleGare       = 0;
        this.gareConScommessa = 0;
        this.vittorie         = 0;
        this.sconfitte        = 0;
        this.guadagnoTotale   = 0;
    }


    // --- GETTER E SETTER ---
    public double getSaldo()             { return saldo; }
    public void   setSaldo(double s)     { saldo = s; }

    public String getCorridoreScelto()   { return corridoreScelto; }
    public double getImportoScommesso()  { return importoScommesso; }
    public double getQuota()             { return quota; }

    public int    getTotaleGare()        { return totaleGare; }
    public int    getGareConScommessa()  { return gareConScommessa; }
    public int    getVittorie()          { return vittorie; }
    public int    getSconfitte()         { return sconfitte; }
    public double getGuadagnoTotale()    { return guadagnoTotale; }
    public double getSaldoIniziale()     { return saldoIniziale; }


    // --- METODI SPECIFICI ---

    // Calcola la quota per un corridore dato il numero totale di partecipanti.
    // Più corridori ci sono, più alta è la quota (e quindi la possibile vincita).
    // La quota base è proporzionale al numero di corridori, con un piccolo margine casuale.
    public static double calcolaQuota(int numeroCorsori) {
        if (numeroCorsori <= 1) return 1.5;
        // quota base: numero corridori * 0.85 (margine bookmaker del 15%)
        double base = numeroCorsori * 0.85;
        // arrotonda a 2 decimali
        base = Math.round(base * 100.0) / 100.0;
        return Math.max(1.10, base);
    }

    // Vincita potenziale = importo × quota (al netto dell'importo già scalato)
    public double getVincitaPotenziale() {
        return Math.round(importoScommesso * quota * 100.0) / 100.0;
    }

    // Saldo che avrebbe l'utente SE vincesse
    public double getSaldoSeVince() {
        return Math.round((saldo + getVincitaPotenziale()) * 100.0) / 100.0;
    }

    // Prova a piazzare una scommessa con una quota specifica.
    // Ritorna true se va bene, false se il saldo non è sufficiente.
    public boolean piazzaScommessa(String nomeCorridore, double importo, double quotaAssegnata) {
        if (importo <= 0)    return false;
        if (importo > saldo) return false;

        corridoreScelto  = nomeCorridore;
        importoScommesso = importo;
        quota            = quotaAssegnata;
        saldo            = saldo - importo;
        return true;
    }

    // Controlla se il corridore scelto ha vinto.
    public boolean haVinto(String nomeVincitore) {
        if (corridoreScelto == null) return false;
        return corridoreScelto.equals(nomeVincitore);
    }

    // Accredita la vincita (importo × quota) e aggiorna lo storico.
    // Da chiamare SOLO se haVinto() è true.
    public void accreditaVincita() {
        double vincita = getVincitaPotenziale();
        saldo          = Math.round((saldo + vincita) * 100.0) / 100.0;
        guadagnoTotale = Math.round((guadagnoTotale + (vincita - importoScommesso)) * 100.0) / 100.0;
        vittorie++;
    }

    // Registra una sconfitta nello storico.
    public void registraSconfitta() {
        guadagnoTotale = Math.round((guadagnoTotale - importoScommesso) * 100.0) / 100.0;
        sconfitte++;
    }

    // Chiamato a fine gara per aggiornare i contatori generali.
    public void registraFineGara(boolean avevaScommessa) {
        totaleGare++;
        if (avevaScommessa) gareConScommessa++;
    }

    // Percentuale di vittorie rispetto alle gare con scommessa.
    public int getPercentualeVittorie() {
        if (gareConScommessa == 0) return 0;
        return (vittorie * 100) / gareConScommessa;
    }

    // Azzera solo la scommessa corrente (lo storico rimane).
    public void resetScommessa() {
        corridoreScelto  = null;
        importoScommesso = 0;
        quota            = 1.0;
    }


    // --- OVERRIDE ---
    @Override
    public String toString() {
        if (corridoreScelto == null) {
            return "Nessuna scommessa — Saldo: " + saldo + " €";
        }
        return "Scommessa su: " + corridoreScelto
             + " | Importo: " + importoScommesso + " €"
             + " | Saldo attuale: " + saldo + " €";
    }
}
