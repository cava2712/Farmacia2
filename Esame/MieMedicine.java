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
import java.io.*;
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
    String[] arr2;
    public MieMedicine(Utente u){

        super("Tutti i Farmaci");
        ug=u;
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
                                    Descrizione.getDocument().remove(leng-1-i,1);
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


        try {
            File file = new File("Esame/miemedicine.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;


            while((line=br.readLine())!=null)
            {
                if(line.startsWith(ug.getEmail())) // trovata linea dell'utente e creiamo linea nuova
                {
                    String[] arr= line.split("☼");
                    arr2 = arr[1].split("§");

                    farmaci = new Object[arr2.length][2];
                    for( int i =0; i< arr2.length; i++)
                    {
                        String[] arr3 = arr2[i].split("¶");
                        farmaci[i][0]=arr3[0];
                        farmaci[i][1]=arr3[1];
                    }
                }
            }
            Table = new JTable(farmaci,colName){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            Table.setFont(new Font("Arial", Font.PLAIN, 15));
            Table.setSize(250, 400);
            Table.setFillsViewportHeight(true);
            scrol=new JScrollPane(Table);
            scrol.setLocation(5, 130);
            scrol.setSize(250,400);
            this.add(scrol);
            Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    for( int i =0; i< arr2.length; i++)
                    {
                        int riga=Table.getSelectedRow();
                        if (riga==i)
                        {
                            String[] arr3 = arr2[i].split("¶");
                            Header.setText(String.format("La terapia del farmaco %s inizia il %s e finisce il %s", arr3[0], arr3[2], arr3[3]));
                            Descrizione.setText((arr3[4]));
                        }
                    }
                }
            });
            try {
                fr.close();
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        } catch (IOException ee) {
            ee.printStackTrace();
        }
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
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


        }
        if (e.getSource() == BtnSalva) {
            if(DataIn.getModel().getValue()==null || DataFin.getModel().getValue()== null || Table.getSelectedRow() == -1)
            {
                JOptionPane.showMessageDialog(null, "Devi selezionare le date e una linea");
                return;
            }
            /*
            try {
                File file = new File("Esame/miemedicine.txt");    //creates a new file instance
                FileReader fr = new FileReader(file);   //reads the file
                BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream


                File ftmp = new File("Esame/tmp.txt");
                FileWriter ftw = new FileWriter(ftmp);
                FileReader ftr = new FileReader(ftmp);
                BufferedReader btr = new BufferedReader(ftr);
                String line;

                while((line=br.readLine())!=null)
                {
                    if(line.startsWith(ug.getEmail())) // trovata linea dell'utente e creiamo linea nuova
                    {
                        for( int i =0; i< arr2.length; i++)
                        {
                            String[] arr3 = arr2[i].split("¶");
                            for(Farmaco f : ug.carrello)
                            {
                                if(Integer.parseInt(arr3[0])==f.getCodice())
                                {
                                    arr3[1]= String.format("%d",f.getQuantità()+Integer.parseInt(arr3[1])) ;
                                    fiol.add(f);
                                    continue;
                                }
                            }
                            modificata += String.format("%s¶%s¶%s¶%s¶%s§",arr3[0],arr3[1],arr3[2],arr3[3],arr3[4]);
                        }
                        boolean vedo=false;
                        for (Farmaco fc :ug.carrello)
                        {
                            vedo=false;
                            for(Farmaco f : fiol)
                            {
                                if(fc==f)
                                    vedo=true;
                            }
                            if(!vedo)
                                modificata += String.format("%s¶%s¶%s¶%s¶%s§",fc.getNome(),fc.getQuantità(),"null","null","null");
                        }
                        continue;
                    }
                    ftw.append(line+"\n");
                }
                if(!trovata)
                {
                    modificata=String.format("%s☼",ug.getEmail());
                    for(Farmaco f : ug.carrello)
                    {
                        modificata += String.format("%s¶%s¶%s¶%s¶%s§",f.getNome(),f.getQuantità(),"null","null","null");
                    }
                }
                ftw.append(modificata);

                try {
                    ftw.close();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
                //copia in miemedicine.txt
                FileWriter fw = new FileWriter(file);
                while((line=btr.readLine())!=null)
                {
                    fw.append(line+"\n");
                }

                FileOutputStream delatet= new FileOutputStream(ftmp);
                delatet.write(("").getBytes());

                try {
                    delatet.close();
                    fr.close();
                    ftr.close();
                    fw.close();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }


            } catch (IOException ee) {
                ee.printStackTrace();
            }
            */
            Date data = (Date)DataIn.getModel().getValue();
            Descrizione.setText(data.toString());

        }
        if (e.getSource() == Back) {

            dispose();
            new HomeCliente(ug);
        }
    }

}

