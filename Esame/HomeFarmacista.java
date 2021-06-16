package Esame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeFarmacista extends JFrame implements ActionListener {
    private final JButton BtnMag;
    private final JButton BtnOrd;
    private final JButton BtnRisp;
    private final JButton BtnRicetta;
    private final JButton BtnProfilo;
    private final JButton BtnDisc;
    private final JMenuBar MenuBar;
    private final JMenu Opzioni;
    private final JMenu Carrello;
    private final JMenuItem Disconnetti,Profilo;


    public HomeFarmacista(String nomeUtente,String passUtente) {
        super("HOME");
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

        BtnMag = new JButton("Gestisci Magazino");
        BtnMag.setSize(200,200);
        BtnMag.setLocation(45,50);
        BtnOrd= new JButton("Ordina nuovi medicinali");
        BtnOrd.setSize(200,200);
        BtnOrd.setLocation(295,50);
        BtnRisp = new JButton("Rispondi ai clienti");
        BtnRisp.setSize(200,200);
        BtnRisp.setLocation(545,50);
        this.add(BtnMag);
        this.add(BtnOrd);
        this.add(BtnRisp);
        BtnRicetta = new JButton("Verifica ricette");
        BtnRicetta.setSize(200,200);
        BtnRicetta.setLocation(150,300);
        BtnProfilo = new JButton("PROFILO");
        BtnProfilo.setSize(200,200);
        BtnProfilo.setLocation(450,300);
        this.add(BtnRicetta);
        this.add(BtnProfilo);
        BtnDisc = new JButton("Disconnettiti");
        BtnDisc.setSize(200,50);
        BtnDisc.setLocation(545,550);
        this.add(BtnDisc);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Esame.HomeFarmacista("davide","figo");
    }
}
