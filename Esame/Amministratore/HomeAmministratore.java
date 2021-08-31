package Esame.Amministratore;

import Esame.Classi.DateLabelFormatter;
import Esame.Classi.Utente;
import Esame.Login.loginInterface;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import kong.unirest.Unirest;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
/**
 * <p>Questa finestra è la home dell'"Amministratore" dove può gestire tutti i farmacisti (modidfica/creazione/eliminazione)</p>
 *
 * @author Luca Barbieri, Davide Cavazzuti
 **/
public class HomeAmministratore extends JFrame implements ActionListener {
    private final JTextField Filtra;
    String[] colName= new String[] { "Email","Name" ,"Cognome" };

    private  JTable Table;
    private final JLabel LabelFiltra;
    private final JLabel Le;//email
    private final JLabel Ln;//nome
    private final JLabel Lc;//cognome
    private final JLabel Lcf;//cf
    private final JLabel Lp;//password
    private final JLabel Ld;
    private final JTextField Texte;
    private final JTextField Textn;
    private final JTextField Textc;
    private final JTextField Textcf;
    private final JTextField Textp;
    private final JDatePickerImpl Dat;
    private  JLabel pic;
    private final JButton BtnModifica;
    private final JButton Back;
    private final JButton Aggiorna;
    private final JButton EliminaFarmaciscta;
    private final JButton AggiungiFarmacista;
    private final JButton ModificaImmagine;
    private final JLabel Mi;
    private final JCheckBox CBO;
    ArrayList<Utente> listaFarmacisti =null;
    private  JScrollPane scrol;
    Object[][] farmacisti;  //matrice per la JTable
    Utente ug;
    JFrame f;
    ObjectMapper om = new ObjectMapper();
    String path = null;

    public HomeAmministratore(Utente u) throws Exception {
        super("Gestisci magazzino");
        ug=u;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(810, 810);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);
        f= this;

        ModificaImmagine=new JButton("Scegli un'altra immagine");
        ModificaImmagine.setFont(new Font("Arial", Font.PLAIN, 15));
        ModificaImmagine.setSize(200, 30);
        ModificaImmagine.setLocation(415, 330);
        this.add(ModificaImmagine);
        Mi = new JLabel(ug.getImg());
        Mi.setFont(new Font("Arial", Font.PLAIN, 15));
        Mi.setSize(200, 30);
        Mi.setLocation(625, 330);
        this.add( Mi);

