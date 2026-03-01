package bragantialessandroprogettothread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * FRMCREACORRIDORE - finestra per aggiungere un corridore alla gara.
 * Solo grafica e controllo dell'input.
 * La logica di aggiunta sta in FrmGara.
 */
public class FrmCreaCorridore extends JFrame {

    //attributi
    JTextField txtNome;
    JTextField txtPettorale;
    JButton    btnAggiungi;
    JButton    btnAnnulla;
    FrmGara frmGara;


    //costruttore
    public FrmCreaCorridore(FrmGara frmGara) {
        this.frmGara = frmGara;

        setTitle("aggiungi corridore");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        txtNome      = new JTextField(20);
        txtPettorale = new JTextField(20);
        btnAggiungi  = new JButton("aggiungi");
        btnAnnulla   = new JButton("annulla");

        //layuot
        JPanel pannello = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        pannello.add(new JLabel("nome:"), gbc);
        gbc.gridx = 1;
        pannello.add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        pannello.add(new JLabel("pettorale:"), gbc);
        gbc.gridx = 1;
        pannello.add(txtPettorale, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        pannello.add(btnAnnulla, gbc);
        gbc.gridx = 1;
        pannello.add(btnAggiungi, gbc);

        add(pannello);
        pack();

        //eventi
        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnAggiungi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliccaAggiungi();
            }
        });
    }


    //metodi specifici

    //legge i campi,li valida e aggiunge il corridore
    void cliccaAggiungi() {
        String nome = txtNome.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "il nome non può essere vuoto", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int pettorale;
        try {
            pettorale = Integer.parseInt(txtPettorale.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "il pettorale deve essere un numero", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (pettorale <= 0) {
            JOptionPane.showMessageDialog(this, "il pettorale deve essere maggiore di zero", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //il pettorale è preso
        if (frmGara.pettoraleGiaUsato(pettorale)) {
            JOptionPane.showMessageDialog(this, "il pettorale " + pettorale + " è già usato", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //se tutto ok aggiunge il corridore e chiude
        frmGara.aggiungiCorridoreDaForm(nome, pettorale);
        dispose();
    }
}
