package Esame.Farmacisti;

import Esame.Classi.Farmaco;
import Esame.Classi.Utente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import kong.unirest.Unirest;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * <p>Questa è la finestra dove l'attuale farmacista può gestire tutti i farmaci disponibili (aggiugni/modifica/elimina) </p>
 *
 * @author Luca Barbieri, Davide Cavazzuti
 **/
public class GestisciMag extends JFrame implements ActionListener {
    private final JTextField Filtra;
    String[] colName= new String[] { "Codice","Nome" ,"Prezzo" };

    private  JTable Table;
    private final JLabel LabelFiltra;
    private final JLabel Lc;
    private final JLabel Ln;
    private final JLabel Lm;
    private final JLabel Lca;
    private final JLabel Lp;
    private final JLabel Lq;
    private final  JLabel Lr;
    private final JTextField Textc;
    private final JTextField Textn;
    private final JTextField Textm;
    private final JTextField Textca;
    private final JTextField Textp;
    private final JTextField Textq;
    private  JLabel pic;
    private final JButton BtnModifica;
    private final JButton Back;
    private final JButton Aggiorna;
    private final JButton EliminaFarmaco;
    private final JButton AggiungiFarmaco;
    ArrayList<Farmaco> listaFarmaci =null;
    private  JScrollPane scrol;
    Object[][] farmaci;
    Utente ug;
    JFrame f;
    ObjectMapper om = new ObjectMapper();

