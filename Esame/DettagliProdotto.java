package Esame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DettagliProdotto extends JFrame implements ActionListener {
    private final JTextField TextCod;
    private final JTextField TextNome;
    private final JTextField TextMarca;
    private final JTextField TextCat;
    private final JTextField TextPre;
    private final JTextField TextQnt;
    private final JLabel Lc;
    private final JLabel Ln;
    private final JLabel Lm;
    private final JLabel Lca;
    private final JLabel Lp;
    private final JLabel Lq;
    private final JLabel pic;

    public DettagliProdotto() {
        super("DettagliProdotto");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 700);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);

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

        Lc=new JLabel("Codice");
        Lc.setFont(new Font("Arial", Font.PLAIN, 30));
        Lc.setSize(200, 30);
        Lc.setLocation(10, 250);
        this.add(Lc);
        TextCod = new JTextField();
        TextCod.setFont(new Font("Arial", Font.PLAIN, 30));
        TextCod.setSize(350, 30);
        TextCod.setLocation(200, 250);
        this.add(TextCod);

        Ln=new JLabel("Nome");
        Ln.setFont(new Font("Arial", Font.PLAIN, 30));
        Ln.setSize(200, 30);
        Ln.setLocation(10, 320);
        this.add(Ln);
        TextNome = new JTextField();
        TextNome.setFont(new Font("Arial", Font.PLAIN, 30));
        TextNome.setSize(350, 30);
        TextNome.setLocation(200, 320);
        this.add(TextNome);

        Lm=new JLabel("Cognome");
        Lm.setFont(new Font("Arial", Font.PLAIN, 30));
        Lm.setSize(200, 30);
        Lm.setLocation(10, 390);
        this.add(Lm);
        TextMarca = new JTextField();
        TextMarca.setFont(new Font("Arial", Font.PLAIN, 30));
        TextMarca.setSize(350, 30);
        TextMarca.setLocation(200, 390);
        this.add(TextMarca);

        Lca=new JLabel("Password");
        Lca.setFont(new Font("Arial", Font.PLAIN, 30));
        Lca.setSize(200, 30);
        Lca.setLocation(10, 460);
        this.add(Lca);
        TextCat = new JPasswordField();
        TextCat.setFont(new Font("Arial", Font.PLAIN, 30));
        TextCat.setSize(350, 30);
        TextCat.setLocation(200, 460);
        this.add(TextCat);

        Lp=new JLabel("Email");
        Lp.setFont(new Font("Arial", Font.PLAIN, 30));
        Lp.setSize(200, 30);
        Lp.setLocation(10, 530);
        this.add(Lp);
        TextPre = new JTextField();
        TextPre.setFont(new Font("Arial", Font.PLAIN, 30));
        TextPre.setSize(350, 30);
        TextPre.setLocation(200, 530);
        this.add(TextPre);

        Lq=new JLabel("CF");
        Lq.setFont(new Font("Arial", Font.PLAIN, 30));
        Lq.setSize(200, 30);
        Lq.setLocation(10, 600);
        this.add(Lq);
        TextQnt = new JTextField();
        TextQnt.setFont(new Font("Arial", Font.PLAIN, 30));
        TextQnt.setSize(350, 30);
        TextQnt.setLocation(200, 600);
        this.add(TextQnt);



        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Esame.DettagliProdotto();
    }

}

