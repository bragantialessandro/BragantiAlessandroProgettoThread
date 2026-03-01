package bragantialessandroprogettothread;

import javax.swing.SwingUtilities;

public class BragantiAlessandroProgettoThread {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrmGara().setVisible(true);
            }
        });
    }
}
