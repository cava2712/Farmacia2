package Esame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registrazione extends JFrame implements ActionListener {
    private final JTextField TextNome;
    private final JPasswordField TextPass;
    private final JTextField TextCognome;
    private final JTextField TextEmail;
    private final JTextField TextCF;
    private final JButton BtnCrea;

    public Registrazione() {
        super("Registrazione");
        TextNome = new JTextField();
        TextPass = new JPasswordField();
        TextCognome = new JTextField();
        TextEmail = new JTextField();
        TextCF = new JTextField();
        BtnCrea = new JButton("Crea account");


        JPanel p1 = new JPanel(new GridLayout(5, 2,3,5));
        p1.add(new JLabel("Nome"));
        p1.add(TextNome);
        p1.add(new JLabel("Password"));
        p1.add(TextPass);
        p1.add(new JLabel("Cognome"));
        p1.add(TextCognome);
        p1.add(new JLabel("Email"));
        p1.add(TextEmail);
        p1.add(new JLabel("CF"));
        p1.add(TextCF);



        JPanel p2 = new JPanel(new GridLayout(1, 1,3,5));
        p2.add(BtnCrea);

        JPanel p4 = new JPanel(new BorderLayout());
        p4.add(p1, BorderLayout.NORTH);
        p4.add(p2, BorderLayout.CENTER);


        /* JFrame methods called */
        setContentPane(p4);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(350, 200);
        setVisible(true);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        BtnCrea.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BtnCrea) {
            //qua prendiamo i dati e poi facciamo la query
            //se va a buon fine creiamo l'utente e lo passiamo come parametro alla finestra successiva
            Utente u=new Utente(Types.Cliente,TextNome.getText(),String.valueOf(TextPass.getPassword()),TextCognome.getText(),TextEmail.getText(),TextCF.getText());
            //lo creiamo nel database
            dispose();
            new HomeCliente(u.getNome(),u.getPassword());
        }
    }

    public static void main(String[] args) {
        new Esame.Registrazione();
    }

}
