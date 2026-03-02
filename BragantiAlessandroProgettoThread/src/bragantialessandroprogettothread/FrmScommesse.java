package bragantialessandroprogettothread;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrmScommesse extends JFrame {

    //attributi grafici
    JLabel lblSaldo;
    JComboBox<String> cmbCorridori;
    JTextField txtImporto;
    JLabel lblQuota;//quota del corridore 
    JLabel lblVincita;//vincita potenziale
    JLabel lblSaldoDopoSc;  //saldo dopo la scommessa
    JButton btnScommetti;
    JButton btnChiudi;
    SistemaScommesse sistema;
    int numeroCorsori; //per calcolare la quota


    //costruttore
    public FrmScommesse(SistemaScommesse sistema, ArrayList<String> nomiCorridori) {
        this.sistema       = sistema;
        this.numeroCorsori = nomiCorridori.size();

        setTitle("💰 piazza una scommessa");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        lblSaldo      = new JLabel("saldo disponibile: " + formattaEuro(sistema.getSaldo()));
        lblSaldo.setFont(new Font("SansSerif", Font.BOLD, 12));

        cmbCorridori  = new JComboBox<String>();
        txtImporto    = new JTextField(10);

        lblQuota      = new JLabel("—");
        lblQuota.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblQuota.setForeground(new Color(0, 100, 180));

        lblVincita    = new JLabel("—");
        lblVincita.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblVincita.setForeground(new Color(0, 140, 0));

        lblSaldoDopoSc = new JLabel("—");
        lblSaldoDopoSc.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSaldoDopoSc.setForeground(Color.DARK_GRAY);

        btnScommetti  = new JButton("scommetti");
        btnChiudi     = new JButton("chiudi");

        // Popola la combo con i nomi dei corridori
        for (int i = 0; i < nomiCorridori.size(); i++) {
            cmbCorridori.addItem(nomiCorridori.get(i));
        }

        JPanel pannello = new JPanel(new GridBagLayout());
        pannello.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        //saldo disponibile
        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy = 0;
        pannello.add(lblSaldo, gbc);

        //separatore
        gbc.gridy = 1;
        pannello.add(new JSeparator(), gbc);

        //scommetti su
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 2;
        pannello.add(new JLabel("scommetti su:"), gbc);
        gbc.gridx = 1;
        pannello.add(cmbCorridori, gbc);

        //importo
        gbc.gridx = 0; gbc.gridy = 3;
        pannello.add(new JLabel("importo (€):"), gbc);
        gbc.gridx = 1;
        pannello.add(txtImporto, gbc);

        //separatore
        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy = 4;
        pannello.add(new JSeparator(), gbc);

        //quota
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 5;
        JLabel etQuota = new JLabel("quota:");
        etQuota.setFont(new Font("SansSerif", Font.PLAIN, 12));
        pannello.add(etQuota, gbc);
        gbc.gridx = 1;
        pannello.add(lblQuota, gbc);

        //vincita potenziale
        gbc.gridx = 0; gbc.gridy = 6;
        JLabel etVincita = new JLabel("vincita potenziale:");
        etVincita.setFont(new Font("SansSerif", Font.PLAIN, 12));
        pannello.add(etVincita, gbc);
        gbc.gridx = 1;
        pannello.add(lblVincita, gbc);

        //saldo dopo scommessa
        gbc.gridx = 0; gbc.gridy = 7;
        pannello.add(new JLabel("saldo dopo puntata:"), gbc);
        gbc.gridx = 1;
        pannello.add(lblSaldoDopoSc, gbc);

        //separatore
        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy = 8;
        pannello.add(new JSeparator(), gbc);

        //pulsanti
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 9;
        pannello.add(btnChiudi, gbc);
        gbc.gridx = 1;
        pannello.add(btnScommetti, gbc);

        add(pannello);
        pack();
        setMinimumSize(new Dimension(340, 0));

        //aggiorna subito la quota per il corridore
        aggiornaPreview();

        //eventi
        btnChiudi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnScommetti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliccaScommetti();
            }
        });

        //quando cambia il corridore nella combo ricalcola quota
        cmbCorridori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiornaPreview();
            }
        });

        //uando cambia l'importo ricalcola vincita e saldo
        txtImporto.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e)  { aggiornaPreview(); }
            @Override public void removeUpdate(DocumentEvent e)  { aggiornaPreview(); }
            @Override public void changedUpdate(DocumentEvent e) { aggiornaPreview(); }
        });
    }


    //metodi specifici

    //ricalcola e mostra quota, vincita potenziale e saldo post-scommessa
    void aggiornaPreview() {
        double quota = SistemaScommesse.calcolaQuota(numeroCorsori);
        lblQuota.setText("x" + String.format("%.2f", quota));

        //legge l'importo dal campo (se non è valido, mostra solo la quota)
        double importo = 0;
        try {
            importo = Double.parseDouble(txtImporto.getText().trim());
        } catch (NumberFormatException e) {
            lblVincita.setText("—");
            lblSaldoDopoSc.setText("—");
            return;
        }

        if (importo <= 0) {
            lblVincita.setText("—");
            lblSaldoDopoSc.setText("—");
            return;
        }

        //vincita potenziale
        double vincita = Math.round(importo * quota * 100.0) / 100.0;
        lblVincita.setText(formattaEuro(vincita));

        //saldo dopo la scommessa
        double saldoDopoScommessa = sistema.getSaldo() - importo;
        if (saldoDopoScommessa < 0) {
            lblSaldoDopoSc.setText("saldo insufficiente");
            lblSaldoDopoSc.setForeground(new Color(180, 0, 0));
        } else {
            lblSaldoDopoSc.setText(formattaEuro(saldoDopoScommessa) + "  (+" + formattaEuro(vincita) + " se vinci)");
            lblSaldoDopoSc.setForeground(Color.DARK_GRAY);
        }
    }

    //legge i campi, li valida e piazza la scommessa
    void cliccaScommetti() {
        String corridore = (String) cmbCorridori.getSelectedItem();

        double importo;
        try {
            importo = Double.parseDouble(txtImporto.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "inserisci un numero valido", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (importo <= 0) {
            JOptionPane.showMessageDialog(this, "l'importo deve essere positivo", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double quota = SistemaScommesse.calcolaQuota(numeroCorsori);

        boolean ok = sistema.piazzaScommessa(corridore, importo, quota);

        if (ok) {
            JOptionPane.showMessageDialog(this,
                "scommessa piazzata!\n" + sistema.toString(),
                "ok", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                "saldo insufficiente: hai " + formattaEuro(sistema.getSaldo()),
                "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Formatta un numero come "123,45 €"
    String formattaEuro(double valore) {
        String segno  = valore >= 0 ? "" : "-";
        double ass    = Math.abs(valore);
        long interi   = (long) ass;
        long dec      = Math.round((ass - interi) * 100);
        return segno + interi + "," + String.format("%02d", dec) + " €";
    }
}
