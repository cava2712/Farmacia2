package Esame.Farmacisti;

import Esame.Classi.Utente;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
/**
 * <p>Questa è la finestra dove il farmacista attuale può gestire tutte le ricette di tutti i clienti </p>
 *
 * @author Luca Barbieri, Davide Cavazzuti
 **/
public class ControllaRicette extends JFrame implements ActionListener {
    String[] colName= new String[] {"Cliente" , "Farmaco" };
    private  JLabel pic;
    private  JLabel stat;
    private final JButton BtnBack;
    private final JButton BtnApprova;
    private final JButton BtnRifiuta;
    private final JButton BtnIngrandisci;
    private final JButton BtnEliminaR;
    private  JTable Table;
    private  JScrollPane scrol;
    Object[][] ricette;
    String[] arr2;
    Utente ug;
    ArrayList<String> listaUtenti = new ArrayList<>();
    ArrayList<String> listaFarmaci = new ArrayList<>();
    ArrayList<String> listaStati = new ArrayList<>();
    int cont=0;
    int riga;

    public ControllaRicette(Utente u)
    {
        super(String.format("Immetti Ricetta"));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(720, 700);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);
        ug=u;
        pic= new JLabel();
        pic.setSize(300, 300);
        pic.setLocation(400, 0);
        this.add(pic);
        stat = new JLabel();
        stat.setFont(new Font("Arial", Font.PLAIN, 20));
        stat.setSize(300, 50);
        stat.setLocation(400, 320);
        this.add(stat);

