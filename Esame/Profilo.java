package Esame;

import kong.unirest.Unirest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Profilo extends JFrame implements ActionListener {
    private final JTextField TextNome;
    private final JTextField TextPass;
    private final JTextField TextCognome;
    private final JTextField TextEmail;
    private final JTextField TextCF;
    private final JButton BtnBack;
    private final JButton Modifica;
    private final JButton Aggiorna;
    private final JLabel Ln;
    private final JLabel Lp;
    private final JLabel Lc;
    private final JLabel Le;
    private final JLabel Lcf;
    private final JLabel pic;
    String emailp=null;
    Utente ug=null;
    public Profilo(Utente u) {

        super(String.format("Profilo di %s",u.getNome()));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(650, 700);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);
        ug=u;
        emailp=u.getEmail();
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("C:\\Users\\Utente\\Dropbox\\Il mio PC (DESKTOP-G0JQQTD)\\Desktop\\download.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pic= new JLabel(new ImageIcon(myPicture));
        pic.setSize(200, 200);
        pic.setLocation(10, 30);
        this.add(pic);

        Ln=new JLabel("Nome:   ");
        Ln.setFont(new Font("Arial", Font.PLAIN, 30));
        Ln.setSize(200, 30);
        Ln.setLocation(10, 250);
        this.add(Ln);
        TextNome = new JTextField(u.getNome());
        TextNome.setFont(new Font("Arial", Font.PLAIN, 30));
        TextNome.setSize(350, 30);
        TextNome.setLocation(200, 250);
        TextNome.setEditable(false);
        this.add(TextNome);

        Lc=new JLabel("Cognome:   ");
        Lc.setFont(new Font("Arial", Font.PLAIN, 30));
        Lc.setSize(200, 30);
        Lc.setLocation(10, 320);
        this.add(Lc);
        TextCognome = new JTextField(u.getCognome());
        TextCognome.setFont(new Font("Arial", Font.PLAIN, 30));
        TextCognome.setSize(350, 30);
        TextCognome.setLocation(200, 320);
        TextCognome.setEditable(false);
        this.add(TextCognome);

        Lp=new JLabel("Password:   ");
        Lp.setFont(new Font("Arial", Font.PLAIN, 30));
        Lp.setSize(200, 30);
        Lp.setLocation(10, 390);
        this.add(Lp);
        TextPass = new JTextField(u.getPassword());
        TextPass.setFont(new Font("Arial", Font.PLAIN, 30));
        TextPass.setSize(350, 30);
        TextPass.setLocation(200, 390);
        TextPass.setEditable(false);
        this.add(TextPass);

        Le=new JLabel("Email:   ");
        Le.setFont(new Font("Arial", Font.PLAIN, 30));
        Le.setSize(200, 30);
        Le.setLocation(10, 460);
        this.add(Le);
        TextEmail = new JTextField(u.getEmail());
        TextEmail.setFont(new Font("Arial", Font.PLAIN, 30));
        TextEmail.setSize(350, 30);
        TextEmail.setLocation(200, 460);
        TextEmail.setEditable(false);
        this.add(TextEmail);

        Lcf=new JLabel("CF:   ");
        Lcf.setFont(new Font("Arial", Font.PLAIN, 30));
        Lcf.setSize(200, 30);
        Lcf.setLocation(10, 530);
        this.add(Lcf);
        TextCF = new JTextField(u.getCF());
        TextCF.setFont(new Font("Arial", Font.PLAIN, 30));
        TextCF.setSize(350, 30);
        TextCF.setLocation(200, 530);
        TextCF.setEditable(false);
        this.add(TextCF);

        BtnBack = new JButton("Back");
        BtnBack.setFont(new Font("Arial", Font.PLAIN, 25));
        BtnBack.setSize(150, 60);
        BtnBack.setLocation(50, 600);
        this.add(BtnBack);

        Modifica = new JButton("Modifica");
        Modifica.setFont(new Font("Arial", Font.PLAIN, 25));
        Modifica.setSize(150, 60);
        Modifica.setLocation(250, 600);
        this.add(Modifica);

        Aggiorna = new JButton("Aggiorna");
        Aggiorna.setFont(new Font("Arial", Font.PLAIN, 25));
        Aggiorna.setSize(150, 60);
        Aggiorna.setLocation(450, 600);
        Aggiorna.setVisible(false);

        this.add(Aggiorna);
        

        BtnBack.addActionListener(this);
        Modifica.addActionListener(this);
        Aggiorna.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BtnBack) {

            dispose();
            new HomeCliente(ug);
        }
        if (e.getSource() == Modifica) {
            Aggiorna.setVisible(true);
            TextCF.setEditable(true);
            TextNome.setEditable(true);
            TextCognome.setEditable(true);
            TextEmail.setEditable(true);
            TextPass.setEditable(true);

        }
        if (e.getSource() == Aggiorna) {
            //qua prendiamo i dati e poi facciamo la query
            String url = "http://localhost:8080/aggiorna";

            String response = Unirest.post(url)
                    .field("types", "cliente")
                    .field("emailp",emailp)
                    .field("email", TextEmail.getText())
                    .field("password", String.valueOf(TextPass.getText()))
                    .field("cf", TextCF.getText())
                    .field("cognome", TextCognome.getText())
                    .field("nome", TextNome.getText())
                    .asString().getBody();

            ug.setCF(TextCF.getText());
            ug.setNome(TextCF.getText());
            ug.setCognome(TextCognome.getText());
            ug.setEmail(TextEmail.getText());
            ug.setPassword(TextPass.getText());
            Aggiorna.setVisible(false);
            TextCF.setEditable(false);
            TextNome.setEditable(false);
            TextCognome.setEditable(false);
            TextEmail.setEditable(false);
            TextPass.setEditable(false);
            emailp=ug.getEmail();
        }
    }

    public static void main(String[] args) {
        new Esame.Profilo(new Utente());
    }

}