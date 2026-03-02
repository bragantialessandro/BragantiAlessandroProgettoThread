package bragantialessandroprogettothread;

import java.util.ArrayList;
public class Gara {

    //attributi
    private int                  metriTotali;
    private ArrayList<Corridore> corridori;      // tutti i corridori iscritti
    private ArrayList<Corridore> ordineArrivo;   // i corridori nell'ordine in cui arrivano


    //xostruttore
    public Gara(int metriTotali) {
        this.metriTotali  = metriTotali;
        this.corridori    = new ArrayList<Corridore>();
        this.ordineArrivo = new ArrayList<Corridore>();
    }


    //getter
    public int                  getMetriTotali()   { return metriTotali; }
    public ArrayList<Corridore> getCorridori()     { return corridori; }
    public ArrayList<Corridore> getOrdineArrivo()  { return ordineArrivo; }


    //metodi specifici

    //aggiunge un corridore alla lista
    public void aggiungiCorridore(Corridore c) {
        corridori.add(c);
    }

    //fa partire tutti i corridori, ognuno su un Thread separato
    public void inizia() {
        for (int i = 0; i < corridori.size(); i++) {
            Thread t = new Thread(corridori.get(i));
            t.start();
        }
    }

    // chiamato da un Corridore quando taglia il traguardo,evita che due corridori hanno la stessa posizione(sincornized)
    public synchronized int registraArrivo(Corridore c) {
        ordineArrivo.add(c);
        return ordineArrivo.size();
    }

    //restituisce il corridore vincitore o null
    public Corridore getVincitore() {
        if (ordineArrivo.isEmpty()) return null;
        return ordineArrivo.get(0);
    }

    //controlla se tutti i corridori hanno terminato
    public boolean tuttaFinita() {
        for (int i = 0; i < corridori.size(); i++) {
            Corridore c = corridori.get(i);
            //sbarrati: non corrono più, li saltiamo
            if (c.isInvasione() || c.isFalsaPartenza()) continue;
            //sani o infortunati devono essere arrivati
            if (!c.isArrivato()) return false;
        }
        return true;
    }


    //override
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

        //corridori che sono arrivati al traguardo
        for (int i = 0; i < ordineArrivo.size(); i++) {
            Corridore c = ordineArrivo.get(i);
            testo += (i + 1) + "° posto:  " + c.getNome() + " [pettorale " + c.getPettorale() + "]\n";
        }

        //corridori che non hanno finito
        for (int i = 0; i < corridori.size(); i++) {
            Corridore c = corridori.get(i);
            if (!c.isArrivato()) {
                String motivo = "Non finito";
                if (c.isInvasione())    motivo = "espulso per invasione";
                if (c.isFalsaPartenza()) motivo = "espulso per falsa partenza";
                if (c.isInfortunato())  motivo = "Infortunato ma ha finito la gara";
                testo += "  ✗  " + c.getNome() + " [" + c.getPettorale() + "] → " + motivo + "\n";
            }
        }

        return testo;
    }
}
