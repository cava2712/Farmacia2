package Esame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import kong.unirest.Unirest;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import spark.utils.StringUtils;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.jdatepicker.*;

public class MieMedicine extends JFrame implements ActionListener {
    String[] colName= new String[] { "NomeProdotto","Quantità"};
    private  JTable Table;
    private final JMenuBar MenuBar;
    private final JMenu Opzioni;
    private final JMenuItem Carrello;
    private final JMenuItem Disconnetti,Profilo;
    private final JButton BtnModifica;
    private final JButton BtnSalva;
    private final JButton Back;
    private  JScrollPane scrol;
    private  JScrollPane scrolD;
    private final JLabel Lin;
    private final JDatePickerImpl DataIn;
    private final JLabel Lfin;
    private final JDatePickerImpl DataFin;
    private final JTextArea Header;
    private final JTextArea Descrizione;

    ArrayList<Farmaco> far =null;
    int cont=0;
    Object[][] farmaci;
    Utente ug;
    JFrame f;



    ObjectMapper om = new ObjectMapper();


    public MieMedicine() throws Exception {

        super("Tutti i Farmaci");
        //ug=u;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 700);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);
        f= this;

        MenuBar= new JMenuBar();
        Opzioni = new  JMenu("Opzioni");
        Carrello = new  JMenuItem(String.format("Carrello:0"));
        Disconnetti= new JMenuItem("Disconnetiti");
        Profilo= new JMenuItem(("profilo"));
        MenuBar.add(Opzioni);
        MenuBar.add(Carrello);
        Opzioni.add(Disconnetti);
        Opzioni.add(Profilo);
        this.setJMenuBar(MenuBar);

        Lin = new JLabel("data inizio terapia");
        Lin.setFont(new Font("Arial", Font.PLAIN, 15));
        Lin.setLocation(5, 65);
        Lin.setSize(150,40);
        this.add(Lin);
        UtilDateModel DateMod = new UtilDateModel();
        DateMod.setDate(LocalDateTime.now().getYear(),LocalDateTime.now().getMonthValue(),LocalDateTime.now().getDayOfMonth());
        Properties p=new Properties();
        p.put("text.today","Today");
        p.put("text.month","Month");
        p.put("text.year","Year");
        DataIn =  new JDatePickerImpl(new JDatePanelImpl(DateMod,p),new DateLabelFormatter());
        DataIn.setLocation(130, 70);
        DataIn.setSize(170,40);
        this.add(DataIn);


        Lfin = new JLabel("data fine terapia");
        Lfin.setFont(new Font("Arial", Font.PLAIN, 15));
        Lfin.setLocation(360, 65);
        Lfin.setSize(150,40);
        this.add(Lfin);
        UtilDateModel DateMod2 = new UtilDateModel();
        DateMod2.setDate(LocalDateTime.now().getYear(),LocalDateTime.now().getMonthValue(),LocalDateTime.now().getDayOfMonth());
        DataFin =  new JDatePickerImpl(new JDatePanelImpl(DateMod2,p),new DateLabelFormatter());
        DataFin.setLocation(480, 70);
        DataFin.setSize(170,40);
        this.add(DataFin);

        Header = new JTextArea("Testo iniziale");
        Header.setFont(new Font("Arial", Font.PLAIN, 15));
        Header.setLocation(280, 130);
        Header.setSize(400,100);
        Header.setEditable(false);
        this.add(Header);

        Descrizione = new JTextArea("");
        Descrizione.setLineWrap(true);
        Descrizione.setWrapStyleWord(true);
        Descrizione.setFont(new Font("Arial", Font.PLAIN, 15));
        Descrizione.setLocation(280, 240);
        Descrizione.setSize(400,290);

        scrolD=new JScrollPane(Descrizione);
        scrolD.setLocation(280, 240);
        scrolD.setSize(400,290);
        this.add(scrolD);

        farmaci = new Object[cont][3];
        for(int i=0;i<cont;i++)
        {
            farmaci[i][0]= "adf";
            farmaci[i][1]= "adf";
            farmaci[i][2]= "adf";
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
        scrol.setLocation(5, 130);
        scrol.setSize(250,400);
        this.add(scrol);
        Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }

        });

        Back= new JButton("Back");
        Back.setFont(new Font("Arial", Font.PLAIN, 30));
        Back.setSize(140, 40);
        Back.setLocation(540, 5);
        this.add(Back);

        BtnModifica= new JButton("Modifica Quantità");
        BtnModifica.setFont(new Font("Arial", Font.PLAIN, 25));
        BtnModifica.setSize(250, 40);
        BtnModifica.setLocation(5, 550);
        this.add(BtnModifica);

        BtnSalva= new JButton("Salva informazioni");
        BtnSalva.setFont(new Font("Arial", Font.PLAIN, 25));
        BtnSalva.setSize(400, 40);
        BtnSalva.setLocation(280, 550);
        this.add(BtnSalva);

        BtnModifica.addActionListener(this);
        BtnSalva.addActionListener(this);
        Back.addActionListener(this);
        Carrello.addActionListener(this);
        Descrizione.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(Descrizione.getDocument().getLength()> 500)
                {
                    Runnable r = new Runnable() {
                        @Override
                        public void run() {
                            int leng = Descrizione.getDocument().getLength();

                            JOptionPane.showMessageDialog(null, "puoi inserire al massimo 500 caratteri nella descrizione");
                            try {
                                for(int i =0; i<leng-500;i++)
                                    Descrizione.getDocument().remove(leng-2,1);
                            } catch (BadLocationException badLocationException) {
                                badLocationException.printStackTrace();
                            }
                        }
                    };
                    SwingUtilities.invokeLater(r);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        setVisible(true);
        /*
        if(u.numCarrello() != 0)//aggiornamento lista farmaci per quando torni da un'altra finestra
        {
            for (Farmaco f: u.carrello)
            {
                far.get(f.getCodice()).setQuantità(far.get(f.getCodice()).getQuantità()-f.getQuantità());
            }
        }*/
    }
    public void modifica()
    {
        this.remove(scrol);
        scrol.remove(Table);
        int c=0;
        ArrayList<Farmaco> lisa= new ArrayList<Farmaco>();
        for(Farmaco f : far)
        {

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

        if (e.getSource() == BtnModifica) {
            dispose();
            new HomeCliente(ug);
        }
        if (e.getSource() == BtnSalva) {
            Date data = (Date)DataIn.getModel().getValue();
            Descrizione.setText(data.toString());
        }
        if (e.getSource() == Back) {

            dispose();
            new HomeCliente(ug);
        }
    }
    public static void main(String[] args) throws Exception {
        new Esame.MieMedicine();
    }
}

