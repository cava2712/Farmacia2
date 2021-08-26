package Esame.Farmacisti;

import Esame.Classi.DateLabelFormatter;
import Esame.Classi.Utente;
import kong.unirest.Unirest;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.Date;
import java.util.Properties;

public class NuovoFarmaco extends JFrame implements ActionListener {
    private final JLabel Ln;
    private final JLabel Lm;
    private final JLabel Lca;
    private final JLabel Lp;
    private final JLabel Lq;
    private final JTextField Textn;
    private final JTextField Textm;
    private final JTextField Textca;
    private final JTextField Textp;
    private final JTextField Textq;
    private final JButton Aggiungi;
    private final JButton ModificaImmagine;
    private  JLabel pic;
    private final JLabel Mi;
    private final JCheckBox CBO;
    private final JCheckBox CBR;
    String path = null;
    String Pimg = null;
    Utente ug;
    public NuovoFarmaco(Utente u) {

        super("Aggiungi un nuovo farmaco");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 700);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);
        ug=u;

        ModificaImmagine=new JButton("Scegli un'altra immagine");
        ModificaImmagine.setFont(new Font("Arial", Font.PLAIN, 15));
        ModificaImmagine.setSize(200, 30);
        ModificaImmagine.setLocation(250, 50);
        this.add(ModificaImmagine);
        Mi = new JLabel(ug.getImg());
        Mi.setFont(new Font("Arial", Font.PLAIN, 15));
        Mi.setSize(200, 30);
        Mi.setLocation(460, 50);
        this.add( Mi);

        pic= new JLabel();
        ImageIcon icon = new ImageIcon("Esame/pic/Farmaci/default.png");
        Image image = icon.getImage();
        Image Nimage = image.getScaledInstance(225,225, Image.SCALE_SMOOTH);
        pic.setIcon(new ImageIcon(Nimage));
        pic.setSize(225, 225);
        pic.setLocation(15, 0);
        this.add(pic);

        CBO=new JCheckBox("Scegli immagine di default");
        CBO.setFont(new Font("Arial", Font.PLAIN, 30));
        CBO.setSize(400, 45);
        CBO.setLocation(250, 100);
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
                    ImageIcon icon = new ImageIcon("Esame/pic/Farmaci/default.png");
                    Image image = icon.getImage();
                    Image Nimage = image.getScaledInstance(225,225, Image.SCALE_SMOOTH);
                    pic.setIcon(new ImageIcon(Nimage));
                }
                else
                {
                    ModificaImmagine.setEnabled(true);
                    Mi.setText("");
                    Mi.setVisible(true);
                }
            }
        });
        CBO.setSelected(true);



        Ln= new JLabel("Nome:");
        Ln.setFont(new Font("Arial", Font.PLAIN, 30));
        Ln.setSize(150, 30);
        Ln.setLocation(5, 350);
        this.add(Ln);
        Textn = new JTextField();
        Textn.setFont(new Font("Arial", Font.PLAIN, 30));
        Textn.setSize(200, 30);
        Textn.setLocation(170, 350);
        this.add(Textn);

        CBR=new JCheckBox("Necessita ricetta?");
        CBR.setFont(new Font("Arial", Font.PLAIN, 15));
        CBR.setSize(300, 15);
        CBR.setLocation(370, 350);
        this.add(CBR);

        Lm= new JLabel("Marca:");
        Lm.setFont(new Font("Arial", Font.PLAIN, 30));
        Lm.setSize(150, 30);
        Lm.setLocation(5, 400);
        this.add(Lm);
        Textm = new JTextField();
        Textm.setFont(new Font("Arial", Font.PLAIN, 30));
        Textm.setSize(200, 30);
        Textm.setLocation(170, 400);
        this.add(Textm);

        Lca= new JLabel("Categoria:");
        Lca.setFont(new Font("Arial", Font.PLAIN, 30));
        Lca.setSize(150, 30);
        Lca.setLocation(5, 450);
        this.add(Lca);
        Textca = new JTextField();
        Textca.setFont(new Font("Arial", Font.PLAIN, 30));
        Textca.setSize(200, 30);
        Textca.setLocation(170, 450);
        this.add(Textca);

        Lp= new JLabel("Prezzo:");
        Lp.setFont(new Font("Arial", Font.PLAIN, 30));
        Lp.setSize(150, 30);
        Lp.setLocation(5, 500);
        this.add(Lp);
        Textp = new JTextField();
        Textp.setFont(new Font("Arial", Font.PLAIN, 30));
        Textp.setSize(200, 30);
        Textp.setLocation(170, 500);
        this.add(Textp);

        Lq= new JLabel("Quantità:");
        Lq.setFont(new Font("Arial", Font.PLAIN, 30));
        Lq.setSize(150, 30);
        Lq.setLocation(5, 550);
        this.add(Lq);
        Textq = new JTextField();
        Textq.setFont(new Font("Arial", Font.PLAIN, 30));
        Textq.setSize(200, 30);
        Textq.setLocation(170, 550);
        this.add(Textq);

        Aggiungi = new JButton("Aggiungi");
        Aggiungi.setFont(new Font("Arial", Font.PLAIN, 25));
        Aggiungi.setSize(270, 60);
        Aggiungi.setLocation(5, 600);
        this.add(Aggiungi);

        ModificaImmagine.setEnabled(false);
        Aggiungi.addActionListener(this);
        ModificaImmagine.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ModificaImmagine) {

            JFileChooser open = new JFileChooser();
            int option = open.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                path = open.getSelectedFile().getAbsolutePath();
                Mi.setText(path);
                ImageIcon icon = new ImageIcon(path);
                Image image = icon.getImage();
                Image Nimage = image.getScaledInstance(225,225, Image.SCALE_SMOOTH);
                pic.setIcon(new ImageIcon(Nimage));
            }
        }

        if (e.getSource() == Aggiungi) {
            if(Textn.getText().trim()== "" || Textca.getText().trim()== "" ||Textp.getText().trim()== "" ||Textm.getText().trim()== "" ||Textq.getText().trim()== "")
            {
                JOptionPane.showMessageDialog(null, "Devi inserire tutti i campi!");
                return;
            }
            if(Mi.getText() == "")
            {
                JOptionPane.showMessageDialog(null, "Scegli un immagine o utilizza quella di default!");
                return;
            }
            String s= Textq.getText();
            try {
                Integer.parseInt(s);
            } catch(NumberFormatException ee){
                JOptionPane.showMessageDialog(null, "devi inserire un numero nella quantità!");
                Textq.setText("");
                return;
            }
            s= Textp.getText();
            try {
                Float.parseFloat(s.replace(',','.'));
            } catch(NumberFormatException ee){
                JOptionPane.showMessageDialog(null, "devi inserire un numero nel prezzo!");
                Textq.setText("");
                return;
            }
            String ricetta="false";
            if(CBR.isSelected())
                ricetta="true";
            String url = "http://localhost:8080/aggiungiF";
            String p=String.format("%s.png",Textn.getText());
            if(Mi.getText().equals("default"))
                p="";
            String response = Unirest.post(url)
                    .field("nome", Textn.getText())
                    .field("marca", Textm.getText())
                    .field("ricetta", ricetta)
                    .field("categoria", Textca.getText())
                    .field("prezzo",String.format("%.2f",Float.parseFloat(Textp.getText().replace(',','.'))))
                    .field("quantità", Textq.getText())
                    .field("percorsoImg", p)
                    .asString().getBody();
            if(response.equals("doppio"))
            {
                JOptionPane.showMessageDialog(null, "Esiste già un farmaco con questo nome!");
                Textn.setText("");
                Textm.setText("");
                Textca.setText("");
                Textp.setText("");
                Textq.setText("");
                return;
            }

            JOptionPane.showMessageDialog(null, "Farmaco aggiunto con successo");

            InputStream is = null;
            OutputStream os = null;
            if(!CBO.isSelected() && path!=null) {
                try {
                    is = new FileInputStream(new File(path));
                    os = new FileOutputStream(new File(String.format("Esame/pic/Farmaci/%s.png", Textn.getText())));
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
            dispose();
            try {
                new GestisciMag(ug);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    }


}
