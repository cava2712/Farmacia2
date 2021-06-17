package Esame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Guariscimi extends JFrame implements ActionListener {
    //private final JTextField Sintomo;
    private final JMenuBar MenuBar;
    private final JMenu Opzioni;
    private final JMenu Carrello;
    private final JMenuItem Disconnetti,Profilo;


    public Guariscimi() {
        super("Guariscimi");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 700);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);

        MenuBar= new JMenuBar();
        Opzioni = new  JMenu("opzioni");
        Carrello = new  JMenu("Carrello");
        Disconnetti= new JMenuItem("Disconnetiti");
        Profilo= new JMenuItem(("profilo"));
        MenuBar.add(Opzioni);
        MenuBar.add(Carrello);
        Opzioni.add(Disconnetti);
        Opzioni.add(Profilo);
        this.setJMenuBar(MenuBar);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Esame.Guariscimi();
    }
}
