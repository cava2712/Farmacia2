package Esame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import kong.unirest.Unirest;
import spark.utils.StringUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

public class TuttiFarmaci extends JFrame implements ActionListener {
    private final JTextField Filtra;
    String[] colName= new String[] { "codice","Name" ,"Price" };

    private  JTable Table;
    private final JMenuBar MenuBar;
    private final JMenu Opzioni;
    private final JMenuItem Carrello;
    private final JMenuItem Disconnetti,Profilo;
    private final JLabel Lf;
    private final JLabel Lc;
    private final JLabel Ln;
    private final JLabel Lm;
    private final JLabel Lca;
    private final JLabel Lp;
    private final JLabel Lq;
    private final JLabel pic;
    private final JButton Add;
    private final JTextField Qnt;
    private final JButton Back;
    ArrayList<Farmaco> far =null;
    private  JScrollPane scrol;
    int cont=0;
    Object[][] farmaci;
    Utente ug;


    ObjectMapper om = new ObjectMapper();


    public TuttiFarmaci(Utente u) throws Exception {
        super("Tutti i Farmaci");
        ug=u;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 750);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);

        MenuBar= new JMenuBar();
        Opzioni = new  JMenu("Opzioni");
        Carrello = new  JMenuItem(String.format("Carrello:%d",u.numCarrello()));
        Disconnetti= new JMenuItem("Disconnetiti");
        Profilo= new JMenuItem(("profilo"));
        MenuBar.add(Opzioni);
        MenuBar.add(Carrello);
        Opzioni.add(Disconnetti);
        Opzioni.add(Profilo);
        this.setJMenuBar(MenuBar);

        Lf = new JLabel("Filtra per nome");
        Lf.setFont(new Font("Arial", Font.PLAIN, 30));
        Lf.setSize(400, 30);
        Lf.setLocation(415, 10);
        this.add(Lf);

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
            far = om.readValue(json, listType);
        } catch (Exception e) {
            throw new Exception(e);
        }

        for (Farmaco f: far)
        {
            cont++;
        }
        farmaci = new Object[cont][3];
        for(int i=0;i<cont;i++)
        {
            farmaci[i][0]= far.get(i).getCodice();
            farmaci[i][1]= far.get(i).getNome();
            farmaci[i][2]= far.get(i).getPrezzo();
        }
        Table = new JTable(farmaci,colName){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        pic= new JLabel();
        pic.setFont(new Font("Arial", Font.PLAIN, 30));
        pic.setSize(400, 30);
        pic.setLocation(415, 120);
        this.add(pic);

        Lc= new JLabel();
        Lc.setFont(new Font("Arial", Font.PLAIN, 30));
        Lc.setSize(400, 30);
        Lc.setLocation(415, 300);
        this.add(Lc);


        Ln= new JLabel();
        Ln.setFont(new Font("Arial", Font.PLAIN, 30));
        Ln.setSize(400, 30);
        Ln.setLocation(415, 350);
        this.add(Ln);


        Lm= new JLabel();
        Lm.setFont(new Font("Arial", Font.PLAIN, 30));
        Lm.setSize(400, 30);
        Lm.setLocation(415, 400);
        this.add(Lm);


        Lca= new JLabel();
        Lca.setFont(new Font("Arial", Font.PLAIN, 30));
        Lca.setSize(400, 30);
        Lca.setLocation(415, 450);
        this.add(Lca);


        Lp= new JLabel();
        Lp.setFont(new Font("Arial", Font.PLAIN, 30));
        Lp.setSize(400, 30);
        Lp.setLocation(415, 500);
        this.add(Lp);


        Lq= new JLabel();
        Lq.setFont(new Font("Arial", Font.PLAIN, 30));
        Lq.setSize(400, 30);
        Lq.setLocation(415, 550);
        this.add(Lq);


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
                Lc.setText(String.format("codice: %d",far.get(riga).getCodice()));
                Ln.setText("nome: "+far.get(riga).getNome());
                Lm.setText("marca: "+far.get(riga).getMarca());
                Lca.setText("categoria: "+far.get(riga).getCategoria());
                Lp.setText(String.format("prezzo: %f",far.get(riga).getPrezzo()));
                Lq.setText(String.format("quantità: %d",far.get(riga).getQuantità()));
                pic.setText("percorso");
            }
        });

        Back= new JButton("Back");
        Back.setFont(new Font("Arial", Font.PLAIN, 30));
        Back.setSize(200, 40);
        Back.setLocation(600, 620);
        this.add(Back);

        Add= new JButton("Aggiungi al Carrello");
        Add.setFont(new Font("Arial", Font.PLAIN, 30));
        Add.setSize(300, 40);
        Add.setLocation(5, 620);
        this.add(Add);

        Qnt= new JTextField("0");
        Qnt.setFont(new Font("Arial", Font.PLAIN, 30));
        Qnt.setSize(100, 40);
        Qnt.setLocation(305, 620);
        this.add(Qnt);
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
        Carrello.addActionListener(this);
        Add.addActionListener(this);
        Back.addActionListener(this);
        Carrello.addActionListener(this);
        setVisible(true);
        if(u.numCarrello() != 0)//aggiornamento lista farmaci per quando torni da un'altra finestra
        {
            for (Farmaco f: u.carrello)
            {
                far.get(f.getCodice()).setQuantità(far.get(f.getCodice()).getQuantità()-f.getQuantità());
            }
        }
    }
    public void modifica()
    {
        this.remove(scrol);
        scrol.remove(Table);
        String t = Filtra.getText();
        int c=0;
        ArrayList<Farmaco> lisa= new ArrayList<Farmaco>();
        for(Farmaco f : far)
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
                Lc.setText(String.format("codice: %d",far.get(riga).getCodice()));
                Ln.setText("nome: "+far.get(riga).getNome());
                Lm.setText("marca: "+far.get(riga).getMarca());
                Lca.setText("categoria: "+far.get(riga).getCategoria());
                Lp.setText(String.format("prezzo: %f",far.get(riga).getPrezzo()));
                Lq.setText(String.format("quantità: %d",far.get(riga).getQuantità()));
                pic.setText("percorso");
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Carrello)
        {
            try {
                dispose();
                new Carrello(ug);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if(e.getSource() == Add)
        {
            int riga=Table.getSelectedRow();
            if(riga==-1)
            {
                JOptionPane.showMessageDialog(null, "nessuna riga selezionata!");
                return;
            }
            String s= Qnt.getText();
            try {
                Integer.parseInt(s);
            } catch(NumberFormatException ee){
                JOptionPane.showMessageDialog(null, "devi inserire un numero!");
                Qnt.setText("");
                return;
            }
            if(far.get(riga).getQuantità() >= Integer.parseInt(Qnt.getText())) {
                //creo un nuovo farmaco con la dimensione nuova da aggiungere al carrello
                boolean trovato= false;
                for( Farmaco f :ug.carrello)
                {
                    if(f.getCodice()==far.get(riga).getCodice())
                    {
                        f.setQuantità(f.getQuantità()+ Integer.parseInt(Qnt.getText()));
                        trovato = true;
                    }
                }
                if(!trovato)
                {
                    Farmaco agg = new Farmaco();
                    agg.setCodice(far.get(riga).getCodice());
                    agg.setCategoria(far.get(riga).getCategoria());
                    agg.setMarca(far.get(riga).getMarca());
                    agg.setNome(far.get(riga).getNome());
                    agg.setPrezzo(far.get(riga).getPrezzo());
                    agg.setPercorsoImg(far.get(riga).getPercorsoImg());
                    agg.setQuantità(Integer.parseInt(Qnt.getText()));
                    ug.carrello.add(agg);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, String.format("non ci sono abbastanza %s",far.get(riga).getNome()));
                Qnt.setText("");
                return;
            }
            int q=far.get(riga).getQuantità()- Integer.parseInt(Qnt.getText());
            far.get(riga).setQuantità(q);
            Lq.setText(String.format("quantità: %d",q));
            Carrello.setText(String.format("Carrello:%d",ug.numCarrello()));
        }
        if (e.getSource() == Back) {
            dispose();
            new HomeCliente(ug);
        }
    }

}
