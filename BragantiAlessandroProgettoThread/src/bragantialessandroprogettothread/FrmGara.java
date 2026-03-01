package bragantialessandroprogettothread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class FrmGara extends JFrame {
   

    //barra thread
    private javax.swing.JComboBox<String> cmbLunghezza;
    private javax.swing.JButton           btnAggiungiCorridore;
    private javax.swing.JButton           btnAvviaGara;
    private javax.swing.JButton           btnScommesse;
    private javax.swing.JLabel            lblTimer;
    private javax.swing.JPanel            pannelloControlli;
    private javax.swing.JLabel            lblLunghezza;

    //corridori
    private javax.swing.JPanel      pannelloBarre;
    private javax.swing.JScrollPane scrollBarre;

    //pannello madre scommesse e classifica
    private javax.swing.JPanel pannelloDestro;

    //pannello classifica
    private javax.swing.JPanel        pannelloClassifica;
    private javax.swing.JLabel        lblTitoloClassifica;
    private javax.swing.JSeparator    sepClass;
    private javax.swing.JPanel        pannelloRigheClass;  // righe dinamiche
    private javax.swing.JScrollPane   scrollClass;

    //pannello scommesse
    private javax.swing.JPanel      pannelloStats;
    private javax.swing.JLabel      lblTitoloStats;
    private javax.swing.JSeparator  sepStats1;
    private javax.swing.JSeparator  sepStats2;
    private javax.swing.JSeparator  sepStats3;

    //saldo
    private javax.swing.JLabel lblIntSaldo;
    private javax.swing.JLabel lblEtSaldoIniziale, lblEtSaldoAttuale, lblEtGuadagno;
    private javax.swing.JLabel lblStatSaldoIniziale, lblStatSaldoAttuale, lblStatGuadagno;

    //stats gare
    private javax.swing.JLabel lblIntGare;
    private javax.swing.JLabel lblEtGare, lblEtConScommessa, lblEtVittorie, lblEtSconfitte, lblEtPercentuale;
    private javax.swing.JLabel lblStatGare, lblStatConScommessa, lblStatVittorie, lblStatSconfitte, lblStatPercentuale;

    // Sezione Scommessa corrente
    private javax.swing.JLabel lblIntCorrente;
    private javax.swing.JLabel lblEtCorridore, lblEtImporto, lblEtQuota, lblEtVincita, lblEtSaldoSeVince;
    private javax.swing.JLabel lblStatCorridore, lblStatImporto, lblStatQuota, lblStatVincita, lblStatSaldoSeVince;

    //attributi
    Gara             gara;
    SistemaScommesse sistemaScommesse;
    ArrayList<String>       nomiCorridori;
    ArrayList<Integer>      pettorali;
    ArrayList<JProgressBar> barre;
    Timer timerGara;
    int   secondiPassati;


    
    //costruttore
    public FrmGara() {
        nomiCorridori    = new ArrayList<String>();
        pettorali        = new ArrayList<Integer>();
        barre            = new ArrayList<JProgressBar>();
        sistemaScommesse = new SistemaScommesse(500.0);
        secondiPassati   = 0;

        initComponents();
        aggiornaStatistiche();
    }


  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    @SuppressWarnings("unchecked")
    private void initComponents() {

        // ---- istanziazione ----
        lblLunghezza         = new javax.swing.JLabel();
        cmbLunghezza         = new javax.swing.JComboBox<>(new String[]{"5000m","10000m","21000m"});
        btnAggiungiCorridore = new javax.swing.JButton();
        btnAvviaGara         = new javax.swing.JButton();
        btnScommesse         = new javax.swing.JButton();
        lblTimer             = new javax.swing.JLabel();
        pannelloControlli    = new javax.swing.JPanel();
        pannelloBarre        = new javax.swing.JPanel();
        scrollBarre          = new javax.swing.JScrollPane();
        pannelloDestro       = new javax.swing.JPanel();

        // classifica
        pannelloClassifica   = new javax.swing.JPanel();
        lblTitoloClassifica  = new javax.swing.JLabel();
        sepClass             = new javax.swing.JSeparator();
        pannelloRigheClass   = new javax.swing.JPanel();
        scrollClass          = new javax.swing.JScrollPane();

        // stats scommesse
        pannelloStats        = new javax.swing.JPanel();
        lblTitoloStats       = new javax.swing.JLabel();
        sepStats1            = new javax.swing.JSeparator();
        sepStats2            = new javax.swing.JSeparator();
        sepStats3            = new javax.swing.JSeparator();
        lblIntSaldo          = new javax.swing.JLabel();
        lblEtSaldoIniziale   = new javax.swing.JLabel();
        lblEtSaldoAttuale    = new javax.swing.JLabel();
        lblEtGuadagno        = new javax.swing.JLabel();
        lblStatSaldoIniziale = new javax.swing.JLabel();
        lblStatSaldoAttuale  = new javax.swing.JLabel();
        lblStatGuadagno      = new javax.swing.JLabel();
        lblIntGare           = new javax.swing.JLabel();
        lblEtGare            = new javax.swing.JLabel();
        lblEtConScommessa    = new javax.swing.JLabel();
        lblEtVittorie        = new javax.swing.JLabel();
        lblEtSconfitte       = new javax.swing.JLabel();
        lblEtPercentuale     = new javax.swing.JLabel();
        lblStatGare          = new javax.swing.JLabel();
        lblStatConScommessa  = new javax.swing.JLabel();
        lblStatVittorie      = new javax.swing.JLabel();
        lblStatSconfitte     = new javax.swing.JLabel();
        lblStatPercentuale   = new javax.swing.JLabel();
        lblIntCorrente       = new javax.swing.JLabel();
        lblEtCorridore       = new javax.swing.JLabel();
        lblEtImporto         = new javax.swing.JLabel();
        lblEtQuota           = new javax.swing.JLabel();
        lblEtVincita         = new javax.swing.JLabel();
        lblEtSaldoSeVince    = new javax.swing.JLabel();
        lblStatCorridore     = new javax.swing.JLabel();
        lblStatImporto       = new javax.swing.JLabel();
        lblStatQuota         = new javax.swing.JLabel();
        lblStatVincita       = new javax.swing.JLabel();
        lblStatSaldoSeVince  = new javax.swing.JLabel();

        // ---- finestra ----
        setTitle("🏃 Simulatore Gara");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1350, 620));

        // ---- pannello controlli (NORTH) ----
        pannelloControlli.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 8));
        lblLunghezza.setText("Lunghezza:");
        pannelloControlli.add(lblLunghezza);
        pannelloControlli.add(cmbLunghezza);
        btnAggiungiCorridore.setText("➕ Aggiungi corridore");
        pannelloControlli.add(btnAggiungiCorridore);
        btnAvviaGara.setText("🏁 Avvia gara");
        btnAvviaGara.setEnabled(false);
        pannelloControlli.add(btnAvviaGara);
        btnScommesse.setText("💰 Scommesse");
        pannelloControlli.add(btnScommesse);
        lblTimer.setText("⏱ 0s");
        lblTimer.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));
        lblTimer.setForeground(new java.awt.Color(0, 100, 0));
        pannelloControlli.add(lblTimer);

        // ---- pannello barre (CENTER) ----
        pannelloBarre.setLayout(new javax.swing.BoxLayout(pannelloBarre, javax.swing.BoxLayout.Y_AXIS));
        pannelloBarre.setBackground(java.awt.Color.WHITE);
        scrollBarre.setViewportView(pannelloBarre);

        // ===========================================================
        // ---- PANNELLO CLASSIFICA (sinistra del destro) ----
        // ===========================================================
        pannelloClassifica.setLayout(new java.awt.BorderLayout());
        pannelloClassifica.setBackground(new java.awt.Color(250, 252, 245));
        pannelloClassifica.setBorder(
            javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 1, new java.awt.Color(180, 210, 180)),
                javax.swing.BorderFactory.createEmptyBorder(14, 12, 14, 12)
            )
        );
        pannelloClassifica.setPreferredSize(new java.awt.Dimension(200, 0));

        // Intestazione classifica
        javax.swing.JPanel headerClass = new javax.swing.JPanel(new java.awt.BorderLayout(0, 4));
        headerClass.setBackground(new java.awt.Color(250, 252, 245));
        lblTitoloClassifica.setText("🏆 Classifica");
        lblTitoloClassifica.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        lblTitoloClassifica.setForeground(new java.awt.Color(40, 100, 40));
        headerClass.add(lblTitoloClassifica, java.awt.BorderLayout.NORTH);
        headerClass.add(sepClass, java.awt.BorderLayout.SOUTH);
        pannelloClassifica.add(headerClass, java.awt.BorderLayout.NORTH);

        // Righe classifica (aggiornate dinamicamente)
        pannelloRigheClass.setLayout(new javax.swing.BoxLayout(pannelloRigheClass, javax.swing.BoxLayout.Y_AXIS));
        pannelloRigheClass.setBackground(new java.awt.Color(250, 252, 245));
        scrollClass.setViewportView(pannelloRigheClass);
        scrollClass.setBorder(null);
        scrollClass.setBackground(new java.awt.Color(250, 252, 245));
        scrollClass.getViewport().setBackground(new java.awt.Color(250, 252, 245));
        pannelloClassifica.add(scrollClass, java.awt.BorderLayout.CENTER);

        // =====================================================
        // ---- PANNELLO STATISTICHE SCOMMESSE (destra) ----
        // =====================================================
        pannelloStats.setBackground(new java.awt.Color(245, 248, 255));
        pannelloStats.setBorder(
            javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(180, 200, 230)),
                javax.swing.BorderFactory.createEmptyBorder(14, 16, 14, 16)
            )
        );
        pannelloStats.setPreferredSize(new java.awt.Dimension(280, 0));

        java.awt.Font fontTitolo = new java.awt.Font("SansSerif", java.awt.Font.BOLD,  13);
        java.awt.Font fontSez    = new java.awt.Font("SansSerif", java.awt.Font.BOLD,  12);
        java.awt.Font fontEt     = new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 11);
        java.awt.Font fontVal    = new java.awt.Font("SansSerif", java.awt.Font.BOLD,  11);
        java.awt.Font fontValGr  = new java.awt.Font("SansSerif", java.awt.Font.BOLD,  13);
        java.awt.Color colTitolo = new java.awt.Color(30, 60, 120);
        java.awt.Color colSez    = new java.awt.Color(60, 90, 150);
        java.awt.Color colEt     = java.awt.Color.DARK_GRAY;
        java.awt.Color colVal    = new java.awt.Color(30, 60, 120);

        lblTitoloStats.setText("💰 Statistiche Scommesse");
        lblTitoloStats.setFont(fontTitolo);
        lblTitoloStats.setForeground(colTitolo);

        lblIntSaldo.setText("💵 Saldo");
        lblIntSaldo.setFont(fontSez); lblIntSaldo.setForeground(colSez);
        impostaEt(lblEtSaldoIniziale, "Saldo iniziale:",  fontEt, colEt);
        impostaEt(lblEtSaldoAttuale,  "Saldo attuale:",   fontEt, colEt);
        impostaEt(lblEtGuadagno,      "Guadagno netto:",  fontEt, colEt);
        impostaVal(lblStatSaldoIniziale, "—", fontVal, colVal);
        impostaVal(lblStatSaldoAttuale,  "—", fontVal, colVal);
        impostaVal(lblStatGuadagno,      "—", fontVal, colVal);

        lblIntGare.setText("📊 Storico gare");
        lblIntGare.setFont(fontSez); lblIntGare.setForeground(colSez);
        impostaEt(lblEtGare,         "Gare totali:",   fontEt, colEt);
        impostaEt(lblEtConScommessa, "Con scommessa:", fontEt, colEt);
        impostaEt(lblEtVittorie,     "Vittorie 🏆:",   fontEt, colEt);
        impostaEt(lblEtSconfitte,    "Sconfitte 😞:",  fontEt, colEt);
        impostaEt(lblEtPercentuale,  "% vittorie:",    fontEt, colEt);
        impostaVal(lblStatGare,         "0", fontVal, colVal);
        impostaVal(lblStatConScommessa, "0", fontVal, colVal);
        impostaVal(lblStatVittorie,     "0", fontVal, colVal);
        impostaVal(lblStatSconfitte,    "0", fontVal, colVal);
        impostaVal(lblStatPercentuale,  "—", fontVal, colVal);

        lblIntCorrente.setText("🎯 Scommessa corrente");
        lblIntCorrente.setFont(fontSez); lblIntCorrente.setForeground(colSez);
        impostaEt(lblEtCorridore,    "Corridore:",          fontEt, colEt);
        impostaEt(lblEtImporto,      "Importo puntato:",    fontEt, colEt);
        impostaEt(lblEtQuota,        "Quota:",              fontEt, colEt);
        impostaEt(lblEtVincita,      "Vincita potenziale:", fontEt, colEt);
        impostaEt(lblEtSaldoSeVince, "Saldo se vinci:",     fontEt, colEt);
        impostaVal(lblStatCorridore,    "—", fontVal,   java.awt.Color.GRAY);
        impostaVal(lblStatImporto,      "—", fontVal,   java.awt.Color.GRAY);
        impostaVal(lblStatQuota,        "—", fontValGr, new java.awt.Color(0, 100, 180));
        impostaVal(lblStatVincita,      "—", fontValGr, new java.awt.Color(0, 140, 0));
        impostaVal(lblStatSaldoSeVince, "—", fontVal,   java.awt.Color.GRAY);

        // GroupLayout pannello stats
        javax.swing.GroupLayout gl = new javax.swing.GroupLayout(pannelloStats);
        pannelloStats.setLayout(gl);
        gl.setAutoCreateGaps(false);
        gl.setAutoCreateContainerGaps(false);
        gl.setHorizontalGroup(gl.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitoloStats)
            .addComponent(sepStats1)
            .addComponent(lblIntSaldo)
            .addGroup(riga(gl, lblEtSaldoIniziale,  lblStatSaldoIniziale))
            .addGroup(riga(gl, lblEtSaldoAttuale,   lblStatSaldoAttuale))
            .addGroup(riga(gl, lblEtGuadagno,       lblStatGuadagno))
            .addComponent(sepStats2)
            .addComponent(lblIntGare)
            .addGroup(riga(gl, lblEtGare,           lblStatGare))
            .addGroup(riga(gl, lblEtConScommessa,   lblStatConScommessa))
            .addGroup(riga(gl, lblEtVittorie,       lblStatVittorie))
            .addGroup(riga(gl, lblEtSconfitte,      lblStatSconfitte))
            .addGroup(riga(gl, lblEtPercentuale,    lblStatPercentuale))
            .addComponent(sepStats3)
            .addComponent(lblIntCorrente)
            .addGroup(riga(gl, lblEtCorridore,      lblStatCorridore))
            .addGroup(riga(gl, lblEtImporto,        lblStatImporto))
            .addGroup(riga(gl, lblEtQuota,          lblStatQuota))
            .addGroup(riga(gl, lblEtVincita,        lblStatVincita))
            .addGroup(riga(gl, lblEtSaldoSeVince,   lblStatSaldoSeVince))
        );
        int H = 22;
        gl.setVerticalGroup(gl.createSequentialGroup()
            .addComponent(lblTitoloStats)
            .addGap(4)
            .addComponent(sepStats1, 1, 1, 1)
            .addComponent(lblIntSaldo)
            .addGroup(rigaV(gl, lblEtSaldoIniziale,  lblStatSaldoIniziale,  H))
            .addGroup(rigaV(gl, lblEtSaldoAttuale,   lblStatSaldoAttuale,   H))
            .addGroup(rigaV(gl, lblEtGuadagno,       lblStatGuadagno,       H))
            .addGap(6)
            .addComponent(sepStats2, 1, 1, 1)
            .addComponent(lblIntGare)
            .addGroup(rigaV(gl, lblEtGare,           lblStatGare,           H))
            .addGroup(rigaV(gl, lblEtConScommessa,   lblStatConScommessa,   H))
            .addGroup(rigaV(gl, lblEtVittorie,       lblStatVittorie,       H))
            .addGroup(rigaV(gl, lblEtSconfitte,      lblStatSconfitte,      H))
            .addGroup(rigaV(gl, lblEtPercentuale,    lblStatPercentuale,    H))
            .addGap(6)
            .addComponent(sepStats3, 1, 1, 1)
            .addComponent(lblIntCorrente)
            .addGroup(rigaV(gl, lblEtCorridore,      lblStatCorridore,      H))
            .addGroup(rigaV(gl, lblEtImporto,        lblStatImporto,        H))
            .addGroup(rigaV(gl, lblEtQuota,          lblStatQuota,          H))
            .addGroup(rigaV(gl, lblEtVincita,        lblStatVincita,        H))
            .addGroup(rigaV(gl, lblEtSaldoSeVince,   lblStatSaldoSeVince,   H))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        // =====================================================
        // ---- PANNELLO DESTRO: classifica + scommesse ----
        // =====================================================
        pannelloDestro.setLayout(new java.awt.BorderLayout());
        pannelloDestro.add(pannelloClassifica, java.awt.BorderLayout.WEST);
        pannelloDestro.add(pannelloStats,      java.awt.BorderLayout.EAST);

        // ---- layout della finestra ----
        getContentPane().add(pannelloControlli, java.awt.BorderLayout.NORTH);
        getContentPane().add(scrollBarre,       java.awt.BorderLayout.CENTER);
        getContentPane().add(pannelloDestro,    java.awt.BorderLayout.EAST);

        // ---- listener ----
        btnAggiungiCorridore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { btnAggiungiCorridoreActionPerformed(evt); }
        });
        btnAvviaGara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { btnAvviaGaraActionPerformed(evt); }
        });
        btnScommesse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { btnScommesseActionPerformed(evt); }
        });

        pack();
        setLocationRelativeTo(null);
    }
    // </editor-fold>

    // Helpers per impostare label
    private void impostaEt(javax.swing.JLabel l, String t, java.awt.Font f, java.awt.Color c) {
        l.setText(t); l.setFont(f); l.setForeground(c);
    }
    private void impostaVal(javax.swing.JLabel l, String t, java.awt.Font f, java.awt.Color c) {
        l.setText(t); l.setFont(f); l.setForeground(c);
        l.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    }
    private javax.swing.GroupLayout.Group riga(javax.swing.GroupLayout gl,
            javax.swing.JLabel et, javax.swing.JLabel val) {
        return gl.createSequentialGroup()
            .addComponent(et)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
            .addComponent(val);
    }
    private javax.swing.GroupLayout.Group rigaV(javax.swing.GroupLayout gl,
            javax.swing.JLabel et, javax.swing.JLabel val, int h) {
        return gl.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(et,  h, h, h)
            .addComponent(val, h, h, h);
    }

    //gestione eventi
    private void btnAggiungiCorridoreActionPerformed(java.awt.event.ActionEvent evt) {
        new FrmCreaCorridore(this).setVisible(true);//così FrmCreaCorridore può richiamare aggiungiCorridoreDaForm()
    }
    private void btnAvviaGaraActionPerformed(java.awt.event.ActionEvent evt) {
        avviaGara();//avvio gara
    }
    private void btnScommesseActionPerformed(java.awt.event.ActionEvent evt) {
        new FrmScommesse(sistemaScommesse, nomiCorridori).setVisible(true);    //apre sezione scommesse,passa il sistema scommesse per azioni su saldo
        SwingUtilities.invokeLater(new Runnable() {
            public void run() { aggiornaStatistiche(); } //aggiorna i pannelli
        });
    }

    //metodi specifici
    public void aggiungiCorridoreDaForm(String nome, int pettorale) {
        nomiCorridori.add(nome);
        pettorali.add(pettorale);

        JProgressBar barra = new JProgressBar(0, 100);
        barra.setStringPainted(true);
        barra.setString("[" + pettorale + "] " + nome + "  0%");
        barre.add(barra);

        pannelloBarre.add(creaRiquadroCorridore(barra));
        pannelloBarre.revalidate();
        pannelloBarre.repaint();
        btnAvviaGara.setEnabled(true);
    }

    public boolean pettoraleGiaUsato(int pettorale) {
        for (int i = 0; i < pettorali.size(); i++) {
            if (pettorali.get(i) == pettorale) return true;
        }
        return false;
    }

    JPanel creaRiquadroCorridore(JProgressBar barra) {
        JPanel riquadro = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        riquadro.setBackground(Color.WHITE);
        riquadro.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        JLabel img = caricaImmagine();
        img.setPreferredSize(new Dimension(80, 80));
        barra.setPreferredSize(new Dimension(390, 34));
        barra.setFont(new Font("SansSerif", Font.PLAIN, 12));

        riquadro.add(img);
        riquadro.add(barra);
        return riquadro;
    }

    JLabel caricaImmagine() {
        String[] percorsi = {
            "img/corridore.png",
            "src/img/corridore.png",
            System.getProperty("user.dir") + "/img/corridore.png"
        };
        for (int i = 0; i < percorsi.length; i++) {
            File f = new File(percorsi[i]);
            if (f.exists()) {
                ImageIcon ic = new ImageIcon(f.getAbsolutePath());
                Image sc = ic.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                return new JLabel(new ImageIcon(sc));
            }
        }
        JLabel seg = new JLabel("🏃", SwingConstants.CENTER);
        seg.setOpaque(true);
        seg.setBackground(new Color(230, 230, 230));
        seg.setFont(new Font("SansSerif", Font.PLAIN, 32));
        seg.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return seg;
    }

    void avviaGara() {
        int metri = 5000;
        String scelta = (String) cmbLunghezza.getSelectedItem();
        if (scelta.equals("10000m")) metri = 10000;
        if (scelta.equals("21000m")) metri = 21000;

        gara = new Gara(metri);
        for (int i = 0; i < nomiCorridori.size(); i++) {
            gara.aggiungiCorridore(new Corridore(nomiCorridori.get(i), pettorali.get(i), metri, gara));
        }

        btnAvviaGara.setEnabled(false);
        btnAggiungiCorridore.setEnabled(false);
        cmbLunghezza.setEnabled(false);

        secondiPassati = 0;
        timerGara = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                secondiPassati++;
                lblTimer.setText("⏱ " + secondiPassati + "s");
            }
        });
        timerGara.start();

        gara.inizia();
        avviaThreadAggiornamento();
    }

    void avviaThreadAggiornamento() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                int elapsed = 0;
                while (elapsed < 200000) {
                    final ArrayList<Corridore> corridori = gara.getCorridori();
                    for (int i = 0; i < corridori.size(); i++) {
                        final Corridore c = corridori.get(i);
                        final JProgressBar b = barre.get(i);
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() { aggiornaBarraCorridore(c, b); }
                        });
                    }
                    // Aggiorna classifica e statistiche ogni ciclo
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            aggiornaClassifica();
                            aggiornaStatistiche();
                        }
                    });

                    if (gara.tuttaFinita()) break;
                    try { Thread.sleep(100); } catch (InterruptedException e) { break; }
                    elapsed += 100;
                }
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (timerGara != null) timerGara.stop();
                        aggiornaClassifica();   // aggiornamento finale
                        mostraRisultatiFinali();
                    }
                });
            }
        });
        t.setDaemon(true);
        t.start();
    }

    void aggiornaBarraCorridore(Corridore c, JProgressBar barra) {
        barra.setValue(c.getPercentuale());
        barra.setString(c.getTestoBarra());
        if      (c.isInvasione() || c.isFalsaPartenza())  barra.setForeground(new Color(200, 50, 50));
        else if (c.isInfortunato() && !c.isArrivato())    barra.setForeground(new Color(220, 140, 0));
        else if (c.isArrivato())                          barra.setForeground(new Color(30, 160, 30));
        else                                              barra.setForeground(new Color(70, 130, 200));
    }

    void mostraRisultatiFinali() {
        JOptionPane.showMessageDialog(this, gara.toString(), "🏁 Classifica finale", JOptionPane.INFORMATION_MESSAGE);

        Corridore vincitore    = gara.getVincitore();
        boolean avevaScommessa = sistemaScommesse.getCorridoreScelto() != null;
        String esito;

        if (vincitore != null && sistemaScommesse.haVinto(vincitore.getNome())) {
            sistemaScommesse.accreditaVincita();
            esito = "🎉 Hai vinto! +" + formattaEuro(sistemaScommesse.getVincitaPotenziale())
                  + "\nSaldo: " + formattaEuro(sistemaScommesse.getSaldo());
        } else if (avevaScommessa) {
            sistemaScommesse.registraSconfitta();
            esito = "😞 Hai perso! -" + formattaEuro(sistemaScommesse.getImportoScommesso())
                  + "\nSaldo: " + formattaEuro(sistemaScommesse.getSaldo());
        } else {
            esito = "Nessuna scommessa piazzata.";
        }

        sistemaScommesse.registraFineGara(avevaScommessa);
        JOptionPane.showMessageDialog(this, esito, "💰 Esito scommessa", JOptionPane.INFORMATION_MESSAGE);
        sistemaScommesse.resetScommessa();
        aggiornaStatistiche();
        resetInterfaccia();
    }

    void resetInterfaccia() {
        btnAggiungiCorridore.setEnabled(true);
        cmbLunghezza.setEnabled(true);
        btnAvviaGara.setEnabled(false);
        pannelloBarre.removeAll();
        pannelloBarre.revalidate();
        pannelloBarre.repaint();
        pannelloRigheClass.removeAll();
        pannelloRigheClass.revalidate();
        pannelloRigheClass.repaint();
        barre.clear();
        nomiCorridori.clear();
        pettorali.clear();
        secondiPassati = 0;
        lblTimer.setText("⏱ 0s");
    }


 
    //classifica in tempo reale e finale
    void aggiornaClassifica() {
        if (gara == null) return;

        ArrayList<Corridore> corridori = gara.getCorridori();

        //classifica ordinata (arrivati-in gara-espulsi)
        ArrayList<Corridore> arrivati  = new ArrayList<Corridore>();
        ArrayList<Corridore> inGara    = new ArrayList<Corridore>();
        ArrayList<Corridore> sbarrati  = new ArrayList<Corridore>();

        for (int i = 0; i < corridori.size(); i++) {
            Corridore c = corridori.get(i);
            if (c.isArrivato()) {
                arrivati.add(c);
            } else if (c.isInvasione() || c.isFalsaPartenza()) {
                sbarrati.add(c);
            } else {
                inGara.add(c);
            }
        }

        //ordina arrivati per posizione


        // ordina in inGara per chi è più avanti
        for (int i = 0; i < inGara.size() - 1; i++) {
            for (int j = i + 1; j < inGara.size(); j++) {
                if (inGara.get(j).getPercentuale() > inGara.get(i).getPercentuale()) {
                    Corridore tmp = inGara.get(i);
                    inGara.set(i, inGara.get(j));
                    inGara.set(j, tmp);
                }
            }
        }

        //ricostruisce la classifica finale
        ArrayList<Corridore> ordinati = new ArrayList<Corridore>();
        ordinati.addAll(arrivati);
        ordinati.addAll(inGara);
        ordinati.addAll(sbarrati);

        //svuota e riempie il pannello righe
        pannelloRigheClass.removeAll();

        for (int i = 0; i < ordinati.size(); i++) {
            Corridore c = ordinati.get(i);
            pannelloRigheClass.add(creaRigaClassifica(i + 1, c));
        }

        pannelloRigheClass.revalidate();
        pannelloRigheClass.repaint();
    }

    //singola riga della classifica
    JPanel creaRigaClassifica(int posizione, Corridore c) {
        JPanel riga = new JPanel(new BorderLayout(6, 0));
        riga.setOpaque(true);
        riga.setBorder(BorderFactory.createEmptyBorder(3, 4, 3, 4));

        //colora sfondo in base allo stato del corridore
        if (c.isArrivato()) {
            switch (c.getPosizione()) {
                case 1:
                    riga.setBackground(new Color(255, 223, 100)); //oro
                    break;
                case 2:
                    riga.setBackground(new Color(220, 220, 220)); //argento
                    break;
                case 3:
                    riga.setBackground(new Color(210, 170, 120)); //bronzo
                    break;
                default:
                    riga.setBackground(new Color(230, 245, 230)); //verde chiaro
                    break;
            }
        } else if (c.isInvasione() || c.isFalsaPartenza()) {
            riga.setBackground(new Color(255, 220, 220)); // rosso chiaro
        } else if (c.isInfortunato()) {
            riga.setBackground(new Color(255, 240, 200)); // arancione chiaro
        } else {
            riga.setBackground(posizione % 2 == 0
                ? new Color(250, 252, 245) : new Color(242, 248, 242));
        }

        //numero posizione
        String numTesto;
        if (c.isArrivato()) {
            switch (c.getPosizione()) {
                case 1:
                    numTesto = "🥇";
                    break;
                case 2:
                    numTesto = "🥈";
                    break;
                case 3:
                    numTesto = "🥉";
                    break;
                default:
                    numTesto = c.getPosizione() + "°";
                    break;
            }
        } else if (c.isInvasione())     { numTesto = "🚫"; }
        else if (c.isFalsaPartenza())   { numTesto = "❌"; }
        else                            { numTesto = posizione + "°"; }

        JLabel lblPos = new JLabel(numTesto);
        lblPos.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblPos.setPreferredSize(new Dimension(28, 20));
        lblPos.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel lblNome = new JLabel(c.getNome());
        lblNome.setFont(new Font("SansSerif", Font.PLAIN, 11));

        //percentuale
        String statTesto;
        if (c.isArrivato())         statTesto = "✓";
        else if (c.isInvasione())   statTesto = "invasione";
        else if (c.isFalsaPartenza()) statTesto = "falsa part.";
        else                         statTesto = c.getPercentuale() + "%";

        JLabel lblStat = new JLabel(statTesto);
        lblStat.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblStat.setHorizontalAlignment(SwingConstants.RIGHT);

        // Separatore sotto ogni riga
        riga.add(lblPos,  BorderLayout.WEST);
        riga.add(lblNome, BorderLayout.CENTER);
        riga.add(lblStat, BorderLayout.EAST);

        // Bordo inferiore sottile
        riga.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 220, 200)),
            BorderFactory.createEmptyBorder(4, 4, 4, 6)
        ));

        return riga;
    }

    //aggiornamenti pannello statistiche
    void aggiornaStatistiche() {
        lblStatSaldoIniziale.setText(formattaEuro(sistemaScommesse.getSaldoIniziale()));
        lblStatSaldoAttuale.setText(formattaEuro(sistemaScommesse.getSaldo()));

        double g = sistemaScommesse.getGuadagnoTotale();
        lblStatGuadagno.setText(formattaEuro(g));
        lblStatGuadagno.setForeground(g > 0
            ? new Color(0, 140, 0) : g < 0
            ? new Color(180, 0, 0) : new Color(30, 60, 120));

        lblStatGare.setText(String.valueOf(sistemaScommesse.getTotaleGare()));
        lblStatConScommessa.setText(String.valueOf(sistemaScommesse.getGareConScommessa()));

        lblStatVittorie.setText(String.valueOf(sistemaScommesse.getVittorie()));
        lblStatVittorie.setForeground(sistemaScommesse.getVittorie() > 0
            ? new Color(0, 140, 0) : new Color(30, 60, 120));

        lblStatSconfitte.setText(String.valueOf(sistemaScommesse.getSconfitte()));
        lblStatSconfitte.setForeground(sistemaScommesse.getSconfitte() > 0
            ? new Color(180, 0, 0) : new Color(30, 60, 120));

        lblStatPercentuale.setText(sistemaScommesse.getGareConScommessa() == 0
            ? "—" : sistemaScommesse.getPercentualeVittorie() + "%");

        String corridore = sistemaScommesse.getCorridoreScelto();
        if (corridore == null) {
            Color g2 = Color.GRAY;
            lblStatCorridore.setText("—");    lblStatCorridore.setForeground(g2);
            lblStatImporto.setText("—");      lblStatImporto.setForeground(g2);
            lblStatQuota.setText("—");        lblStatQuota.setForeground(g2);
            lblStatVincita.setText("—");      lblStatVincita.setForeground(g2);
            lblStatSaldoSeVince.setText("—"); lblStatSaldoSeVince.setForeground(g2);
        } else {
            Color ar = new Color(180, 100, 0);
            lblStatCorridore.setText(corridore);
            lblStatCorridore.setForeground(ar);
            lblStatImporto.setText(formattaEuro(sistemaScommesse.getImportoScommesso()));
            lblStatImporto.setForeground(ar);
            lblStatQuota.setText("x" + String.format("%.2f", sistemaScommesse.getQuota()));
            lblStatQuota.setForeground(new Color(0, 100, 180));
            lblStatVincita.setText(formattaEuro(sistemaScommesse.getVincitaPotenziale()));
            lblStatVincita.setForeground(new Color(0, 140, 0));
            lblStatSaldoSeVince.setText(formattaEuro(sistemaScommesse.getSaldoSeVince()));
            lblStatSaldoSeVince.setForeground(new Color(30, 60, 120));
        }

        pannelloStats.revalidate();
        pannelloStats.repaint();
    }

    String formattaEuro(double valore) {
        String segno = valore >= 0 ? "" : "-";
        double ass   = Math.abs(valore);
        long interi  = (long) ass;
        long dec     = Math.round((ass - interi) * 100);
        return segno + interi + "," + String.format("%02d", dec) + " €";
    }
}
