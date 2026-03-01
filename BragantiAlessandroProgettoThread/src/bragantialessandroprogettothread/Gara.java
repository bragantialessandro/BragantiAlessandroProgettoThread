package bragantialessandroprogettothread;

import java.util.ArrayList;

/*
 * GARA
 * Gestisce la lista dei corridori, li fa partire e registra l'ordine di arrivo.
 * Niente grafica qui.
 */
public class Gara {

    // --- ATTRIBUTI ---
    private int                  metriTotali;
    private ArrayList<Corridore> corridori;      // tutti i corridori iscritti
    private ArrayList<Corridore> ordineArrivo;   // i corridori nell'ordine in cui arrivano


    // --- COSTRUTTORE ---
    public Gara(int metriTotali) {
        this.metriTotali  = metriTotali;
        this.corridori    = new ArrayList<Corridore>();
        this.ordineArrivo = new ArrayList<Corridore>();
    }


    // --- GETTER ---
    public int                  getMetriTotali()   { return metriTotali; }
    public ArrayList<Corridore> getCorridori()     { return corridori; }
    public ArrayList<Corridore> getOrdineArrivo()  { return ordineArrivo; }


    // --- METODI SPECIFICI ---

    // Aggiunge un corridore alla lista degli iscritti
    public void aggiungiCorridore(Corridore c) {
        corridori.add(c);
    }

    // Fa partire tutti i corridori, ognuno su un Thread separato
    public void inizia() {
        for (int i = 0; i < corridori.size(); i++) {
            Thread t = new Thread(corridori.get(i));
            t.start();
        }
    }

    // Chiamato da un Corridore quando taglia il traguardo.
    // "synchronized" evita che due corridori ottengano la stessa posizione.
    public synchronized int registraArrivo(Corridore c) {
        ordineArrivo.add(c);
        return ordineArrivo.size();
    }

    // Restituisce il corridore vincitore (il primo arrivato), oppure null
    public Corridore getVincitore() {
        if (ordineArrivo.isEmpty()) return null;
        return ordineArrivo.get(0);
    }

    // Controlla se tutti i corridori hanno terminato.
    // Gli infortunati rallentano ma devono comunque arrivare al traguardo:
    // la gara finisce solo quando anche l'ultimo infortunato ha tagliato il traguardo.
    // Sono considerati "già finiti" solo invasione e falsa partenza (sbarrati).
    public boolean tuttaFinita() {
        for (int i = 0; i < corridori.size(); i++) {
            Corridore c = corridori.get(i);
            // sbarrati: non corrono più, li saltiamo
            if (c.isInvasione() || c.isFalsaPartenza()) continue;
            // tutti gli altri (sani o infortunati) devono essere arrivati
            if (!c.isArrivato()) return false;
        }
        return true;
    }


    // --- OVERRIDE ---
    @Override
    public boolean equals(Object altro) {
        if (altro == null) return false;
        if (!(altro instanceof Gara)) return false;
        Gara g = (Gara) altro;
        return this.metriTotali == g.metriTotali
            && this.corridori.equals(g.corridori);
    }

    @Override
    public String toString() {
        String testo = "=== CLASSIFICA FINALE (" + metriTotali + "m) ===\n\n";

        // Corridori che sono arrivati al traguardo
        for (int i = 0; i < ordineArrivo.size(); i++) {
            Corridore c = ordineArrivo.get(i);
            testo += (i + 1) + "° posto:  " + c.getNome() + " [pettorale " + c.getPettorale() + "]\n";
        }

        // Corridori che non hanno finito
        for (int i = 0; i < corridori.size(); i++) {
            Corridore c = corridori.get(i);
            if (!c.isArrivato()) {
                String motivo = "Non finito";
                if (c.isInvasione())    motivo = "Sbarrato per invasione";
                if (c.isFalsaPartenza()) motivo = "Sbarrato per falsa partenza";
                if (c.isInfortunato())  motivo = "Infortunato ma ha finito la gara";
                testo += "  ✗  " + c.getNome() + " [" + c.getPettorale() + "] → " + motivo + "\n";
            }
        }

        return testo;
    }
}
