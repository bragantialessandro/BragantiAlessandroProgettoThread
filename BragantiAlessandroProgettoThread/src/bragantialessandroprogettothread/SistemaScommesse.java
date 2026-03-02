package bragantialessandroprogettothread;

public class SistemaScommesse {

    //attributi
    private double saldo;
    private String corridoreScelto;    //nome del corridore su cui si è scommesso
    private double importoScommesso;
    private double quota;              //quota assegnata al corridore 

    //storico cumulativo
    private int    totaleGare;
    private int    gareConScommessa;
    private int    vittorie;
    private int    sconfitte;
    private double guadagnoTotale;
    private double saldoIniziale;


    //costruttore
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


    //g&s
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


    //metodi specifici

    //calcola la quota per un corridore sul numero totale di partecipanti
    public static double calcolaQuota(int numeroCorsori) {
        if (numeroCorsori <= 1) return 1.5;
        //quota base
        double base = numeroCorsori * 0.85;
        //arrotonda a 2 decimali
        base = Math.round(base * 100.0) / 100.0;
        return Math.max(1.10, base);
    }

    //vincita potenziale
    public double getVincitaPotenziale() {
        return Math.round(importoScommesso * quota * 100.0) / 100.0;
    }

    //saldo che avrebbe l'utente se vincesse
    public double getSaldoSeVince() {
        return Math.round((saldo + getVincitaPotenziale()) * 100.0) / 100.0;
    }

    //prova a piazzare una scommessa con una quota specifica,ritorna true se va bene, false se il saldo non è sufficiente.
    public boolean piazzaScommessa(String nomeCorridore, double importo, double quotaAssegnata) {
        if (importo <= 0)    return false;
        if (importo > saldo) return false;

        corridoreScelto  = nomeCorridore;
        importoScommesso = importo;
        quota            = quotaAssegnata;
        saldo            = saldo - importo;
        return true;
    }

    //controlla se il corridore scelto ha vinto
    public boolean haVinto(String nomeVincitore) {
        if (corridoreScelto == null) return false;
        return corridoreScelto.equals(nomeVincitore);
    }

    //accredita la vincita e aggiorna lo storico, se haVinto() è true
    public void accreditaVincita() {
        double vincita = getVincitaPotenziale();
        saldo          = Math.round((saldo + vincita) * 100.0) / 100.0;
        guadagnoTotale = Math.round((guadagnoTotale + (vincita - importoScommesso)) * 100.0) / 100.0;
        vittorie++;
    }

    //registra una sconfitta nello storico
    public void registraSconfitta() {
        guadagnoTotale = Math.round((guadagnoTotale - importoScommesso) * 100.0) / 100.0;
        sconfitte++;
    }

    //chiamato a fine gara per aggiornare i contatori generali
    public void registraFineGara(boolean avevaScommessa) {
        totaleGare++;
        if (avevaScommessa) gareConScommessa++;
    }

    //percentuale di vittorie rispetto alle gare con scommessa
    public int getPercentualeVittorie() {
        if (gareConScommessa == 0) return 0;
        return (vittorie * 100) / gareConScommessa;
    }

    //azzera solo la scommessa corrente
    public void resetScommessa() {
        corridoreScelto  = null;
        importoScommesso = 0;
        quota            = 1.0;
    }
    
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
