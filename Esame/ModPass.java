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

public class ModPass extends JFrame implements ActionListener {
    private final JTextField TextEmail;
    private final JTextField TextNpass;
    private final JButton Modifica;
    private final JLabel Le;
    private final JLabel Lnp;
    public ModPass() {



        super("Modifica password");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 400);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);


        Le=new JLabel("Email:");
        Le.setFont(new Font("Arial", Font.PLAIN, 15));
        Le.setSize(100, 30);
        Le.setLocation(10, 50);
        this.add(Le);
        TextEmail = new JTextField();
        TextEmail.setFont(new Font("Arial", Font.PLAIN, 15));
        TextEmail.setSize(200, 30);
        TextEmail.setLocation(180, 50);
        this.add(TextEmail);

        Lnp=new JLabel("Nuova Password:");
        Lnp.setFont(new Font("Arial", Font.PLAIN, 15));
        Lnp.setSize(170, 30);
        Lnp.setLocation(10, 150);
        this.add(Lnp);
        TextNpass = new JTextField();
        TextNpass.setFont(new Font("Arial", Font.PLAIN, 15));
        TextNpass.setSize(200, 30);
        TextNpass.setLocation(180, 150);
        this.add(TextNpass);

        Modifica=new JButton("Modifica Password");
        Modifica.setFont(new Font("Arial", Font.PLAIN, 15));
        Modifica.setSize(375, 30);
        Modifica.setLocation(5, 270);
        this.add(Modifica);



        Modifica.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Modifica) {
            //qua prendiamo i dati e poi facciamo la query
            String url = "http://localhost:8080/password";
            String response = Unirest.post(url)
                    .field("email", TextEmail.getText())
                    .field("password", String.valueOf(TextNpass.getText()))
                    .asString().getBody();

            if (response.equals("errore email"))
            {
                JOptionPane.showMessageDialog(null, "Non esiste un account su quest'email");
                return;
            }
            JOptionPane.showMessageDialog(null, "Password modificata correttamente");
            dispose();
            new loginInterface();

        }
    }

    public static void main(String[] args) {
        new Esame.ModPass();
    }
}