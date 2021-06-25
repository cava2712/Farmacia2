package Esame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import kong.unirest.Unirest;
import org.eclipse.jetty.util.ArrayUtil;
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
import java.util.*;

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
    private final JTextField Mquantità;

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
        Header.setLineWrap(true);
        Header.setWrapStyleWord(true);
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
        BtnModifica.setFont(new Font("Arial", Font.PLAIN, 20));
        BtnModifica.setSize(200, 40);
        BtnModifica.setLocation(5, 550);
        this.add(BtnModifica);
        Mquantità = new JTextField("0");
        Mquantità.setFont(new Font("Arial", Font.PLAIN, 25));
        Mquantità.setSize(50, 40);
        Mquantità.setLocation(205, 550);
        this.add(Mquantità);

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
            if(farmaci == null)
                farmaci =new Object[0][2];
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
                    EventoTabella();
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
            int riga=Table.getSelectedRow();
            String fzero="";
            boolean zero=false;
            if(riga==-1)
            {
                JOptionPane.showMessageDialog(null, "nessuna riga selezionata!");
                return;
            }
            String s= Mquantità.getText();
            int q=0;
            try {
                q=Integer.parseInt(s);
            } catch(NumberFormatException ee){
                JOptionPane.showMessageDialog(null, "devi inserire un numero!");
                Mquantità.setText("");
                return;
            }
            int a = Integer.parseInt((String) farmaci[riga][1]);
            if( q > a)
            {
                JOptionPane.showMessageDialog(null, "la quantità modificata non può essere superiore alla quantità posseduta");
                return;
            }


            try {
                File file = new File("Esame/miemedicine.txt");    //creates a new file instance
                FileReader fr = new FileReader(file);   //reads the file
                BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream


                File ftmp = new File("Esame/tmp.txt"); //file temporaneo
                FileWriter ftw = new FileWriter(ftmp);
                FileReader ftr = new FileReader(ftmp);
                BufferedReader btr = new BufferedReader(ftr);
                String line;
                String modificata="";
                boolean ric = true;
                while((line=br.readLine())!=null)
                {
                    if(line.startsWith(ug.getEmail())) // trovata linea dell'utente e creiamo linea nuova
                    {
                        modificata=String.format("%s☼",ug.getEmail());
                        for( int i =0; i< arr2.length; i++)
                        {
                            riga=Table.getSelectedRow();
                            if (riga==i)
                            {
                                String[] arr3 = arr2[i].split("¶");
                                arr3[1] = String.format("%d",Integer.parseInt(arr3[1])-q);// modifichiamo la quantità
                                if(Integer.parseInt(arr3[1])==0)
                                {
                                    zero=true;
                                    arr2[i]=null;
                                    ric = false;
                                    fzero=arr3[0];
                                }
                                if(ric) {
                                    arr2[i]= String.format("%s¶%s¶%s¶%s¶%s",arr3[0],arr3[1],arr3[2],arr3[3],arr3[4]);
                                    modificata += String.format("%s¶%s¶%s¶%s¶%s§", arr3[0], arr3[1], arr3[2], arr3[3], arr3[4]);
                                }
                                ric=true;
                                continue;
                            }
                            modificata += arr2[i]+'§';
                        }
                        continue;
                    }
                    ftw.append(line+"\n");
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
            arr2 = ArrayUtil.removeNulls(arr2);
            farmaci = new Object[arr2.length][2];
            for( int i =0; i< arr2.length; i++)
            {
                String[] arr3 = arr2[i].split("¶");
                farmaci[i][0]=arr3[0];
                farmaci[i][1]=arr3[1];
            }
            scrol.remove(Table);
            this.remove(scrol);
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
                    EventoTabella();
                }
            });
            if(zero) {
                int result = JOptionPane.showConfirmDialog(this, String.format("Il farmaco %s è finito! \n vuoi comprarlo di nuovo?", fzero), "Attenzione!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                    try {
                        new TuttiFarmaci(ug, fzero);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                } else if (result == JOptionPane.NO_OPTION) {
                } else {
                }
            }
        }
        if (e.getSource() == BtnSalva) {
            int riga=Table.getSelectedRow();
            if(riga==-1 || DataIn.getModel().getValue()==null || DataFin.getModel().getValue()==null)
            {
                JOptionPane.showMessageDialog(null, "devi selezionare una riga e devi inserire le date");
                return;
            }
            if(((Date)DataFin.getModel().getValue()).before(((Date)DataIn.getModel().getValue())))
            {
                JOptionPane.showMessageDialog(null, "La data iniziale deve essere precedente a quella finale");
                return;
            }
            try {
                File file = new File("Esame/miemedicine.txt");    //creates a new file instance
                FileReader fr = new FileReader(file);   //reads the file
                BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream


                File ftmp = new File("Esame/tmp.txt");
                FileWriter ftw = new FileWriter(ftmp);
                FileReader ftr = new FileReader(ftmp);
                BufferedReader btr = new BufferedReader(ftr);
                String line;
                String modificata="";
                String desc = Descrizione.getText();
                desc=desc.replace("☼","");
                desc=desc.replace("¶","");
                desc=desc.replace("§","");
                while((line=br.readLine())!=null)
                {
                    if(line.startsWith(ug.getEmail())) // trovata linea dell'utente e creiamo linea nuova
                    {
                        modificata=String.format("%s☼",ug.getEmail());
                        for( int i =0; i< arr2.length; i++)
                        {
                            if (riga==i)
                            {
                                String[] arr3 = arr2[i].split("¶");
                                String n= "null";
                                if(desc.trim().equals(""))
                                {
                                    arr2[i]= String.format("%s¶%s¶%s¶%s¶%s",arr3[0],arr3[1],DataToString((Date)DataIn.getModel().getValue()),DataToString((Date)DataFin.getModel().getValue()),n);
                                    modificata += String.format("%s¶%s¶%s¶%s¶%s§",arr3[0],arr3[1],DataToString((Date)DataIn.getModel().getValue()),DataToString((Date)DataFin.getModel().getValue()),n);
                                    Header.setText(String.format("La terapia del farmaco %s inizia il %s e finisce il %s", arr3[0], DataToString((Date)DataIn.getModel().getValue()),DataToString((Date)DataFin.getModel().getValue())));
                                    continue;
                                }
                                arr2[i]= String.format("%s¶%s¶%s¶%s¶%s",arr3[0],arr3[1],DataToString((Date)DataIn.getModel().getValue()),DataToString((Date)DataFin.getModel().getValue()),desc);
                                modificata += String.format("%s¶%s¶%s¶%s¶%s§",arr3[0],arr3[1],DataToString((Date)DataIn.getModel().getValue()),DataToString((Date)DataFin.getModel().getValue()),desc);
                                Header.setText(String.format("La terapia del farmaco %s inizia il %s e finisce il %s", arr3[0], DataToString((Date)DataIn.getModel().getValue()),DataToString((Date)DataFin.getModel().getValue())));
                                continue;
                            }
                            modificata += arr2[i]+'§';
                        }
                        continue;
                    }
                    ftw.append(line+"\n");
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
            DataIn.getModel().setSelected(false);
            DataFin.getModel().setSelected(false);
            Header.setText("");
            Descrizione.setText("");
        }
        if (e.getSource() == Back) {

            dispose();
            new HomeCliente(ug);
        }
    }
    public String DataToString ( Date d)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return String.format("%s-%s-%s",c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
    }
    public void EventoTabella()
    {
        Descrizione.setText("");
        DataIn.getModel().setSelected(false);
        DataFin.getModel().setSelected(false);
        for( int i =0; i< arr2.length; i++)
        {
            int riga=Table.getSelectedRow();
            if (riga==i)
            {
                String[] arr3 = arr2[i].split("¶");
                Header.setText(String.format("La terapia del farmaco %s inizia il %s e finisce il %s", arr3[0], arr3[2], arr3[3]));
                if(arr3[4].equals("null"))
                {
                    if(arr3[2].equals("null"))
                    {
                        Header.setText(String.format("La terapia del farmaco %s inizia il NON DEFINITO e finisce il NON DEFINITO", arr3[0]));
                    }
                    Descrizione.setText("");
                    return;
                }
                if(arr3[2].equals("null"))
                {
                    Header.setText(String.format("La terapia del farmaco %s inizia il NON DEFINITO e finisce il NON DEFINITO", arr3[0]));
                    return;
                }

                Descrizione.setText((arr3[4]));
                String[] dataIn = arr3[2].split("-");
                String[] dataFin = arr3[3].split("-");
                DataIn.getModel().setDate(Integer.parseInt(dataIn[0]),Integer.parseInt(dataIn[1]),Integer.parseInt(dataIn[2]));
                DataFin.getModel().setDate(Integer.parseInt(dataFin[0]),Integer.parseInt(dataFin[1]),Integer.parseInt(dataFin[2]));
                DataIn.getModel().setSelected(true);
                DataFin.getModel().setSelected(true);
                return;
            }
        }
    }

}

