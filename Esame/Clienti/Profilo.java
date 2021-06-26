package Esame.Clienti;

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

public class Profilo extends JFrame implements ActionListener {
    private final JTextField TextNome;
    private final JTextField TextPass;
    private final JTextField TextCognome;
    private final JTextField TextEmail;
    private final JTextField TextCF;
    private final JButton BtnBack;
    private final JButton Modifica;
    private final JButton Aggiorna;
    private final JButton ModificaImmagine;
    private final JLabel Mi;
    private final JLabel Ln;
    private final JLabel Lp;
    private final JLabel Lc;
    private final JLabel Le;
    private final JLabel Lcf;
    private  JLabel pic;
    private final JLabel Ld;
    private final JDatePickerImpl Dat;
    private final JCheckBox CBO;
    String emailp=null;
    Utente ug=null;
    String path = null;
    String Pimg = null;
    public Profilo(Utente u) {

        super(String.format("Profilo di %s",u.getNome()));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 700);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);
        ug=u;
        emailp=u.getEmail();
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
                }
                else
                {
                    ModificaImmagine.setEnabled(true);
                    Mi.setText("");
                    Mi.setVisible(true);
                }
            }
        });

        if(ug.getImg()=="")
            ug.setImg("default.png");
        pic= new JLabel();
        ImageIcon icon = new ImageIcon(String.format("Esame/pic/Utenti/%s",ug.getImg()));
        Image image = icon.getImage();
        Image Nimage = image.getScaledInstance(225,225, Image.SCALE_SMOOTH);
        pic.setIcon(new ImageIcon(Nimage));
        pic.setSize(225, 225);
        pic.setLocation(15, 0);
        this.add(pic);

        Ln=new JLabel("Nome:   ");
        Ln.setFont(new Font("Arial", Font.PLAIN, 30));
        Ln.setSize(200, 30);
        Ln.setLocation(10, 240);
        this.add(Ln);
        TextNome = new JTextField(u.getNome());
        TextNome.setFont(new Font("Arial", Font.PLAIN, 30));
        TextNome.setSize(350, 30);
        TextNome.setLocation(210, 240);
        TextNome.setEditable(false);
        this.add(TextNome);

        Lc=new JLabel("Cognome:   ");
        Lc.setFont(new Font("Arial", Font.PLAIN, 30));
        Lc.setSize(200, 30);
        Lc.setLocation(10, 290);
        this.add(Lc);
        TextCognome = new JTextField(u.getCognome());
        TextCognome.setFont(new Font("Arial", Font.PLAIN, 30));
        TextCognome.setSize(350, 30);
        TextCognome.setLocation(210, 290);
        TextCognome.setEditable(false);
        this.add(TextCognome);

        Ld = new JLabel("Data di nascita");
        Ld.setFont(new Font("Arial", Font.PLAIN, 30));
        Ld.setLocation(10, 345);
        Ld.setSize(200,40);
        this.add(Ld);
        UtilDateModel DateMod = new UtilDateModel();
        DateMod.setDate(ug.AnnoNascita(),ug.MeseNascita(),ug.GiornoNascita());
        DateMod.setSelected(true);
        Properties p=new Properties();
        p.put("text.today","Today");
        p.put("text.month","Month");
        p.put("text.year","Year");
        Dat =  new JDatePickerImpl(new JDatePanelImpl(DateMod,p),new DateLabelFormatter());
        Dat.setLocation(210, 350);
        Dat.setSize(170,40);
        Dat.getComponent(1).setEnabled(false);
        this.add(Dat);

        Lp=new JLabel("Password:   ");
        Lp.setFont(new Font("Arial", Font.PLAIN, 30));
        Lp.setSize(200, 30);
        Lp.setLocation(10, 410);
        this.add(Lp);
        TextPass = new JTextField(u.getPassword());
        TextPass.setFont(new Font("Arial", Font.PLAIN, 30));
        TextPass.setSize(350, 30);
        TextPass.setLocation(210, 410);
        TextPass.setEditable(false);
        this.add(TextPass);

        Le=new JLabel("Email:   ");
        Le.setFont(new Font("Arial", Font.PLAIN, 30));
        Le.setSize(200, 30);
        Le.setLocation(10, 480);
        this.add(Le);
        TextEmail = new JTextField(u.getEmail());
        TextEmail.setFont(new Font("Arial", Font.PLAIN, 30));
        TextEmail.setSize(350, 30);
        TextEmail.setLocation(210, 480);
        TextEmail.setEditable(false);
        this.add(TextEmail);

        Lcf=new JLabel("CF:   ");
        Lcf.setFont(new Font("Arial", Font.PLAIN, 30));
        Lcf.setSize(200, 30);
        Lcf.setLocation(10, 550);
        this.add(Lcf);
        TextCF = new JTextField(u.getCF());
        TextCF.setFont(new Font("Arial", Font.PLAIN, 30));
        TextCF.setSize(350, 30);
        TextCF.setLocation(210, 550);
        TextCF.setEditable(false);
        this.add(TextCF);

        BtnBack = new JButton("Back");
        BtnBack.setFont(new Font("Arial", Font.PLAIN, 25));
        BtnBack.setSize(150, 60);
        BtnBack.setLocation(50, 600);
        this.add(BtnBack);

        Modifica = new JButton("Modifica");
        Modifica.setFont(new Font("Arial", Font.PLAIN, 25));
        Modifica.setSize(150, 60);
        Modifica.setLocation(250, 600);
        this.add(Modifica);

        Aggiorna = new JButton("Aggiorna");
        Aggiorna.setFont(new Font("Arial", Font.PLAIN, 25));
        Aggiorna.setSize(150, 60);
        Aggiorna.setLocation(450, 600);
        Aggiorna.setVisible(false);
        this.add(Aggiorna);

        ModificaImmagine.setEnabled(false);
        CBO.setEnabled(false);

        BtnBack.addActionListener(this);
        Modifica.addActionListener(this);
        Aggiorna.addActionListener(this);
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
            }
        }
        if (e.getSource() == BtnBack) {

            dispose();
            new HomeCliente(ug);
        }
        if (e.getSource() == Modifica) {
            Aggiorna.setVisible(true);
            TextCF.setEditable(true);
            TextNome.setEditable(true);
            TextCognome.setEditable(true);
            TextEmail.setEditable(true);
            TextPass.setEditable(true);
            Dat.getComponent(1).setEnabled(true);
            ModificaImmagine.setEnabled(true);
            CBO.setEnabled(true);
            if(ug.getImg().equals("default.png"))
                CBO.setSelected(true);

        }
        if (e.getSource() == Aggiorna) {
            //qua prendiamo i dati e poi facciamo la query
            String url = "http://localhost:8080/aggiorna";
            if(CBO.isSelected())
            {
                Pimg= "default.png";
            }
            else
            {
                Pimg= String.format("%s.png", TextEmail.getText());
            }
            ug.setImg(Pimg);
            String response = Unirest.post(url)
                    .field("types", "cliente")
                    .field("emailp",emailp)
                    .field("email", TextEmail.getText())
                    .field("password", String.valueOf(TextPass.getText()))
                    .field("cf", TextCF.getText())
                    .field("cognome", TextCognome.getText())
                    .field("nome", TextNome.getText())
                    .field("img",ug.getImg())
                    .field("dataDiNascita",String.format("%d-%d-%d",Dat.getModel().getYear(),Dat.getModel().getMonth()+1,Dat.getModel().getDay()))
                    .asString().getBody();

            ug.setCF(TextCF.getText());
            ug.setNome(TextNome.getText());
            ug.setCognome(TextCognome.getText());
            ug.setEmail(TextEmail.getText());
            ug.setPassword(TextPass.getText());
            Date data = (Date)Dat.getModel().getValue();
            ug.setDataDiNascita(data);
            Aggiorna.setVisible(false);
            TextCF.setEditable(false);
            TextNome.setEditable(false);
            TextCognome.setEditable(false);
            TextEmail.setEditable(false);
            TextPass.setEditable(false);
            emailp=ug.getEmail();
            JOptionPane.showMessageDialog(null, "Dati aggiornati correttamente");

            InputStream is = null;
            OutputStream os = null;
            if(!CBO.isSelected() && path!=null) {
                try {
                    is = new FileInputStream(new File(path));
                    os = new FileOutputStream(new File(String.format("Esame/pic/Utenti/%s.png", TextEmail.getText())));
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
            String a = ug.getImg();
            ImageIcon icon = new ImageIcon(String.format("Esame/pic/Utenti/%s",a));
            Image image = icon.getImage();
            Image Nimage = image.getScaledInstance(225,225, Image.SCALE_SMOOTH);
            pic.setIcon(new ImageIcon(Nimage));
            ModificaImmagine.setEnabled(false);
            CBO.setEnabled(false);
        }
    }


}