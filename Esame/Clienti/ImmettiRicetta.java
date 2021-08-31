package Esame.Clienti;

import Esame.Classi.Utente;
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
import java.util.*;
/**
 * <p>Questa è la finestra dove l'utente invia le ricette ai farmacisti</p>
 *
 * @author Luca Barbieri, Davide Cavazzuti
 **/
public class ImmettiRicetta extends JFrame implements ActionListener {
    String[] colName= new String[] {"Farmaco" , "approvata" };
    private final JLabel Im;
    private final JButton ScegliRicetta;
    private  JLabel pic;
    private final JButton BtnBack;
    private final JButton BtnInvia;
    private final JButton BtnElimina;
    private  JLabel Lf;
    private final JComboBox NomeF;
    private  JTable Table;
    private  JScrollPane scrol;
    Object[][] ricette;
    String[] arr2;
    String path = null;
    Utente ug;
    boolean ricettevuoto=true;
    ArrayList<String> listaFarmaci =null;
    ObjectMapper om = new ObjectMapper();
    ArrayList<String> ricetteUtente = new ArrayList<>();
    ArrayList<String> statoRicette = new ArrayList<>();
    int riga;

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
        //leggiamo il file "ricette.txt" per leggere le ricette dell'attuale utente
        try
        {
            File file = new File("Esame/FIle/ricette.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while((line=br.readLine())!=null)
            {
                if(line.startsWith(ug.getEmail())) // trovata linea dell'utente e creiamo linea nuova
                {
                    String[] arr= line.split("☼");
                    arr2 = arr[1].split("§");
                    ricettevuoto=false;
                    ricette = new Object[arr2.length][2];
                    for( int i =0; i< arr2.length; i++)
                    {
                        String[] arr3 = arr2[i].split("¶");
                        ricette[i][0]=arr3[0];
                        ricette[i][1]=arr3[1];
                        ricetteUtente.add(arr3[0]);
                        statoRicette.add(arr3[1]);
                    }
                }
            }
        }
        catch (IOException ee) {
            ee.printStackTrace();
        }

        creaTabella();

        Lf = new JLabel("Nome farmaco:");
        Lf.setFont(new Font("Arial", Font.PLAIN, 30));
        Lf.setSize(300, 30);
        Lf.setLocation(5, 550);
        this.add( Lf);
        NomeF = new JComboBox();
        NomeF.setFont(new Font("Arial", Font.PLAIN, 30));
        NomeF.setSize(300, 30);
        NomeF.setLocation(260, 550);
        this.add(NomeF);

        CollectionType listType =
                om.getTypeFactory().constructCollectionType(ArrayList.class, String.class);
        String url = "http://localhost:8080/nomefarmaci";
        String json = Unirest.get(url).asString().getBody();
        try {
            listaFarmaci = om.readValue(json, listType);
        } catch (Exception e) {
            try {
                throw new Exception(e);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        for (String s: listaFarmaci)
        {
            NomeF.addItem(s);
        }

        BtnInvia = new JButton("Invia");
        BtnInvia.setFont(new Font("Arial", Font.PLAIN, 25));
        BtnInvia.setSize(250, 30);
        BtnInvia.setLocation(5, 600);
        this.add(BtnInvia);

        BtnElimina = new JButton("Elimina");
        BtnElimina.setFont(new Font("Arial", Font.PLAIN, 25));
        BtnElimina.setSize(250, 30);
        BtnElimina.setLocation(265, 600);
        this.add(BtnElimina);

        BtnBack = new JButton("Back");
        BtnBack.setFont(new Font("Arial", Font.PLAIN, 25));
        BtnBack.setSize(130, 50);
        BtnBack.setLocation(545, 600);
        this.add(BtnBack);

        BtnBack.addActionListener(this);
        BtnInvia.addActionListener(this);
        BtnElimina.addActionListener(this);
        ScegliRicetta.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ScegliRicetta) {

            JFileChooser open = new JFileChooser();
            int option = open.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                path = open.getSelectedFile().getAbsolutePath();
                Im.setText(path);
            }
            ImageIcon icon = new ImageIcon(path);
            Image image = icon.getImage();
            Image Nimage = image.getScaledInstance(290, 300, Image.SCALE_SMOOTH);
            pic.setIcon(new ImageIcon(Nimage));
        }
        if (e.getSource() == BtnElimina) {
            try {
                riga = Table.getSelectedRow();
                if(riga==-1)
                {
                    JOptionPane.showMessageDialog(null, "Devi selezionare una riga delle ricette");
                    return;
                }
                File file = new File("Esame/FIle/ricette.txt");    //creates a new file instance
                FileReader fr = new FileReader(file);   //reads the file
                BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
                File ftmp = new File("Esame/FIle/tmp.txt");
                FileWriter ftw = new FileWriter(ftmp);
                FileReader ftr = new FileReader(ftmp);
                BufferedReader btr = new BufferedReader(ftr);
                String line;
                String modificata = "";
                while ((line = br.readLine()) != null) {
                    if (line.startsWith(ug.getEmail())) // trovata linea dell'utente e creiamo linea nuova
                    {
                        String[] arr = line.split("☼");
                        arr2 = arr[1].split("§");
                        modificata = String.format("%s☼", ug.getEmail());
                        for (int i = 0; i < arr2.length; i++) {
                            String[] arr3 = arr2[i].split("¶");
                            if (ricetteUtente.get(riga).compareTo(arr3[0]) == 0) {

                            } else
                                modificata += arr2[i] + '§';
                        }
                        continue;
                    }
                    ftw.append(line + "\n");
                }
                if(!modificata.equals(String.format("%s☼", ug.getEmail())))
                {
                    ftw.append(modificata);
                }

                try {
                    ftw.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                //copia in ricette.txt
                FileWriter fw = new FileWriter(file);
                while ((line = btr.readLine()) != null) {
                    fw.append(line + "\n");
                }

                FileOutputStream delatet = new FileOutputStream(ftmp);
                delatet.write(("").getBytes());

                try {
                    delatet.close();
                    fr.close();
                    ftr.close();
                    fw.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


            } catch (IOException ee) {
                ee.printStackTrace();
            }
            File fIm = new File(String.format("Esame/pic/Ricette/%s_%s.jpg", ug.getEmail(), ricetteUtente.get(riga)));
            fIm.delete();
            ricetteUtente.remove(riga);
            statoRicette.remove(riga);
            ricette = new Object[ricetteUtente.size()][2];
            for (int i = 0; i < ricetteUtente.size(); i++) {
                ricette[i][0] = ricetteUtente.get(i);
                ricette[i][1] = statoRicette.get(i);
            }
            scrol.remove(Table);
            this.remove(scrol);
            creaTabella();

            JOptionPane.showMessageDialog(null, "Ricetta eliminata!");

        }
        if (e.getSource() == BtnInvia)
        {
            if(Im.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Devi selezionare l'immagine della ricetta!");
                return;
            }
            for (int i=0;i< ricette.length;i++)
            {
                if(String.valueOf(NomeF.getSelectedItem()).equals(ricette[i][0]))
                {
                    JOptionPane.showMessageDialog(null, "È già presente una ricetta per questo farmaco!");
                    return;
                }
            }
            boolean trovata=false;
            File f =  new File(path);
            String response=Unirest.post("http://localhost:8080/ricetta")
                    .field("ricetta", f)
                    .header("email", ug.getEmail())
                    .header("nomef",String.valueOf(NomeF.getSelectedItem()))
                    .asString().getBody();

            try {

                File file = new File("Esame/FIle/ricette.txt");    //creates a new file instance
                FileReader fr = new FileReader(file);   //reads the file
                BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream


                File ftmp = new File("Esame/FIle/tmp.txt");
                FileWriter ftw = new FileWriter(ftmp);
                FileReader ftr = new FileReader(ftmp);
                BufferedReader btr = new BufferedReader(ftr);
                String line;
                String modificata="";
                while((line=br.readLine())!=null)
                {
                    if(line.startsWith(ug.getEmail())) // trovata linea dell'utente e creiamo linea nuova
                    {
                        trovata=true;
                        modificata=String.format("%s☼",ug.getEmail());
                        for( int i =0; i< arr2.length; i++)
                        {
                            modificata += arr2[i]+'§';
                        }
                        modificata += String.format("%s¶%s§",String.valueOf(NomeF.getSelectedItem()),"non visionata");
                        continue;
                    }
                    ftw.append(line+"\n");
                }
                if(!trovata)
                {
                    modificata= String.format("%s☼",ug.getEmail()) + String.format("%s¶%s§",String.valueOf(NomeF.getSelectedItem()),"non visionata");
                }
                ftw.append(modificata);

                try {
                    ftw.close();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
                //copia in ricette.txt
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
            int ricetteN=0;
            if(!ricettevuoto)
                ricetteN = ricette.length;
            if(ricetteN==0)
            {
                ricette=new Object[1][2];
                ricette[0][0]=String.valueOf(NomeF.getSelectedItem());
                ricette[0][1]="non visionata";
                arr2= new String[1];
                arr2[0] = String.format("%s¶%s",String.valueOf(NomeF.getSelectedItem()),"non visionata");
                ricetteUtente.add(String.format("%s",String.valueOf(NomeF.getSelectedItem())));
                statoRicette.add("non visionata");
                ricettevuoto=false;
            }
            else
            {
                ricetteUtente.add(String.valueOf(NomeF.getSelectedItem()));
                statoRicette.add("non visionata");
                ricette= new Object[ricetteUtente.size()][2];
                for(int i =0; i< ricetteUtente.size();i++)
                {
                    ricette[i][0]=ricetteUtente.get(i);
                    ricette[i][1]=statoRicette.get(i);
                }
                arr2 = Arrays.copyOf(arr2,arr2.length+1);
                arr2[arr2.length-1]=String.format("%s¶%s",String.valueOf(NomeF.getSelectedItem()),"non visionata");
            }
            scrol.remove(Table);
            this.remove(scrol);
            creaTabella();
            if(response.equals("ok"))
                JOptionPane.showMessageDialog(null, "Ricetta Inviata correttamente!");
        }
        if (e.getSource() == BtnBack)
        {
            dispose();
            new HomeCliente(ug);
        }
    }
    private void creaTabella() {
        Table = new JTable(ricette,colName){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Table.setFont(new Font("Arial", Font.PLAIN, 15));
        Table.setSize(390, 480);
        Table.setFillsViewportHeight(true);
        scrol = new JScrollPane(Table);
        scrol.setLocation(5, 5);
        scrol.setSize(390, 480);
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
                        ImageIcon icon = new ImageIcon(String.format("Esame/pic/Ricette/%s_%s.jpg",ug.getEmail(),arr3[0]));
                        Image image = icon.getImage();
                        Image Nimage = image.getScaledInstance(290,300, Image.SCALE_SMOOTH);
                        pic.setIcon(new ImageIcon(Nimage));
                        return;
                    }
                }
            }

        });
    }
}
