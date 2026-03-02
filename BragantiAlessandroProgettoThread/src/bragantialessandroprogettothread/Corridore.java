package bragantialessandroprogettothread;

import java.util.Random;

/*
 * CORRIDORE
 * Rappresenta un corridore che corre su un Thread separato.
 * Gestisce la logica della corsa: avanzamento, eventi casuali, arrivo.
 */
public class Corridore implements Runnable {

    //attributi
    private String  nome;
    private int     pettorale;
    private int     metriTotali;
    private int     metriFatti;
    private int     posizione;

    private boolean arrivato;
    private boolean invasione;
    private boolean infortunato;   // se infortunato corre più lento ma non si ferma
    private boolean falsaPartenza;

    private Gara   gara;
    private Random dado = new Random();


    //costruttore parametrizzato
    public Corridore(String nome, int pettorale, int metriTotali, Gara gara) {
        this.nome         = nome;
        this.pettorale    = pettorale;
        this.metriTotali  = metriTotali;
        this.metriFatti   = 0;
        this.posizione    = 0;
        this.arrivato     = false;
        this.invasione    = false;
        this.infortunato  = false;
        this.falsaPartenza = false;
        this.gara         = gara;
    }


    //g&s
    public String  getNome()             { return nome; }
    public void    setNome(String n)     { nome = n; }

    public int     getPettorale()        { return pettorale; }
    public void    setPettorale(int p)   { pettorale = p; }

    public int     getMetriTotali()      { return metriTotali; }
    public void    setMetriTotali(int m) { metriTotali = m; }

    public int     getMetriFatti()       { return metriFatti; }

    public int     getPosizione()        { return posizione; }
    public void    setPosizione(int p)   { posizione = p; }

    public boolean isArrivato()          { return arrivato; }
    public boolean isInvasione()         { return invasione; }
    public boolean isInfortunato()       { return infortunato; }
    public boolean isFalsaPartenza()     { return falsaPartenza; }


    //metodi specifici
    //restituisce la percentuale di avanzamento
    public int getPercentuale() {
        if (metriTotali == 0) return 0;
        return (metriFatti * 100) / metriTotali;
    }

    //testo da mostrare sulla barra di avanzamento
    public String getTestoBarra() {
        if (falsaPartenza) return "❌ [" + pettorale + "] " + nome + " - falsa partenza";
        if (invasione)     return "🚫 [" + pettorale + "] " + nome + " - nvasione di corsia";
        if (infortunato && !arrivato)
                           return "🤕 [" + pettorale + "] " + nome + " infortunato -" + getPercentuale() + "%";
        if (arrivato)      return "🏅 [" + pettorale + "] " + nome + " - " + posizione + "° posto!";
        return "[" + pettorale + "] " + nome + "  " + getPercentuale() + "%";
    }

    //pausa del thread 
    private void aspetta(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    //metodo run (logica corsa)
    @Override
    public void run() {

        //falsa partenza con 1% 
        if (dado.nextInt(100) < 1) {
            falsaPartenza = true;
            return;
        }

        //eventi decisi prima
        //invasione di corsia (7%)
        boolean avraInvasione = dado.nextInt(100) < 7;

        //scivolamento (8%)
        boolean avraScivolata = !avraInvasione && dado.nextInt(100) < 8;

        //infortunio (5%)
        boolean avraInfortunio = !avraInvasione && !avraScivolata && dado.nextInt(100) < 5;

        //quando che percentuale di gara avverrà l'evento (10%<x<90%)
        int percentualeEvento = 10 + dado.nextInt(80);
        boolean eventoScatenato = false;

        //andamento corsa
        while (metriFatti < metriTotali) {

            //controlla se è il momento di scatenare l'evento
            if (!eventoScatenato && getPercentuale() >= percentualeEvento) {
                eventoScatenato = true;

                if (avraInvasione) {
                    invasione = true;
                    return;   //esce subito
                }
                if (avraScivolata) {
                    aspetta(2000);   //pausa di 2 secondi poi riprende
                }
                if (avraInfortunio) {
                    infortunato = true;   //rallenta ma continua
                }
            }

            //calcolo andamento
            double velocitaBase = metriTotali / 60.0;

            //variazione casuale +-30%
            double variazione = 0.70 + dado.nextDouble() * 0.60;

            //se infortunato corre a metà della velocità normale
            if (infortunato) {
                variazione = variazione * 0.5;
            }

            double velocita = velocitaBase * variazione;

            //pausa tra step
            int pausaMs = 80 + dado.nextInt(120);

            //distanza percorsa in questo step
            int passo = (int)(velocita * pausaMs / 1000.0);
            metriFatti = metriFatti + passo;

            //non superare il traguardo
            if (metriFatti > metriTotali) {
                metriFatti = metriTotali;
            }

            aspetta(pausaMs);
        }

        //è arrivato
        arrivato  = true;
        posizione = gara.registraArrivo(this);
    }
    @Override
    public boolean equals(Object altro) {
        if (altro == null) return false;
        if (!(altro instanceof Corridore)) return false;
        Corridore c = (Corridore) altro;
        return this.pettorale == c.pettorale;
    }

    @Override
    public String toString() {
        if (falsaPartenza) return "[" + pettorale + "] " + nome + " — falsa partenza";
        if (invasione)     return "[" + pettorale + "] " + nome + " — invasione di corsia";
        if (arrivato)      return "[" + pettorale + "] " + nome + " — " + posizione + "° posto";
        if (infortunato)   return "[" + pettorale + "] " + nome + " — infortunato";
        return "[" + pettorale + "] " + nome + " - in gara";
    }
}
