package Esame.Login;

import Esame.Amministratore.HomeAmministratore;
import Esame.Classi.Types;
import Esame.Classi.Utente;
import Esame.Clienti.HomeCliente;
import Esame.Farmacisti.HomeFarmacista;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.Unirest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * <p>Questa è la finestra di login principale dove un utente può accedere all'applicazione selezionando il proprio ruolo: "Cliente", "Farmacista" o "Amministratore".</p>
 *
 * @author Luca Barbieri, Davide Cavazzuti
 **/

public class loginInterface extends JFrame implements ActionListener
{
    private final JRadioButton RadioFarmacista;
    private final JRadioButton RadioCliente;
    private final JRadioButton RadioAmministratore;
    private final JTextField TextUtente;
    private final JPasswordField TextPassword;
    private final JButton BtnLogin;
    private final JButton Lreg;
    private final JButton Newpass;
    private final JLabel Lnp;
    private final ButtonGroup RadioGroup;
    private final JLabel Lc;
    private final JLabel Lf;
    private final JLabel La;
    private final JLabel Lu;
    private final JLabel Lp;
    private final JLabel Ln;
    private final JLabel Ls;
    ObjectMapper om = new ObjectMapper();

    public loginInterface() {
        super("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);

        Lc=new JLabel("Cliente");
        Lc.setFont(new Font("Arial", Font.PLAIN, 30));
        Lc.setSize(200, 30);
        Lc.setLocation(10, 30);
        this.add(Lc);
        RadioCliente = new JRadioButton();
        RadioCliente.setName("cliente");
        RadioCliente.setFont(new Font("Arial", Font.PLAIN, 15));
        RadioCliente.setSelected(true);
        RadioCliente.setSize(75, 20);
        RadioCliente.setLocation(250,35);

        Lf=new JLabel("Farmacista");
        Lf.setFont(new Font("Arial", Font.PLAIN, 30));
        Lf.setSize(200, 30);
        Lf.setLocation(10, 100);
        this.add(Lf);
        RadioFarmacista = new JRadioButton();
        RadioFarmacista.setName("farmacista");
        RadioFarmacista.setFont(new Font("Arial", Font.PLAIN, 15));
        RadioFarmacista.setSize(75, 20);
        RadioFarmacista.setLocation(250,105);

        La=new JLabel("Amministratore");
        La.setFont(new Font("Arial", Font.PLAIN, 30));
        La.setSize(200, 30);
        La.setLocation(10, 170);
        this.add(La);
        RadioAmministratore = new JRadioButton();
        RadioAmministratore.setName("amministratore");
        RadioAmministratore.setFont(new Font("Arial", Font.PLAIN, 15));
        RadioAmministratore.setSize(75, 20);
        RadioAmministratore.setLocation(250,175);

        Lu=new JLabel("Email");
        Lu.setFont(new Font("Arial", Font.PLAIN, 30));
        Lu.setSize(200, 30);
        Lu.setLocation(10, 240);
        this.add(Lu);
        TextUtente = new JTextField();
        TextUtente.setFont(new Font("Arial", Font.PLAIN, 30));
        TextUtente.setSize(200, 30);
        TextUtente.setLocation(250, 240);
        this.add(TextUtente);

        Lp=new JLabel("Password");
        Lp.setFont(new Font("Arial", Font.PLAIN, 30));
        Lp.setSize(200, 30);
        Lp.setLocation(10, 310);
        this.add(Lp);
        TextPassword = new JPasswordField();
        TextPassword.setFont(new Font("Arial", Font.PLAIN, 30));
        TextPassword.setSize(200, 30);
        TextPassword.setLocation(250, 310);
        this.add(TextPassword);

        Ln=new JLabel("non hai un account?");
        Ln.setFont(new Font("Arial", Font.PLAIN, 30));
        Ln.setSize(400, 30);
        Ln.setLocation(10, 380);
        this.add(Ln);
        Lreg = new JButton("Registrati");
        Lreg.setFont(new Font("Arial", Font.PLAIN, 30));
        Lreg.setSize(270, 30);
        Lreg.setLocation(510, 380);
        this.add(Lreg);

        Ls=new JLabel("hai già un account?");
        Ls.setFont(new Font("Arial", Font.PLAIN, 30));
        Ls.setSize(400, 30);
        Ls.setLocation(10, 450);
        this.add(Ls);
        BtnLogin = new JButton("Login");
        BtnLogin.setFont(new Font("Arial", Font.PLAIN, 30));
        BtnLogin.setSize(270, 30);
        BtnLogin.setLocation(510, 450);
        this.add(BtnLogin);

        Lnp=new JLabel("hai dimenticato la password?");
        Lnp.setFont(new Font("Arial", Font.PLAIN, 30));
        Lnp.setSize(400, 30);
        Lnp.setLocation(10, 520);
        this.add(Lnp);
        Newpass = new JButton("Nuova password");
        Newpass.setFont(new Font("Arial", Font.PLAIN, 30));
        Newpass.setSize(270, 30);
        Newpass.setLocation(510, 520);
        this.add(Newpass);


        RadioGroup = new ButtonGroup();
        RadioGroup.add(RadioAmministratore);
        RadioGroup.add(RadioFarmacista);
        RadioGroup.add(RadioCliente);
        this.add(RadioCliente);
        this.add(RadioAmministratore);
        this.add(RadioFarmacista);

        setVisible(true);
        BtnLogin.addActionListener(this);
        Lreg.addActionListener(this);
        Newpass.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == BtnLogin) {
            //qua prendiamo i dati e poi facciamo la query per controllare le credenziali

            String radio=null;
            if(RadioCliente.isSelected())
                radio=RadioCliente.getName();
            if(RadioAmministratore.isSelected())
                radio=RadioAmministratore.getName();
            if(RadioFarmacista.isSelected())
                radio=RadioFarmacista.getName();
            String url = "http://localhost:8080/login";
            String response = Unirest.post(url)
                    .field("types", radio)
                    .field("email", TextUtente.getText())
                    .field("password", String.valueOf(TextPassword.getPassword()))
                    .asString().getBody();
            if (response.equals("failed"))
            {
                JOptionPane.showMessageDialog(null, "Email o password errati");
                return;
            }

            Utente u = null;
            try{
                u= om.readValue(response,Utente.class);
            }
            catch (IOException ee) {
                ee.printStackTrace();
            }
            dispose();

            if(u.getT()== Types.cliente)
                new HomeCliente(u);
            else if(u.getT()== Types.farmacista)
                new HomeFarmacista(u);
            else {
                try {
                    new HomeAmministratore(u);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

        }
        if(e.getSource() == Lreg)
        {
            dispose();
            new Registrazione();
        }
        if(e.getSource() == Newpass)
        {
            dispose();
            new ModPass();
        }
    }

    public static void main(String[] args) {
        new loginInterface();
    }
}