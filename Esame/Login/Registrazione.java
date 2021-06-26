package Esame.Login;

import Esame.Classi.DateLabelFormatter;
import Esame.Clienti.HomeCliente;
import Esame.Classi.Types;
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
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

public class Registrazione extends JFrame implements ActionListener {
    private final JTextField TextNome;
    private final JPasswordField TextPass;
    private final JTextField TextCognome;
    private final JTextField TextEmail;
    private final JTextField TextCF;
    private final JButton BtnCrea;
    private final JButton BtnImg;
    private final JLabel Img;
    private final JLabel Ln;
    private final JLabel Lp;
    private final JLabel Lc;
    private final JLabel Le;
    private final JLabel Lcf;
    private final JLabel Ld;
    private final JDatePickerImpl Dat;
    private final JCheckBox CBO;
    String path = null;

    public Registrazione() {
        super("Registrazione");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 600);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);

        Ln=new JLabel("Nome");
        Ln.setFont(new Font("Arial", Font.PLAIN, 30));
        Ln.setSize(200, 30);
        Ln.setLocation(10, 5);
        this.add(Ln);
        TextNome = new JTextField();
        TextNome.setFont(new Font("Arial", Font.PLAIN, 30));
        TextNome.setSize(350, 30);
        TextNome.setLocation(210, 5);
        this.add(TextNome);

        Lc=new JLabel("Cognome");
        Lc.setFont(new Font("Arial", Font.PLAIN, 30));
        Lc.setSize(200, 30);
        Lc.setLocation(10, 65);
        this.add(Lc);
        TextCognome = new JTextField();
        TextCognome.setFont(new Font("Arial", Font.PLAIN, 30));
        TextCognome.setSize(350, 30);
        TextCognome.setLocation(210, 65);
        this.add(TextCognome);

        Ld = new JLabel("Data di nascita");
        Ld.setFont(new Font("Arial", Font.PLAIN, 30));
        Ld.setLocation(10, 110);
        Ld.setSize(200,40);
        this.add(Ld);
        UtilDateModel DateMod = new UtilDateModel();
        DateMod.setDate(LocalDateTime.now().getYear(),LocalDateTime.now().getMonthValue(),LocalDateTime.now().getDayOfMonth());
        Properties p=new Properties();
        p.put("text.today","Today");
        p.put("text.month","Month");
        p.put("text.year","Year");
        Dat =  new JDatePickerImpl(new JDatePanelImpl(DateMod,p),new DateLabelFormatter());
        Dat.setLocation(210, 115);
        Dat.setSize(170,40);
        this.add(Dat);

        Lp=new JLabel("Password");
        Lp.setFont(new Font("Arial", Font.PLAIN, 30));
        Lp.setSize(200, 30);
        Lp.setLocation(10, 170);
        this.add(Lp);
        TextPass = new JPasswordField();
        TextPass.setFont(new Font("Arial", Font.PLAIN, 30));
        TextPass.setSize(350, 30);
        TextPass.setLocation(210, 170);
        this.add(TextPass);

        Le=new JLabel("Email");
        Le.setFont(new Font("Arial", Font.PLAIN, 30));
        Le.setSize(200, 30);
        Le.setLocation(10, 240);
        this.add(Le);
        TextEmail = new JTextField();
        TextEmail.setFont(new Font("Arial", Font.PLAIN, 30));
        TextEmail.setSize(350, 30);
        TextEmail.setLocation(210, 240);
        this.add(TextEmail);

        Lcf=new JLabel("CF");
        Lcf.setFont(new Font("Arial", Font.PLAIN, 30));
        Lcf.setSize(200, 30);
        Lcf.setLocation(10, 310);
        this.add(Lcf);
        TextCF = new JTextField();
        TextCF.setFont(new Font("Arial", Font.PLAIN, 30));
        TextCF.setSize(350, 30);
        TextCF.setLocation(210, 310);
        this.add(TextCF);

        CBO=new JCheckBox("Scegli immagine di default");
        CBO.setFont(new Font("Arial", Font.PLAIN, 30));
        CBO.setSize(500, 45);
        CBO.setLocation(10, 380);
        this.add(CBO);

        BtnImg=new JButton("Scegli immagine");
        BtnImg.setFont(new Font("Arial", Font.PLAIN, 30));
        BtnImg.setSize(300, 45);
        BtnImg.setLocation(10, 440);
        this.add(BtnImg);
        Img = new JLabel();
        Img.setFont(new Font("Arial", Font.PLAIN, 15));
        Img.setSize(250, 30);
        Img.setLocation(325, 440);
        this.add(Img);

        BtnCrea = new JButton("Crea account");
        BtnCrea.setFont(new Font("Arial", Font.PLAIN, 30));
        BtnCrea.setSize(600, 45);
        BtnCrea.setLocation(0, 510);
        this.add(BtnCrea);
        BtnCrea.addActionListener(this);
        BtnImg.addActionListener(this);
        CBO.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if(CBO.isSelected())
                {
                    Img.setText("default");
                    Img.setVisible(false);
                    BtnImg.setEnabled(false);
                }
                else
                {
                    BtnImg.setEnabled(true);
                    Img.setText("");
                    Img.setVisible(false);
                }
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BtnCrea) {
            if(TextCognome.getText()=="" || TextNome.getText()=="" || TextEmail.getText()=="" || TextCF.getText()=="" || String.valueOf(TextPass.getPassword())=="" || Img.getText()=="")
            {
                JOptionPane.showMessageDialog(null, "devi inserire tutti i campi");
                return;
            }
                Date data = (Date)Dat.getModel().getValue();
                //qua prendiamo i dati e poi facciamo la query
                String url = "http://localhost:8080/crea";
                String Pimg;
                if(CBO.isSelected())
                {
                    Pimg= "default.png";
                }
                else
                {
                    Pimg= String.format("%s.png", TextEmail.getText());
                }
                String response = Unirest.post(url)
                        .field("types", "cliente")
                        .field("email", TextEmail.getText())
                        .field("password", String.valueOf(TextPass.getPassword()))
                        .field("cf", TextCF.getText())
                        .field("cognome", TextCognome.getText())
                        .field("nome", TextNome.getText())
                        .field("img",Pimg)
                        .field("dataDiNascita",String.format("%d-%d-%d",Dat.getModel().getYear(),Dat.getModel().getMonth(),Dat.getModel().getDay()))
                        .asString().getBody();
                //se va a buon fine creiamo l'utente e lo passiamo come parametro alla finestra successiva
                if (response.equals("doppio")) {
                    JOptionPane.showMessageDialog(null, "Esiste già un account con quest'email");
                    return;
                }
                InputStream is = null;
                OutputStream os = null;
                if(!CBO.isSelected()) {
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

            Utente u = new Utente(Types.cliente, TextNome.getText(), String.valueOf(TextPass.getPassword()), TextCognome.getText(), TextEmail.getText(), TextCF.getText(), Pimg,data);

                dispose();
                new HomeCliente(u);
        }
        if (e.getSource() == BtnImg) {
            JFileChooser open = new JFileChooser();
            int option = open.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                path = open.getSelectedFile().getAbsolutePath();

                Img.setText(path);

            }
        }
        if (e.getSource() == CBO) {
            JOptionPane.showMessageDialog(null, "devi inserire tutti i campi");
            return;
        }
    }

    public static void main(String[] args) {
        new Registrazione();
    }

}
