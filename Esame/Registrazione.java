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
    private final JLabel Ln;
    private final JLabel Lp;
    private final JLabel Lc;
    private final JLabel Le;
    private final JLabel Lcf;

    public Registrazione() {
        super("Registrazione");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 550);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);

        Ln=new JLabel("Nome");
        Ln.setFont(new Font("Arial", Font.PLAIN, 30));
        Ln.setSize(200, 30);
        Ln.setLocation(10, 30);
        this.add(Ln);
        TextNome = new JTextField();
        TextNome.setFont(new Font("Arial", Font.PLAIN, 30));
        TextNome.setSize(350, 30);
        TextNome.setLocation(200, 30);
        this.add(TextNome);

        Lc=new JLabel("Cognome");
        Lc.setFont(new Font("Arial", Font.PLAIN, 30));
        Lc.setSize(200, 30);
        Lc.setLocation(10, 100);
        this.add(Lc);
        TextCognome = new JTextField();
        TextCognome.setFont(new Font("Arial", Font.PLAIN, 30));
        TextCognome.setSize(350, 30);
        TextCognome.setLocation(200, 100);
        this.add(TextCognome);

        Lp=new JLabel("Password");
        Lp.setFont(new Font("Arial", Font.PLAIN, 30));
        Lp.setSize(200, 30);
        Lp.setLocation(10, 170);
        this.add(Lp);
        TextPass = new JPasswordField();
        TextPass.setFont(new Font("Arial", Font.PLAIN, 30));
        TextPass.setSize(350, 30);
        TextPass.setLocation(200, 170);
        this.add(TextPass);

        Le=new JLabel("Email");
        Le.setFont(new Font("Arial", Font.PLAIN, 30));
        Le.setSize(200, 30);
        Le.setLocation(10, 240);
        this.add(Le);
        TextEmail = new JTextField();
        TextEmail.setFont(new Font("Arial", Font.PLAIN, 30));
        TextEmail.setSize(350, 30);
        TextEmail.setLocation(200, 240);
        this.add(TextEmail);

        Lcf=new JLabel("CF");
        Lcf.setFont(new Font("Arial", Font.PLAIN, 30));
        Lcf.setSize(200, 30);
        Lcf.setLocation(10, 310);
        this.add(Lcf);
        TextCF = new JTextField();
        TextCF.setFont(new Font("Arial", Font.PLAIN, 30));
        TextCF.setSize(350, 30);
        TextCF.setLocation(200, 310);
        this.add(TextCF);

        BtnCrea = new JButton("Crea account");
        BtnCrea.setFont(new Font("Arial", Font.PLAIN, 30));
        BtnCrea.setSize(600, 60);
        BtnCrea.setLocation(0, 450);
        this.add(BtnCrea);


        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BtnCrea) {
            //qua prendiamo i dati e poi facciamo la query
            //se va a buon fine creiamo l'utente e lo passiamo come parametro alla finestra successiva
            Utente u=new Utente(Types.cliente,TextNome.getText(),String.valueOf(TextPass.getPassword()),TextCognome.getText(),TextEmail.getText(),TextCF.getText());
            //lo creiamo nel database
            dispose();
            new HomeCliente(u.getNome(),u.getPassword());
        }
    }

    public static void main(String[] args) {
        new Esame.Registrazione();
    }

}
