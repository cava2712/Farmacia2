package Esame;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import kong.unirest.Unirest;
import spark.utils.StringUtils;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class Carrello extends JFrame implements ActionListener {
    String[] colName= new String[] { "Nome","Categoria" ,"Quantità" };

    private  JTable Table;
    private final JLabel Lc;
    private final JLabel Ln;
    private final JLabel Lm;
    private final JLabel Lca;
    private final JLabel Lp;
    private final JLabel Lq;
    private final JLabel pic;
    private final JButton Elimina;
    private final JButton Pagamento;
    private final JButton Back;
    private  JScrollPane scrol;
    private JFrame f;
    int cont=0;
    Object[][] farmaci;
    Utente ug;

    public Carrello(Utente u) throws Exception {
        super("Carrello");
        f=this;
        ug=u;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);

        for (Farmaco f: u.carrello)
        {
            cont++;
        }
        farmaci = new Object[cont][3];
        for(int i=0;i<cont;i++)
        {
            farmaci[i][0]= u.carrello.get(i).getNome();
            farmaci[i][1]= u.carrello.get(i).getCategoria();
            farmaci[i][2]= u.carrello.get(i).getQuantità();
        }
        Table = new JTable(farmaci,colName){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            } //per non modificare le righe/colonne
        };


        pic= new JLabel();
        this.add(pic);

        Lc= new JLabel();
        Lc.setFont(new Font("Arial", Font.PLAIN, 15));
        Lc.setSize(400, 30);
        Lc.setLocation(290, 170);
        this.add(Lc);


        Ln= new JLabel();
        Ln.setFont(new Font("Arial", Font.PLAIN, 15));
        Ln.setSize(400, 30);
        Ln.setLocation(290, 190);
        this.add(Ln);


        Lm= new JLabel();
        Lm.setFont(new Font("Arial", Font.PLAIN, 15));
        Lm.setSize(400, 30);
        Lm.setLocation(290, 210);
        this.add(Lm);


        Lca= new JLabel();
        Lca.setFont(new Font("Arial", Font.PLAIN, 15));
        Lca.setSize(400, 30);
        Lca.setLocation(290, 230);
        this.add(Lca);


        Lp= new JLabel();
        Lp.setFont(new Font("Arial", Font.PLAIN, 15));
        Lp.setSize(400, 30);
        Lp.setLocation(290, 250);
        this.add(Lp);


        Lq= new JLabel();
        Lq.setFont(new Font("Arial", Font.PLAIN, 15));
        Lq.setSize(400, 30);
        Lq.setLocation(290, 270);
        this.add(Lq);


        Table.setFont(new Font("Arial", Font.PLAIN, 15));
        Table.setSize(250, 370);
        Table.setFillsViewportHeight(true);
        scrol=new JScrollPane(Table);
        scrol.setLocation(5, 20);
        scrol.setSize(250,370);
        this.add(scrol);
        Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int riga=Table.getSelectedRow();
                Lc.setText(String.format("codice: %d",u.carrello.get(riga).getCodice()));
                Ln.setText("nome: "+u.carrello.get(riga).getNome());
                Lm.setText("marca: "+u.carrello.get(riga).getMarca());
                Lca.setText("categoria: "+u.carrello.get(riga).getCategoria());
                Lp.setText(String.format("prezzo: %f",u.carrello.get(riga).getPrezzo()));
                Lq.setText(String.format("quantità: %d",u.carrello.get(riga).getQuantità()));

                String a = ug.carrello.get(riga).getPercorsoImg();
                if(a=="")
                    a="default.png";
                ImageIcon icon = new ImageIcon(String.format("Esame/pic/Farmaci/%s",a));
                Image image = icon.getImage();
                Image Nimage = image.getScaledInstance(225,145, Image.SCALE_SMOOTH);
                pic.setIcon(new ImageIcon(Nimage));
                pic.setSize(225, 145);
                pic.setLocation(270, 20);
                f.add(pic);
            }
        });

        Elimina= new JButton("Elimina");
        Elimina.setFont(new Font("Arial", Font.PLAIN, 15));
        Elimina.setSize(125, 40);
        Elimina.setLocation(5, 400);
        this.add(Elimina);

        Pagamento= new JButton("Pagamento");
        Pagamento.setFont(new Font("Arial", Font.PLAIN, 15));
        Pagamento.setSize(125, 40);
        Pagamento.setLocation(130, 400);
        this.add(Pagamento);

        Back= new JButton("Back");
        Back.setFont(new Font("Arial", Font.PLAIN, 15));
        Back.setSize(125, 40);
        Back.setLocation(320, 400);
        this.add(Back);


        Elimina.addActionListener(this);
        Pagamento.addActionListener(this);
        Back.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Pagamento) {
            String url = "http://localhost:8080/pagamento";
            String json = Unirest.get(url).asString().getBody();
        }
        if (e.getSource() == Elimina) {
            int riga = Table.getSelectedRow();
            if (riga == -1) {
                JOptionPane.showMessageDialog(null, "nessuna riga selezionata!");
                return;
            }
            ug.carrello.remove(riga);

            this.remove(scrol);
            scrol.remove(Table);
            int c = 0;
            ArrayList<Farmaco> lisa = new ArrayList<Farmaco>();
            for (Farmaco f : ug.carrello) {
                lisa.add(f);
                c++;
            }
            farmaci = new Object[c][3];
            for (int i = 0; i < c; i++) {
                farmaci[i][0] = lisa.get(i).getCodice();
                farmaci[i][1] = lisa.get(i).getNome();
                farmaci[i][2] = lisa.get(i).getPrezzo();
            }
            Table = new JTable(farmaci, colName) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            Table.setFont(new Font("Arial", Font.PLAIN, 15));
            Table.setSize(250, 370);
            Table.setFillsViewportHeight(true);
            scrol = new JScrollPane(Table);
            scrol.setLocation(5, 20);
            scrol.setSize(250, 370);
            this.add(scrol);
            Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int riga = Table.getSelectedRow();
                    Lc.setText(String.format("codice: %d", ug.carrello.get(riga).getCodice()));
                    Ln.setText("nome: " + ug.carrello.get(riga).getNome());
                    Lm.setText("marca: " + ug.carrello.get(riga).getMarca());
                    Lca.setText("categoria: " + ug.carrello.get(riga).getCategoria());
                    Lp.setText(String.format("prezzo: %f", ug.carrello.get(riga).getPrezzo()));
                    Lq.setText(String.format("quantità: %d", ug.carrello.get(riga).getQuantità()));


                    String a = ug.carrello.get(riga).getPercorsoImg();
                    if (a == "")
                        a = "default.png";
                    ImageIcon icon = new ImageIcon(String.format("Esame/pic/Farmaci/%s", a));
                    Image image = icon.getImage();
                    Image Nimage = image.getScaledInstance(225, 145, Image.SCALE_SMOOTH);
                    pic.setIcon(new ImageIcon(Nimage));
                    pic.setSize(225, 145);
                    pic.setLocation(270, 20);
                    f.add(pic);
                }
            });
            Lc.setText("");
            Ln.setText("");
            Lm.setText("");
            Lca.setText("");
            Lp.setText("");
            Lq.setText("");
            pic.setText("");
        }
        if (e.getSource() == Back) {
            this.dispose();
            new HomeCliente(ug);
        }
        if (e.getSource() == Pagamento) {
            for (Farmaco f : ug.carrello) {
                String url = "http://localhost:8080/pagamento";
                String response = Unirest.post(url)
                        .field("codice", String.format("%s", f.getCodice()))
                        .field("quantità", String.format("%s", f.getQuantità()))
                        .asString().getBody();
            }
            JOptionPane.showMessageDialog(null, "Pagamento andato a buon fine");

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
                boolean trovata=false;
                ArrayList<Farmaco> fiol = new ArrayList<Farmaco>();
                while((line=br.readLine())!=null)
                {
                    if(line.startsWith(ug.getEmail())) // trovata linea dell'utente e creiamo linea nuova
                    {
                        trovata=true;
                        modificata=String.format("%s☼",ug.getEmail());
                        String[] arr= line.split("☼");
                        String[] arr2 = arr[1].split("§");

                        for( int i =0; i< arr2.length; i++)
                        {
                            String[] arr3 = arr2[i].split("¶");
                            for(Farmaco f : ug.carrello)
                            {
                                if(arr3[0].equals(f.getNome()))
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


            ug.carrello.clear();
            dispose();
            new HomeCliente(ug);


        }
    }

}
