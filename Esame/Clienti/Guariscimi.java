package Esame.Clienti;

import Esame.Classi.Farmaco;
import Esame.Classi.Utente;
import Esame.Login.loginInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import kong.unirest.Unirest;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
/**
 * <p>Questa è la finestra chiamata "Guariscimi" perchè il cliente può filtrare i farmaci in base alla categoria </p>
 *
 * @author Luca Barbieri, Davide Cavazzuti
 **/
public class Guariscimi extends JFrame implements ActionListener {
    private final JComboBox Filtra;
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
    private final JLabel Lr;
    private  JLabel pic;
    private final JButton Add;
    private final JTextField Qnt;
    private final JButton Back;
    ArrayList<Farmaco> listaFarmaci =null;
    ArrayList<String> listaCategorie =null;
    private  JScrollPane scrol;
    int cont=0;
    Object[][] farmaci;
    Utente ug;
    JFrame f;
    ArrayList<String> ricetteUtente = new ArrayList<>();
    ArrayList<String> statoRicette = new ArrayList<>();
    String[] arr2;
    ObjectMapper om = new ObjectMapper();

    public Guariscimi(Utente u) throws Exception {
        super("Guariscimi");
        ug=u;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 800);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);
        f= this;

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

        Filtra = new JComboBox();
        Filtra.setFont(new Font("Arial", Font.PLAIN, 30));
        Filtra.setSize(400, 30);
        Filtra.setLocation(5, 10);
        this.add(Filtra);

        CollectionType listType =
                om.getTypeFactory().constructCollectionType(ArrayList.class, String.class);
        String url = "http://localhost:8080/categorie";
        String json = Unirest.get(url).asString().getBody();
        try {
            listaCategorie = om.readValue(json, listType);
        } catch (Exception e) {
            throw new Exception(e);
        }
        for (String s:listaCategorie)
        {
            Filtra.addItem(s);
        }

        listType =
                om.getTypeFactory().constructCollectionType(ArrayList.class, Farmaco.class);

        url = "http://localhost:8080/farmaci";
        json = Unirest.get(url).asString().getBody();
        try {
            listaFarmaci = om.readValue(json, listType);
        } catch (Exception e) {
            throw new Exception(e);
        }
        farmaci = new Object[listaFarmaci.size()][3];
        for(int i=0;i<listaFarmaci.size();i++)
        {
            farmaci[i][0]= listaFarmaci.get(i).getCodice();
            farmaci[i][1]= listaFarmaci.get(i).getNome();
            farmaci[i][2]= listaFarmaci.get(i).getPrezzo();
        }
        if(u.numCarrello() != 0)//aggiornamento lista farmaci per quando torni da un'altra finestra
        {
            for (Farmaco f: u.carrello)
            {
                Farmaco temp=listaFarmaci.stream().filter(farmaco -> f.getCodice()==farmaco.getCodice()).findFirst().orElse(null);
                listaFarmaci.get(listaFarmaci.indexOf(temp)).setQuantita(listaFarmaci.get(listaFarmaci.indexOf(temp)).getQuantita()-f.getQuantita());
            }
        }
        pic= new JLabel();
        pic.setLocation(415, 90);
        f.add(pic);

        Lc= new JLabel();
        Lc.setFont(new Font("Arial", Font.PLAIN, 20));
        Lc.setSize(400, 30);
        Lc.setLocation(415, 300);
        this.add(Lc);

        Ln= new JLabel();
        Ln.setFont(new Font("Arial", Font.PLAIN, 20));
        Ln.setSize(400, 30);
        Ln.setLocation(415, 350);
        this.add(Ln);

        Lm= new JLabel();
        Lm.setFont(new Font("Arial", Font.PLAIN, 20));
        Lm.setSize(400, 30);
        Lm.setLocation(415, 400);
        this.add(Lm);

        Lca= new JLabel();
        Lca.setFont(new Font("Arial", Font.PLAIN, 20));
        Lca.setSize(400, 30);
        Lca.setLocation(415, 450);
        this.add(Lca);

        Lp= new JLabel();
        Lp.setFont(new Font("Arial", Font.PLAIN, 20));
        Lp.setSize(400, 30);
        Lp.setLocation(415, 500);
        this.add(Lp);

        Lq= new JLabel();
        Lq.setFont(new Font("Arial", Font.PLAIN, 20));
        Lq.setSize(400, 30);
        Lq.setLocation(415, 550);
        this.add(Lq);

        Lr= new JLabel();
        Lr.setFont(new Font("Arial", Font.PLAIN, 20));
        Lr.setSize(400, 30);
        Lr.setLocation(415, 600);
        this.add(Lr);

        creazioneTabella();

        Back= new JButton("Back");
        Back.setFont(new Font("Arial", Font.PLAIN, 30));
        Back.setSize(200, 40);
        Back.setLocation(600, 650);
        this.add(Back);

        Add= new JButton("Aggiungi al Carrello");
        Add.setFont(new Font("Arial", Font.PLAIN, 30));
        Add.setSize(300, 40);
        Add.setLocation(5, 650);
        this.add(Add);

        Qnt= new JTextField("0");
        Qnt.setFont(new Font("Arial", Font.PLAIN, 30));
        Qnt.setSize(100, 40);
        Qnt.setLocation(305, 650);
        this.add(Qnt);
        Filtra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cont=0;
                ArrayList<Farmaco> fcat = new ArrayList<Farmaco>();
                for (Farmaco f: listaFarmaci)
                {
                    if(f.getCategoria().equals(Filtra.getSelectedItem()))
                    {
                        fcat.add(f);
                        cont++;
                    }
                }
                farmaci = new Object[cont][3];
                for(int i=0;i<cont;i++)
                {
                    farmaci[i][0]= fcat.get(i).getCodice();
                    farmaci[i][1]= fcat.get(i).getNome();
                    farmaci[i][2]= fcat.get(i).getPrezzo();
                }
                scrol.remove(Table);
                f.remove(scrol);
                creazioneTabella();
            }
        });
        Add.addActionListener(this);
        Back.addActionListener(this);
        Carrello.addActionListener(this);
        Disconnetti.addActionListener(this);
        Profilo.addActionListener(this);
        setVisible(true);

        try {
            File file = new File("Esame/FIle/ricette.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split("☼");
                if(arr[0].equals(ug.getEmail()))
                {
                    arr2 = arr[1].split("§");
                    for (int i = 0; i < arr2.length; i++) {
                        String[] arr3 = arr2[i].split("¶");
                        ricetteUtente.add(arr3[0]);
                        statoRicette.add(arr3[1]);
                    }
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
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
            int sCodice= (int)farmaci[riga][0];
            Farmaco sFarmaco=listaFarmaci.stream().filter(farmaco -> sCodice==farmaco.getCodice()).findFirst().orElse(null); //farmaco selezionato dalla tabella
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
            //verifichiamo se il farmaco che sta provando a comprare necessita di "Ricetta"
            if(sFarmaco.getRicetta().equals("true"))
            {
                Boolean trovata=false;
                for (int i = 0; i< ricetteUtente.size(); i++)
                {
                    if(ricetteUtente.get(i).equals(sFarmaco.getNome()))
                    {
                        if(statoRicette.get(i).equals("approvata")) {
                            JOptionPane.showMessageDialog(null, "La tua ricetta è stata approvata!");
                        }
                        else if(statoRicette.get(i).equals("rifiutata")) {
                            JOptionPane.showMessageDialog(null, "La tua ricetta non è stata approvata!");
                            return;
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "La tua ricetta non è ancora stata visionata!");
                            return;
                        }
                        trovata=true;
                        break;
                    }
                }
                if(!trovata)
                {
                    JOptionPane.showMessageDialog(null, "Questo farmaco richiede ricetta");
                    return;
                }
            }
            if(sFarmaco.getQuantita() >= Integer.parseInt(Qnt.getText())) {
                //creo un nuovo farmaco con la dimensione nuova da aggiungere al carrello se non era già presente
                boolean trovato= false;
                for( Farmaco f :ug.carrello)
                {
                    if(f.getCodice()== sFarmaco.getCodice())
                    {
                        f.setQuantita(f.getQuantita()+ Integer.parseInt(Qnt.getText()));
                        trovato = true;
                    }
                }
                if(!trovato)
                {
                    Farmaco agg = new Farmaco();
                    agg.setCodice(sFarmaco.getCodice());
                    agg.setCategoria(sFarmaco.getCategoria());
                    agg.setMarca(sFarmaco.getMarca());
                    agg.setNome(sFarmaco.getNome());
                    agg.setPrezzo(sFarmaco.getPrezzo());
                    agg.setPercorsoImg(sFarmaco.getPercorsoImg());
                    agg.setQuantita(Integer.parseInt(Qnt.getText()));
                    ug.carrello.add(agg);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, String.format("non ci sono abbastanza %s", sFarmaco.getNome()));
                Qnt.setText("");
                return;
            }
            int q= sFarmaco.getQuantita()- Integer.parseInt(Qnt.getText());
            listaFarmaci.get(listaFarmaci.indexOf(sFarmaco)).setQuantita(q);
            Lq.setText(String.format("quantità: %d",q));
            Carrello.setText(String.format("Carrello:%d",ug.numCarrello()));
        }
        if (e.getSource() == Back) {
            dispose();
            new HomeCliente(ug);
        }
        if(e.getSource()== Disconnetti)
        {
            dispose();
            new loginInterface();
        }
        if(e.getSource()== Profilo)
        {
            dispose();
            new Profilo(ug);
        }
    }

    private void creazioneTabella() {
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
        f.add(scrol);
        Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int riga=Table.getSelectedRow();
                int sCodice= (int)farmaci[riga][0];
                Farmaco sFarmaco=listaFarmaci.stream().filter(farmaco -> sCodice==farmaco.getCodice()).findFirst().orElse(null);
                Lc.setText(String.format("codice: %d", sFarmaco.getCodice()));
                Ln.setText("nome: "+ sFarmaco.getNome());
                Lm.setText("marca: "+ sFarmaco.getMarca());
                Lca.setText("categoria: "+ sFarmaco.getCategoria());
                Lp.setText(String.format("prezzo: %f", sFarmaco.getPrezzo()));
                Lq.setText(String.format("quantità: %d", sFarmaco.getQuantita()));
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
