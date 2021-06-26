package Esame.Clienti;

import Esame.Classi.Utente;
import Esame.Login.loginInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeCliente extends JFrame implements ActionListener {
    private final JButton BtnMed;
    private final JButton BtnGuariscimi;
    private final JButton BtnContatta;
    private final JButton BtnRicetta;
    private final JButton BtnFarmaci;
    private final JButton BtnProfilo;
    private final JButton BtnDisc;
    private final JMenuBar MenuBar;
    private final JMenu Opzioni;
    private final JMenuItem Carrello;
    private final JMenuItem Disconnetti,Profilo;
    private final JLabel utente;
    Utente ug=null;

    public HomeCliente(Utente u) {
        super("HOME");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 700);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);
        ug=u;
        MenuBar= new JMenuBar();
        Opzioni = new  JMenu("opzioni");
        Carrello = new  JMenuItem(String.format("Carrello:%d",u.numCarrello()));
        Disconnetti= new JMenuItem("Disconnetiti");
        Profilo= new JMenuItem(("profilo"));
        MenuBar.add(Opzioni);
        MenuBar.add(Carrello);
        Opzioni.add(Disconnetti);
        Opzioni.add(Profilo);
        this.setJMenuBar(MenuBar);

        utente= new JLabel(String.format("Utente:%s Email:%s", u.getNome(),u.getEmail()));
        utente.setFont(new Font("Arial", Font.PLAIN, 30));
        utente.setSize(400, 30);
        utente.setLocation(45, 10);
        this.add(utente);
        BtnMed = new JButton("MIE MEDICINE");
        BtnMed.setSize(200,200);
        BtnMed.setLocation(45,50);
        BtnGuariscimi= new JButton("GUARISCIMI");
        BtnGuariscimi.setSize(200,200);
        BtnGuariscimi.setLocation(295,50);
        BtnContatta = new JButton("CONTATTA");
        BtnContatta.setSize(200,200);
        BtnContatta.setLocation(545,50);
        this.add(BtnMed);
        this.add(BtnGuariscimi);
        this.add(BtnContatta);
        BtnRicetta = new JButton("IMMETTI RICETTA");
        BtnRicetta.setSize(200,200);
        BtnRicetta.setLocation(45,300);
        BtnFarmaci = new JButton("TUTTI I FARMACI");
        BtnFarmaci.setSize(200,200);
        BtnFarmaci.setLocation(295,300);
        BtnProfilo = new JButton("PROFILO");
        BtnProfilo.setSize(200,200);
        BtnProfilo.setLocation(545,300);
        this.add(BtnRicetta);
        this.add(BtnFarmaci);
        this.add(BtnProfilo);

        BtnDisc = new JButton("Disconnettiti");
        BtnDisc.setSize(200,50);
        BtnDisc.setLocation(545,550);
        this.add(BtnDisc);
        BtnProfilo.addActionListener(this);
        BtnDisc.addActionListener(this);
        BtnFarmaci.addActionListener(this);
        BtnGuariscimi.addActionListener(this);
        BtnMed.addActionListener(this);
        Carrello.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BtnProfilo) {
            dispose();
            new Profilo(ug);
        }
        if (e.getSource() == BtnMed) {
            dispose();
            new MieMedicine(ug);
        }
        if (e.getSource() == Carrello) {
            try {
                dispose();
                new Carrello(ug);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (e.getSource() == BtnDisc) {
            dispose();
            new loginInterface();
        }
        if (e.getSource() == BtnGuariscimi) {
            dispose();
            try {
                new Guariscimi(ug);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (e.getSource() == BtnFarmaci) {
            dispose();
            try {
                new TuttiFarmaci(ug,null);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

}