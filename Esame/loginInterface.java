package Esame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class loginInterface extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private final JRadioButton RadioFarmacista;
    private final JRadioButton RadioCliente;
    private final JRadioButton RadioAmministratore;
    private final JTextField TextUtente;
    private final JPasswordField TextPassword;
    private final JButton BtnLogin;
    private final JButton Lreg;
    private final ButtonGroup RadioGroup;
    private final JLabel Nc;

    public loginInterface() {
        super("Login");
        RadioFarmacista = new JRadioButton();
        RadioCliente = new JRadioButton();
        RadioAmministratore = new JRadioButton();
        RadioGroup = new ButtonGroup();
        RadioGroup.add(RadioAmministratore);
        RadioGroup.add(RadioFarmacista);
        RadioGroup.add(RadioCliente);
        TextUtente = new JTextField();
        TextPassword = new JPasswordField();
        TextPassword.setSize(190, 20);
        BtnLogin = new JButton("Login");
        Lreg = new JButton("Registrati");
        Nc=new JLabel("Cliente");

        JPanel p1 = new JPanel(new GridLayout(3, 1,3,5));
        p1.add(Nc);
        p1.add(RadioCliente);
        p1.add(new JLabel("Farmacista"));
        p1.add(RadioFarmacista);
        p1.add(new JLabel("Amminstatore"));
        p1.add(RadioAmministratore);



        JPanel p2 = new JPanel(new GridLayout(3, 2,3,5));
        p2.add(new JLabel("Username"));
        p2.add(TextUtente);
        p2.add(new JLabel("Password"));
        p2.add(TextPassword);
        p2.add(new JLabel("non hai un account?"));
        p2.add(Lreg);

        JPanel p3 = new JPanel(new GridLayout(1, 1,3,5));
        p3.add(BtnLogin);

        JPanel p4 = new JPanel(new BorderLayout());
        p4.add(p1, BorderLayout.NORTH);
        p4.add(p2, BorderLayout.CENTER);
        p4.add(p3, BorderLayout.SOUTH);

        /* JFrame methods called */
        setContentPane(p4);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(350, 200);
        setVisible(true);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        BtnLogin.addActionListener(this);
        Lreg.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BtnLogin) {
            //qua prendiamo i dati e poi facciamo la query
            //se va a buon fine creiamo l'utente e lo passiamo come parametro alla finestra successiva
            dispose();
            //controllo esiste cliente
            new HomeCliente(TextUtente.getText(),String.valueOf(TextPassword.getPassword()));
        }
        if(e.getSource() == Lreg)
        {
            dispose();
            new Registrazione();
        }
    }

    public static void main(String[] args) {
        new Esame.loginInterface();
    }
}