        CBO=new JCheckBox("Scegli immagine di default");
        CBO.setFont(new Font("Arial", Font.PLAIN, 30));
        CBO.setSize(400, 45);
        CBO.setLocation(415, 360);
        this.add(CBO);
        CBO.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if(CBO.isSelected())
                {
                    Mi.setText("default");
                    Mi.setVisible(false);
                    ModificaImmagine.setEnabled(false);
                }
                else
                {
                    ModificaImmagine.setEnabled(true);
                    Mi.setText("");
                    Mi.setVisible(true);
                }
            }
        });

        pic= new JLabel();
        pic.setSize(225, 225);
        pic.setLocation(415, 120);
        this.add(pic);

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
                om.getTypeFactory().constructCollectionType(ArrayList.class, Utente.class);

        String url = "http://localhost:8080/farmacisti";
        String json = Unirest.get(url).asString().getBody();
        try {
            listaFarmacisti = om.readValue(json, listType);
        } catch (Exception e) {
            throw new Exception(e);
        }
        farmacisti = new Object[listaFarmacisti.size()][3];
        for(int i = 0; i< listaFarmacisti.size(); i++)
        {
            farmacisti[i][0]= listaFarmacisti.get(i).getEmail();
            farmacisti[i][1]= listaFarmacisti.get(i).getNome();
            farmacisti[i][2]= listaFarmacisti.get(i).getCognome();
        }

        Le = new JLabel("Email:");
        Le.setFont(new Font("Arial", Font.PLAIN, 30));
        Le.setSize(150, 30);
        Le.setLocation(415, 420);
        this.add(Le);
        Texte = new JTextField();
        Texte.setFont(new Font("Arial", Font.PLAIN, 30));
        Texte.setSize(200, 30);
        Texte.setLocation(570, 420);
        Texte.setEditable(false);
        this.add(Texte);

        Ln= new JLabel("Nome:");
        Ln.setFont(new Font("Arial", Font.PLAIN, 30));
        Ln.setSize(150, 30);
        Ln.setLocation(415, 470);
        this.add(Ln);
        Textn = new JTextField();
        Textn.setFont(new Font("Arial", Font.PLAIN, 30));
        Textn.setSize(200, 30);
        Textn.setLocation(570, 470);
        Textn.setEditable(false);
        this.add(Textn);

        Lc = new JLabel("Cognome:");
        Lc.setFont(new Font("Arial", Font.PLAIN, 30));
        Lc.setSize(150, 30);
        Lc.setLocation(415, 520);
        this.add(Lc);
        Textc = new JTextField();
        Textc.setFont(new Font("Arial", Font.PLAIN, 30));
        Textc.setSize(200, 30);
        Textc.setLocation(570, 520);
        Textc.setEditable(false);
        this.add(Textc);

        Lcf = new JLabel("CF:");
        Lcf.setFont(new Font("Arial", Font.PLAIN, 30));
        Lcf.setSize(150, 30);
        Lcf.setLocation(415, 570);
        this.add(Lcf);
        Textcf = new JTextField();
        Textcf.setFont(new Font("Arial", Font.PLAIN, 30));
        Textcf.setSize(200, 30);
        Textcf.setLocation(570, 570);
        Textcf.setEditable(false);
        this.add(Textcf);

        Lp= new JLabel("Password:");
        Lp.setFont(new Font("Arial", Font.PLAIN, 30));
        Lp.setSize(150, 30);
        Lp.setLocation(415, 620);
        this.add(Lp);
        Textp = new JTextField();
        Textp.setFont(new Font("Arial", Font.PLAIN, 30));
        Textp.setSize(200, 30);
        Textp.setLocation(570, 620);
        Textp.setEditable(false);
        this.add(Textp);

        Ld = new JLabel("Data di nascita:");
        Ld.setFont(new Font("Arial", Font.PLAIN, 15));
        Ld.setSize(200, 30);
        Ld.setLocation(415, 670);
        this.add(Ld);
        UtilDateModel DateMod = new UtilDateModel();
        DateMod.setDate(ug.AnnoNascita(),ug.MeseNascita(),ug.GiornoNascita());
        DateMod.setSelected(true);
        Properties p=new Properties();
        p.put("text.today","Today");
        p.put("text.month","Month");
        p.put("text.year","Year");
        Dat =  new JDatePickerImpl(new JDatePanelImpl(DateMod,p),new DateLabelFormatter());
        Dat.setLocation(570, 670);
        Dat.setSize(170,40);
        Dat.getComponent(1).setEnabled(false);
        this.add(Dat);

        creazioneTabella();

        Back= new JButton("Back");
        Back.setFont(new Font("Arial", Font.PLAIN, 30));
        Back.setSize(150, 40);
        Back.setLocation(600, 720);
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

        AggiungiFarmacista = new JButton("Aggiungi un nuovo farmacista");
        AggiungiFarmacista.setFont(new Font("Arial", Font.PLAIN, 15));
        AggiungiFarmacista.setSize(300, 40);
        AggiungiFarmacista.setLocation(5, 720);
        this.add(AggiungiFarmacista);

        EliminaFarmaciscta = new JButton("Elimina farmacista");
        EliminaFarmaciscta.setFont(new Font("Arial", Font.PLAIN, 15));
        EliminaFarmaciscta.setSize(200, 40);
        EliminaFarmaciscta.setLocation(305, 720);
        this.add(EliminaFarmaciscta);

        Filtra.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtraNome();
            } //evento richiamato ogni volta che viene inserito un carattere nel textField

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtraNome();
            }//evento richiamato ogni volta che viene cancellato un carattere nel textField

            @Override
            public void changedUpdate(DocumentEvent e) {
                return;
            } //evento richiamato in ogni caso
        });

        CBO.setEnabled(false);
        ModificaImmagine.setEnabled(false);
        BtnModifica.addActionListener(this);
        Back.addActionListener(this);
        Aggiorna.addActionListener(this);
        EliminaFarmaciscta.addActionListener(this);
        AggiungiFarmacista.addActionListener(this);
        ModificaImmagine.addActionListener(this);
        setVisible(true);
    }
    //funzione per filtrare i nomi dei farmacisti nella tabella
    public void filtraNome()
    {
        String t = Filtra.getText();
        ArrayList<Utente> lisa= new ArrayList<Utente>();
        for(Utente f : listaFarmacisti)
        {
            if(f.getNome().toLowerCase().startsWith(t))
                lisa.add(f);
        }
        farmacisti = new Object[lisa.size()][3];
        for(int i=0;i<lisa.size();i++)
        {
            farmacisti[i][0]= lisa.get(i).getEmail();
            farmacisti[i][1]= lisa.get(i).getNome();
            farmacisti[i][2]= lisa.get(i).getCognome();
        }

        scrol.remove(Table);
        this.remove(scrol);
        creazioneTabella();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ModificaImmagine) {
            JFileChooser open = new JFileChooser();
            int option = open.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                path = open.getSelectedFile().getAbsolutePath();
                Mi.setText(path);
            }
        }

        if(e.getSource() == BtnModifica)
        {
            CBO.setEnabled(true);
            ModificaImmagine.setEnabled(true);
            int riga=Table.getSelectedRow();
            if(riga==-1)
            {
                JOptionPane.showMessageDialog(null, "nessuna riga selezionata!");
                return;
            }
            if(listaFarmacisti.get(riga).getImg()!= "")
                CBO.setSelected(true);
            Aggiorna.setVisible(true);
            Textn.setEditable(true);
            Textc.setEditable(true);
            Textcf.setEditable(true);
            Textp.setEditable(true);
            Texte.setEditable(true);
            Dat.getComponent(1).setEnabled(true);
        }

        if (e.getSource() == Aggiorna)
        {
            String Pimg=null;
            int riga=Table.getSelectedRow();
            String sEmail=farmacisti[riga][0].toString();
            Utente sUtente = listaFarmacisti.stream().filter(utente1 -> utente1.getEmail().equals(sEmail)).findFirst().orElse(null);
            //qua prendiamo i dati e poi facciamo la query
            String url = "http://localhost:8080/aggiorna";
            if(CBO.isSelected())
            {
                Pimg= "default.png";
            }
            else
            {
                Pimg= String.format("%s.png", Texte.getText());
            }
            ug.setImg(Pimg);
            String response = Unirest.post(url)
                    .field("types", "farmacista")
                    .field("emailp", sUtente.getEmail())
                    .field("email", Texte.getText())
                    .field("password",Textp.getText())
                    .field("cf", Textcf.getText())
                    .field("cognome", Textc.getText())
                    .field("nome", Textn.getText())
                    .field("img",Pimg)
                    .field("dataDiNascita",String.format("%d-%d-%d",Dat.getModel().getYear(),Dat.getModel().getMonth()+1,Dat.getModel().getDay()))
                    .asString().getBody();

            listaFarmacisti.get(listaFarmacisti.indexOf(sUtente)).setCF(Textcf.getText());
            listaFarmacisti.get(listaFarmacisti.indexOf(sUtente)).setNome(Textn.getText());
            listaFarmacisti.get(listaFarmacisti.indexOf(sUtente)).setCognome(Textc.getText());
            listaFarmacisti.get(listaFarmacisti.indexOf(sUtente)).setEmail(Texte.getText());
            listaFarmacisti.get(listaFarmacisti.indexOf(sUtente)).setPassword(Textp.getText());
            Date data = (Date)Dat.getModel().getValue();
            listaFarmacisti.get(listaFarmacisti.indexOf(sUtente)).setDataDiNascita(data);
            Aggiorna.setVisible(false);
            Textc.setEditable(false);
            Textn.setEditable(false);
            Textc.setEditable(false);
            Texte.setEditable(false);
            Textp.setEditable(false);
            JOptionPane.showMessageDialog(null, "Dati aggiornati correttamente");

            //se la checkbox è spuntata copio l'immagine nella cartella giusta
            InputStream is = null;
            OutputStream os = null;
            if(!CBO.isSelected() && path!=null) {
                try {
                    is = new FileInputStream(new File(path));
                    os = new FileOutputStream(new File(String.format("Esame/pic/Utenti/%s.png", Texte.getText())));
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) > 0) {
                        os.write(buffer, 0, length);
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } finally {
                    try {
                        is.close();
                        os.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
            ImageIcon icon = new ImageIcon(String.format("Esame/pic/Utenti/%s",Pimg));
            Image image = icon.getImage();
            Image Nimage = image.getScaledInstance(300,250, Image.SCALE_SMOOTH);
            pic.setIcon(new ImageIcon(Nimage));
            ModificaImmagine.setEnabled(false);
            CBO.setEnabled(false);
        }

        if (e.getSource() == Back)
        {
            dispose();
            new loginInterface();
        }

        if (e.getSource() == EliminaFarmaciscta) {
            String url = "http://localhost:8080/eliminaFista";
            int riga=Table.getSelectedRow();
            String sEmail=farmacisti[riga][0].toString();
            Utente sUtente = listaFarmacisti.stream().filter(utente1 -> utente1.getEmail().equals(sEmail)).findFirst().orElse(null);
            if(riga==-1)
            {
                JOptionPane.showMessageDialog(null, "Nessuna riga selezionata!");
                return;
            }
            String response = Unirest.post(url)
                    .field("email", sUtente.getEmail())
                    .asString().getBody();
            listaFarmacisti.remove(listaFarmacisti.indexOf(sUtente));
            farmacisti = new Object[listaFarmacisti.size()][3];
            for(int i = 0; i< listaFarmacisti.size(); i++)
            {
                farmacisti[i][0]= listaFarmacisti.get(i).getEmail();
                farmacisti[i][1]= listaFarmacisti.get(i).getNome();
                farmacisti[i][2]= listaFarmacisti.get(i).getCognome();
            }
            creazioneTabella();
            JOptionPane.showMessageDialog(null, "Farmacista eliminato correttamente!");
            return;
        }

        if (e.getSource() == AggiungiFarmacista) {
            dispose();
            new NuovoFarmacista(ug);
            return;
        }
    }

    private void creazioneTabella() {
        Table = new JTable(farmacisti,colName){
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
                String sEmail=farmacisti[riga][0].toString();
                Utente sUtente = listaFarmacisti.stream().filter(utente1 -> utente1.getEmail().equals(sEmail)).findFirst().orElse(null);
                Texte.setText(sUtente.getEmail());
                Textn.setText(sUtente.getNome());
                Textc.setText(sUtente.getCognome());
                Textcf.setText(sUtente.getCF());
                Textp.setText(sUtente.getPassword());
                Dat.getModel().setDate(sUtente.AnnoNascita(), sUtente.MeseNascita(), sUtente.GiornoNascita());
                Dat.getModel().setSelected(true);

                String a = sUtente.getImg();
                if(a=="")
                    a="default.png";
                ImageIcon icon = new ImageIcon(String.format("Esame/pic/Utenti/%s",a));
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