        String NomeU;
        ricette = new Object[0][2];
        //leggiamo il file per caricare tutte le ricette di tutti i clienti
        try
        {
            File file = new File("Esame/FIle/ricette.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            while((line=br.readLine())!=null)
            {
                String[] arr= line.split("☼");
                NomeU=arr[0];
                arr2 = arr[1].split("§");
                for( int i =0; i< arr2.length; i++)
                {
                    String[] arr3 = arr2[i].split("¶");
                    listaUtenti.add(NomeU);
                    listaFarmaci.add(arr3[0]);
                    listaStati.add(arr3[1]);
                    cont++;
                }
            }
        }
        catch (IOException ee) {
            ee.printStackTrace();
        }
        ricette = new Object[listaUtenti.size()][2];
        for (int i = 0; i< listaUtenti.size(); i++)
        {
            ricette[i][0]= listaUtenti.get(i);
            ricette[i][1]= listaFarmaci.get(i);
        }
        creaTabella();

        BtnApprova = new JButton("Approva ricetta");
        BtnApprova.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnApprova.setSize(170, 50);
        BtnApprova.setLocation(5, 530);
        this.add(BtnApprova);

        BtnRifiuta = new JButton("Rifiuta ricetta");
        BtnRifiuta.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnRifiuta.setSize(170, 50);
        BtnRifiuta.setLocation(180, 530);
        this.add(BtnRifiuta);

        BtnIngrandisci = new JButton("Ingrandisci ricetta");
        BtnIngrandisci.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnIngrandisci.setSize(170, 50);
        BtnIngrandisci.setLocation(355, 530);
        this.add(BtnIngrandisci);

        BtnEliminaR = new JButton("Elimina ricetta");
        BtnEliminaR.setFont(new Font("Arial", Font.PLAIN, 15));
        BtnEliminaR.setSize(170, 50);
        BtnEliminaR.setLocation(530, 530);
        this.add(BtnEliminaR);

        BtnBack = new JButton("Back");
        BtnBack.setFont(new Font("Arial", Font.PLAIN, 25));
        BtnBack.setSize(130, 50);
        BtnBack.setLocation(545, 600);
        this.add(BtnBack);

        BtnBack.addActionListener(this);
        BtnApprova.addActionListener(this);
        BtnRifiuta.addActionListener(this);
        BtnIngrandisci.addActionListener(this);
        BtnEliminaR.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == BtnApprova)
        {
            try {
                riga=Table.getSelectedRow();
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

                    if(line.startsWith(listaUtenti.get(riga))) // trovata linea dell'utente e creiamo linea nuova
                    {
                        String[] arr= line.split("☼");
                        arr2 = arr[1].split("§");
                        modificata=String.format("%s☼", listaUtenti.get(riga));
                        for( int i =0; i< arr2.length; i++)
                        {
                            String[] arr3 = arr2[i].split("¶");
                            if(listaFarmaci.get(riga).compareTo(arr3[0])==0)
                            {
                                modificata += String.format("%s¶%s§",arr3[0],"approvata");
                            }
                            else
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
            JOptionPane.showMessageDialog(null, "Ricetta approvata!");
            listaStati.set(riga,"approvata");
        }
        if (e.getSource() == BtnRifiuta)
        {
            try {
                riga=Table.getSelectedRow();
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

                    if(line.startsWith(listaUtenti.get(riga))) // trovata linea dell'utente e creiamo linea nuova
                    {
                        String[] arr= line.split("☼");
                        arr2 = arr[1].split("§");
                        modificata=String.format("%s☼", listaUtenti.get(riga));
                        for( int i =0; i< arr2.length; i++)
                        {
                            String[] arr3 = arr2[i].split("¶");
                            if(listaFarmaci.get(riga).compareTo(arr3[0])==0)
                            {
                                modificata += String.format("%s¶%s§",arr3[0],"rifiutata");
                            }
                            else
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
            listaStati.set(riga,"rifiutata");
            JOptionPane.showMessageDialog(null, "Ricetta rifiutata!");
        }
        if (e.getSource() == BtnEliminaR)
        {
            try {
                riga=Table.getSelectedRow();
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

                    if(line.startsWith(listaUtenti.get(riga))) // trovata linea dell'utente e creiamo linea nuova
                    {
                        String[] arr= line.split("☼");
                        arr2 = arr[1].split("§");
                        modificata=String.format("%s☼", listaUtenti.get(riga));
                        for( int i =0; i< arr2.length; i++)
                        {
                            String[] arr3 = arr2[i].split("¶");
                            if(listaFarmaci.get(riga).compareTo(arr3[0])==0)
                            {

                            }
                            else
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
            File fIm= new File(String.format("Esame/pic/Ricette/%s_%s.jpg", listaUtenti.get(riga), listaFarmaci.get(riga)));
            fIm.delete();

            listaUtenti.remove(riga);
            listaFarmaci.remove(riga);
            listaStati.remove(riga);
            ricette = new Object[listaUtenti.size()][2];
            for (int i = 0; i< listaUtenti.size(); i++)
            {
                ricette[i][0]= listaUtenti.get(i);
                ricette[i][1]= listaFarmaci.get(i);
            }
            scrol.remove(Table);
            this.remove(scrol);
            creaTabella();

            JOptionPane.showMessageDialog(null, "Ricetta eliminata!");
        }
        if (e.getSource() == BtnIngrandisci)
        {
            int riga=Table.getSelectedRow();
            new ingrandisci(listaUtenti.get(riga), listaFarmaci.get(riga));

        }
        if (e.getSource() == BtnBack)
        {
            dispose();
            new HomeFarmacista(ug);
        }
    }
    public void creaTabella()
    {
        Table = new JTable(ricette,colName){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Table.setFont(new Font("Arial", Font.PLAIN, 15));
        Table.setSize(390, 510);
        Table.setFillsViewportHeight(true);
        scrol=new JScrollPane(Table);
        scrol.setLocation(5, 5);
        scrol.setSize(390,510);
        this.add(scrol);
        Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                riga=Table.getSelectedRow();
                stat.setText(String.format("Stato: %s",listaStati.get(riga)));
                ImageIcon icon = new ImageIcon(String.format("Esame/pic/Ricette/%s_%s.jpg", listaUtenti.get(riga), listaFarmaci.get(riga)));
                Image image = icon.getImage();
                Image Nimage = image.getScaledInstance(300,300, Image.SCALE_SMOOTH);
                pic.setIcon(new ImageIcon(Nimage));
            }

        });
    }
}