    public GestisciMag(Utente u) throws Exception {
        super("Gestisci magazzino");
        ug=u;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 800);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);
        f= this;

        LabelFiltra = new JLabel("Filtra per nome");
        LabelFiltra.setFont(new Font("Arial", Font.PLAIN, 30));
        LabelFiltra.setSize(400, 30);
        LabelFiltra.setLocation(415, 10);
        this.add(LabelFiltra);

        Filtra = new JTextField();
        Filtra.setFont(new Font("Arial", Font.PLAIN, 30));
        Filtra.setSize(400, 30);
        Filtra.setLocation(5, 10);
        this.add(Filtra);
        CollectionType listType =
                om.getTypeFactory().constructCollectionType(ArrayList.class, Farmaco.class);

        String url = "http://localhost:8080/farmaci";
        String json = Unirest.get(url).asString().getBody();
        try {
            listaFarmaci = om.readValue(json, listType);
        } catch (Exception e) {
            throw new Exception(e);
        }

        farmaci = new Object[listaFarmaci.size()][3];
        for(int i = 0; i< listaFarmaci.size(); i++)
        {
            farmaci[i][0]= listaFarmaci.get(i).getCodice();
            farmaci[i][1]= listaFarmaci.get(i).getNome();
            farmaci[i][2]= listaFarmaci.get(i).getPrezzo();
        }

        pic= new JLabel();
        pic.setLocation(415, 90);
        f.add(pic);

        Lc= new JLabel("Codice:");
        Lc.setFont(new Font("Arial", Font.PLAIN, 20));
        Lc.setSize(150, 30);
        Lc.setLocation(415, 300);
        this.add(Lc);
        Textc= new JTextField();
        Textc.setFont(new Font("Arial", Font.PLAIN, 20));
        Textc.setSize(200, 30);
        Textc.setLocation(570, 300);
        Textc.setEditable(false);
        this.add(Textc);

        Ln= new JLabel("Nome:");
        Ln.setFont(new Font("Arial", Font.PLAIN, 20));
        Ln.setSize(150, 30);
        Ln.setLocation(415, 350);
        this.add(Ln);
        Textn = new JTextField();
        Textn.setFont(new Font("Arial", Font.PLAIN, 20));
        Textn.setSize(200, 30);
        Textn.setLocation(570, 350);
        Textn.setEditable(false);
        this.add(Textn);

        Lm= new JLabel("Marca:");
        Lm.setFont(new Font("Arial", Font.PLAIN, 20));
        Lm.setSize(150, 30);
        Lm.setLocation(415, 400);
        this.add(Lm);
        Textm = new JTextField();
        Textm.setFont(new Font("Arial", Font.PLAIN, 20));
        Textm.setSize(200, 30);
        Textm.setLocation(570, 400);
        Textm.setEditable(false);
        this.add(Textm);

        Lca= new JLabel("Categoria:");
        Lca.setFont(new Font("Arial", Font.PLAIN, 20));
        Lca.setSize(150, 30);
        Lca.setLocation(415, 450);
        this.add(Lca);
        Textca = new JTextField();
        Textca.setFont(new Font("Arial", Font.PLAIN, 20));
        Textca.setSize(200, 30);
        Textca.setLocation(570, 450);
        Textca.setEditable(false);
        this.add(Textca);

        Lp= new JLabel("Prezzo:");
        Lp.setFont(new Font("Arial", Font.PLAIN, 20));
        Lp.setSize(150, 30);
        Lp.setLocation(415, 500);
        this.add(Lp);
        Textp = new JTextField();
        Textp.setFont(new Font("Arial", Font.PLAIN, 20));
        Textp.setSize(200, 30);
        Textp.setLocation(570, 500);
        Textp.setEditable(false);
        this.add(Textp);

        Lq= new JLabel("Quantità:");
        Lq.setFont(new Font("Arial", Font.PLAIN, 20));
        Lq.setSize(150, 30);
        Lq.setLocation(415, 550);
        this.add(Lq);
        Textq = new JTextField();
        Textq.setFont(new Font("Arial", Font.PLAIN, 20));
        Textq.setSize(200, 30);
        Textq.setLocation(570, 550);
        Textq.setEditable(false);
        this.add(Textq);

        Lr= new JLabel("Ricetta:");
        Lr.setFont(new Font("Arial", Font.PLAIN, 20));
        Lr.setSize(450, 30);
        Lr.setLocation(415, 600);
        this.add(Lr);

        creaTabella();

        Back= new JButton("Back");
        Back.setFont(new Font("Arial", Font.PLAIN, 30));
        Back.setSize(150, 40);
        Back.setLocation(600, 680);
        this.add(Back);

        Aggiorna= new JButton("Aggiorna");
        Aggiorna.setFont(new Font("Arial", Font.PLAIN, 30));
        Aggiorna.setSize(200, 40);
        Aggiorna.setLocation(205, 620);
        Aggiorna.setVisible(false);
        this.add(Aggiorna);

        BtnModifica = new JButton("Modifica");
        BtnModifica.setFont(new Font("Arial", Font.PLAIN, 30));
        BtnModifica.setSize(200, 40);
        BtnModifica.setLocation(5, 620);
        this.add(BtnModifica);

        AggiungiFarmaco= new JButton("Aggiungi un nuovo farmaco");
        AggiungiFarmaco.setFont(new Font("Arial", Font.PLAIN, 15));
        AggiungiFarmaco.setSize(300, 40);
        AggiungiFarmaco.setLocation(5, 680);
        this.add(AggiungiFarmaco);

        EliminaFarmaco = new JButton("Elimina farmaco");
        EliminaFarmaco.setFont(new Font("Arial", Font.PLAIN, 15));
        EliminaFarmaco.setSize(200, 40);
        EliminaFarmaco.setLocation(305, 680);
        this.add(EliminaFarmaco);

        Filtra.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                modifica();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                modifica();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                return;
            }
        });
        BtnModifica.addActionListener(this);
        Back.addActionListener(this);
        Aggiorna.addActionListener(this);
        EliminaFarmaco.addActionListener(this);
        AggiungiFarmaco.addActionListener(this);
        setVisible(true);
    }
    public void modifica()
    {
        this.remove(scrol);
        scrol.remove(Table);
        String t = Filtra.getText();
        int c=0;
        ArrayList<Farmaco> lisa= new ArrayList<Farmaco>();
        for(Farmaco f : listaFarmaci)
        {
            if(f.getNome().toLowerCase().startsWith(t))
            {
                lisa.add(f);
                c++;
            }
        }
        farmaci = new Object[c][3];
        for(int i=0;i<c;i++)
        {
            farmaci[i][0]= lisa.get(i).getCodice();
            farmaci[i][1]= lisa.get(i).getNome();
            farmaci[i][2]= lisa.get(i).getPrezzo();
        }
        creaTabella();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == BtnModifica)
        {
            int riga=Table.getSelectedRow();
            if(riga==-1)
            {
                JOptionPane.showMessageDialog(null, "nessuna riga selezionata!");
                return;
            }
            Aggiorna.setVisible(true);
            Textn.setEditable(true);
            Textm.setEditable(true);
            Textca.setEditable(true);
            Textp.setEditable(true);
            Textq.setEditable(true);
        }
        if (e.getSource() == Aggiorna)
        {
            String url = "http://localhost:8080/aggiornaF";

            String s= Textq.getText();
            try {
                Integer.parseInt(s);
            } catch(NumberFormatException ee){
                JOptionPane.showMessageDialog(null, "devi inserire un numero!");
                Textq.setText("");
                return;
            }
            s= Textp.getText();
            try {
                Float.parseFloat(s.replace(',','.'));
            } catch(NumberFormatException ee){
                JOptionPane.showMessageDialog(null, "devi inserire un numero nel prezzo!");
                Textq.setText("");
                return;
            }
            int riga=Table.getSelectedRow();
            int sCodice= (int)farmaci[riga][0];
            Farmaco sFarmaco=listaFarmaci.stream().filter(farmaco -> sCodice==farmaco.getCodice()).findFirst().orElse(null);
            String a = listaFarmaci.get(riga).getPercorsoImg();
            String response = Unirest.post(url)
                    .field("nome", Textn.getText())
                    .field("marca", Textm.getText())
                    .field("categoria", Textca.getText())
                    .field("prezzo",String.format("%.2f",Float.parseFloat(Textp.getText().replace(',','.'))))
                    .field("quantità", Textq.getText())
                    .field("codice", Textc.getText())
                    .field("ricetta", sFarmaco.getRicetta())
                    .asString().getBody();
            listaFarmaci.get(listaFarmaci.indexOf(sFarmaco)).setCategoria( Textca.getText());
            listaFarmaci.get(listaFarmaci.indexOf(sFarmaco)).setPrezzo(Float.parseFloat(Textp.getText().replace(',','.')));
            listaFarmaci.get(listaFarmaci.indexOf(sFarmaco)).setQuantita(Integer.parseInt(Textq.getText()));
            listaFarmaci.get(listaFarmaci.indexOf(sFarmaco)).setNome( Textn.getText());
            listaFarmaci.get(listaFarmaci.indexOf(sFarmaco)).setMarca( Textm.getText());

            scrol.remove(Table);
            this.remove(scrol);
            farmaci = new Object[listaFarmaci.size()][3];
            for(int i = 0; i< listaFarmaci.size(); i++)
            {
                farmaci[i][0]= listaFarmaci.get(i).getCodice();
                farmaci[i][1]= listaFarmaci.get(i).getNome();
                farmaci[i][2]= listaFarmaci.get(i).getPrezzo();
            }
            creaTabella();

            Aggiorna.setVisible(false);
            Textn.setEditable(false);
            Textm.setEditable(false);
            Textca.setEditable(false);
            Textp.setEditable(false);
            Textq.setEditable(false);
            JOptionPane.showMessageDialog(null, "modifica avvenuta con successo!");
            return;
        }

        if (e.getSource() == Back)
        {
            dispose();
            new HomeFarmacista(ug);
        }

        if (e.getSource() == EliminaFarmaco) {
            String url = "http://localhost:8080/eliminaF";
            int riga=Table.getSelectedRow();
            int sCodice= (int)farmaci[riga][0];
            Farmaco sFarmaco=listaFarmaci.stream().filter(farmaco -> sCodice==farmaco.getCodice()).findFirst().orElse(null);
            if(riga==-1)
            {
                JOptionPane.showMessageDialog(null, "Nessuna riga selezionata!");
                return;
            }
            String response = Unirest.post(url)
                    .field("codice", listaFarmaci.get(riga).getCodice())
                    .asString().getBody();
            listaFarmaci.remove(listaFarmaci.indexOf(sFarmaco));
            scrol.remove(Table);
            this.remove(scrol);
            farmaci = new Object[listaFarmaci.size()][3];
            for(int i = 0; i< listaFarmaci.size(); i++)
            {
                farmaci[i][0]= listaFarmaci.get(i).getCodice();
                farmaci[i][1]= listaFarmaci.get(i).getNome();
                farmaci[i][2]= listaFarmaci.get(i).getPrezzo();
            }
            creaTabella();
            JOptionPane.showMessageDialog(null, "Farmaco eliminato correttamente!");
            return;
        }

        if (e.getSource() == AggiungiFarmaco) {
            dispose();
            new NuovoFarmaco(ug);
            return;
        }

    }
    private void creaTabella()
    {
        Table = new JTable(farmaci,colName){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Table.setFont(new Font("Arial", Font.PLAIN, 15));
        Table.setSize(400, 500);
        Table.setFillsViewportHeight(true);
        scrol=new JScrollPane(Table);
        scrol.setLocation(5, 100);
        scrol.setSize(400,500);
        this.add(scrol);

        Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int riga=Table.getSelectedRow();
                int sCodice= (int)farmaci[riga][0];
                Farmaco sFarmaco=listaFarmaci.stream().filter(farmaco -> sCodice==farmaco.getCodice()).findFirst().orElse(null);
                Textc.setText(String.format("%d", sFarmaco.getCodice()));
                Textn.setText(sFarmaco.getNome());
                Textm.setText(sFarmaco.getMarca());
                Textca.setText(sFarmaco.getCategoria());
                Textp.setText(String.format("%.2f", sFarmaco.getPrezzo()));
                Textq.setText(String.format("%d", sFarmaco.getQuantita()));
                if(sFarmaco.getRicetta().compareTo("true")==0)
                    Lr.setText("Ricetta: necessaria");
                else
                    Lr.setText("Ricetta: non necessaria");

                String a = sFarmaco.getPercorsoImg();
                if(a=="")
                    a="default.png";
                ImageIcon icon = new ImageIcon(String.format("Esame/pic/Farmaci/%s",a));
                Image image = icon.getImage();
                Image Nimage = image.getScaledInstance(300,250, Image.SCALE_SMOOTH);
                pic.setIcon(new ImageIcon(Nimage));
                pic.setSize(300, 250);
                pic.setLocation(415, 50);
                f.add(pic);
            }
        });
    }
}

