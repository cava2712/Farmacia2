package Esame.Farmacisti;

import javax.swing.*;
import java.awt.*;

import Esame.Classi.Utente;
import Esame.Clienti.HomeCliente;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class ControllaRicette extends JFrame implements ActionListener {
    String[] colName= new String[] {"Cliente" , "Farmaco" };
    private  JLabel pic;
    private final JButton BtnBack;
    private final JButton BtnApprova;
    private final JButton BtnRifiuta;
    private final JButton BtnIngrandisci;
    private final JButton BtnEliminaR;
    private  JTable Table;
    private  JScrollPane scrol;
    Object[][] ricette;
    String[] arr2;
    String path = null;
    boolean ricettevuoto=true;
    ArrayList<String> cat =null;
    ObjectMapper om = new ObjectMapper();
    Utente ug;
    ArrayList<String> nome = new ArrayList<>();
    ArrayList<String> farm = new ArrayList<>();
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
        String NomeU;
        ricette = new Object[0][2];
        //for per riempire ricette

        try
        {
            File file = new File("Esame/FIle/ricette.txt");    //creates a new file instance
            FileReader fr = new FileReader(file);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            String line;
            br.readLine();
            while((line=br.readLine())!=null)
            {
                    String[] arr= line.split("☼");
                    NomeU=arr[0];
                    arr2 = arr[1].split("§");
                    //ricette = new Object[arr2.length][2];
                    for( int i =0; i< arr2.length; i++)
                    {
                        String[] arr3 = arr2[i].split("¶");
                        nome.add(NomeU);
                        farm.add(arr3[0]);
                        cont++;
                    }
            }
        }
        catch (IOException ee) {
            ee.printStackTrace();
        }
        ricette = new Object[nome.size()][2];
        for (int i =0;i<nome.size();i++)
        {
            ricette[i][0]=nome.get(i);
            ricette[i][1]=farm.get(i);
        }
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
                EventoTabella();
            }

        });

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

                    if(line.startsWith(nome.get(riga))) // trovata linea dell'utente e creiamo linea nuova
                    {
                        String[] arr= line.split("☼");
                        arr2 = arr[1].split("§");
                        modificata=String.format("%s☼",nome.get(riga));
                        for( int i =0; i< arr2.length; i++)
                        {
                            String[] arr3 = arr2[i].split("¶");
                            if(farm.get(riga).compareTo(arr3[0])==0)
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

                    if(line.startsWith(nome.get(riga))) // trovata linea dell'utente e creiamo linea nuova
                    {
                        String[] arr= line.split("☼");
                        arr2 = arr[1].split("§");
                        modificata=String.format("%s☼",nome.get(riga));
                        for( int i =0; i< arr2.length; i++)
                        {
                            String[] arr3 = arr2[i].split("¶");
                            if(farm.get(riga).compareTo(arr3[0])==0)
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

                    if(line.startsWith(nome.get(riga))) // trovata linea dell'utente e creiamo linea nuova
                    {
                        String[] arr= line.split("☼");
                        arr2 = arr[1].split("§");
                        modificata=String.format("%s☼",nome.get(riga));
                        for( int i =0; i< arr2.length; i++)
                        {
                            String[] arr3 = arr2[i].split("¶");
                            if(farm.get(riga).compareTo(arr3[0])==0)
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
            File fIm= new File(String.format("Esame/pic/Ricette/%s_%s.jpg",nome.get(riga),farm.get(riga)));
            fIm.delete();

            nome.remove(riga);
            farm.remove(riga);
            ricette = new Object[nome.size()][2];
            for (int i =0;i<nome.size();i++)
            {
                ricette[i][0]=nome.get(i);
                ricette[i][1]=farm.get(i);
            }
            scrol.remove(Table);
            this.remove(scrol);
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
                    EventoTabella();
                }

            });

            JOptionPane.showMessageDialog(null, "Ricetta eliminata!");
        }
        if (e.getSource() == BtnIngrandisci)
        {
            int riga=Table.getSelectedRow();
            new ingrandisci(nome.get(riga),farm.get(riga));

        }
        if (e.getSource() == BtnBack)
        {
            dispose();
            new HomeFarmacista(ug);
        }
    }
    public void EventoTabella()
    {
                riga=Table.getSelectedRow();
                ImageIcon icon = new ImageIcon(String.format("Esame/pic/Ricette/%s_%s.jpg",nome.get(riga),farm.get(riga)));
                Image image = icon.getImage();
                Image Nimage = image.getScaledInstance(300,300, Image.SCALE_SMOOTH);
                pic.setIcon(new ImageIcon(Nimage));
                return;
    }
}
