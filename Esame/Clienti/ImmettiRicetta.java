package Esame.Clienti;

import Esame.Classi.DateLabelFormatter;
import Esame.Classi.Utente;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.mysql.cj.util.Base64Decoder;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.apache.commons.codec.Encoder;
import org.apache.commons.codec.binary.Base64;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Date;
import java.util.*;
import java.util.Properties;

public class ImmettiRicetta extends JFrame implements ActionListener {
    String[] colName= new String[] {"Farmaco" , "approvata" };
    private final JLabel Im;
    private final JButton ScegliRicetta;
    private  JLabel pic;
    private final JButton BtnBack;
    private final JButton BtnInvia;
    private  JLabel Lf;
    private final JComboBox NomeF;
    private  JTable Table;
    private  JScrollPane scrol;
    Object[][] ricette;
    String path = null;
    Utente ug;
    ArrayList<String> cat =null;
    ObjectMapper om = new ObjectMapper();
    public ImmettiRicetta(Utente u)
    {
        super(String.format("Immetti Ricetta"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 700);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);
        ug=u;

        Im = new JLabel();
        Im.setFont(new Font("Arial", Font.PLAIN, 15));
        Im.setSize(400, 30);
        Im.setLocation(270, 500);
        this.add( Im);

        ScegliRicetta=new JButton("Scegli Ricetta");
        ScegliRicetta.setFont(new Font("Arial", Font.PLAIN, 15));
        ScegliRicetta.setSize(250, 30);
        ScegliRicetta.setLocation(5, 500);
        this.add(ScegliRicetta);

        pic= new JLabel();
        pic.setSize(290, 300);
        pic.setLocation(400, 0);
        this.add(pic);

        ricette = new Object[0][2];
        //for per riempire ricette
        Table = new JTable(ricette,colName){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Table.setFont(new Font("Arial", Font.PLAIN, 15));
        Table.setSize(390, 480);
        Table.setFillsViewportHeight(true);
        scrol=new JScrollPane(Table);
        scrol.setLocation(5, 5);
        scrol.setSize(390,480);
        this.add(scrol);
        Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }

        });


        Lf = new JLabel("Nome farmaco:");
        Lf.setFont(new Font("Arial", Font.PLAIN, 30));
        Lf.setSize(300, 30);
        Lf.setLocation(5, 550);
        this.add( Lf);
        NomeF = new JComboBox();
        NomeF.setFont(new Font("Arial", Font.PLAIN, 30));
        NomeF.setSize(300, 30);
        NomeF.setLocation(310, 550);
        this.add(NomeF);

        CollectionType listType =
                om.getTypeFactory().constructCollectionType(ArrayList.class, String.class);
        String url = "http://localhost:8080/nomefarmaci";
        String json = Unirest.get(url).asString().getBody();
        try {
            cat = om.readValue(json, listType);
        } catch (Exception e) {
            try {
                throw new Exception(e);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        for (String s:cat)
        {
            NomeF.addItem(s);
        }

        BtnInvia = new JButton("Invia");
        BtnInvia.setFont(new Font("Arial", Font.PLAIN, 25));
        BtnInvia.setSize(250, 30);
        BtnInvia.setLocation(5, 600);
        this.add(BtnInvia);

        BtnBack = new JButton("Back");
        BtnBack.setFont(new Font("Arial", Font.PLAIN, 25));
        BtnBack.setSize(130, 50);
        BtnBack.setLocation(545, 600);
        this.add(BtnBack);

        BtnBack.addActionListener(this);
        BtnInvia.addActionListener(this);
        ScegliRicetta.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == ScegliRicetta)
        {

            JFileChooser open = new JFileChooser();
            int option = open.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                path = open.getSelectedFile().getAbsolutePath();
                Im.setText(path);
            }
            ImageIcon icon = new ImageIcon(path);
            Image image = icon.getImage();
            Image Nimage = image.getScaledInstance(290,300, Image.SCALE_SMOOTH);
            pic.setIcon(new ImageIcon(Nimage));
        }
        if (e.getSource() == BtnInvia)
        {
            byte[] b = null;
            File f =  new File(path);
            try {
                 b = Files.readAllBytes(f.toPath());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            String encodstring = Base64.encodeBase64String(b);
            String response = Unirest.post(
                    "http://localhost:8080/ricetta")
                    .field("ricetta", encodstring)
                    .field("email", ug.getEmail())
                    .field("nomef",String.valueOf(NomeF.getSelectedItem()))
                    .asString().getBody();
        }
        if (e.getSource() == BtnBack)
        {
            dispose();
            new HomeCliente(ug);
        }
    }

}
