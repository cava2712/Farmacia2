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
    int cont=0;
    Object[][] farmaci;
    Utente ug;

    public Carrello(Utente u) throws Exception {
        super("Pagamento");
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
        pic.setFont(new Font("Arial", Font.PLAIN, 15));
        pic.setSize(400, 30);
        pic.setLocation(290, 20);
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
                pic.setText("percorso");
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
        if(e.getSource() == Elimina)
        {
            int riga=Table.getSelectedRow();
            if(riga==-1)
            {
                JOptionPane.showMessageDialog(null, "nessuna riga selezionata!");
                return;
            }
            ug.carrello.remove(riga);

            this.remove(scrol);
            scrol.remove(Table);
            int c=0;
            ArrayList<Farmaco> lisa= new ArrayList<Farmaco>();
            for(Farmaco f : ug.carrello)
            {
                    lisa.add(f);
                    c++;
            }
            farmaci = new Object[c][3];
            for(int i=0;i<c;i++)
            {
                farmaci[i][0]= lisa.get(i).getCodice();
                farmaci[i][1]= lisa.get(i).getNome();
                farmaci[i][2]= lisa.get(i).getPrezzo();
            }
            Table = new JTable(farmaci,colName){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

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
                    Lc.setText(String.format("codice: %d",ug.carrello.get(riga).getCodice()));
                    Ln.setText("nome: "+ug.carrello.get(riga).getNome());
                    Lm.setText("marca: "+ug.carrello.get(riga).getMarca());
                    Lca.setText("categoria: "+ug.carrello.get(riga).getCategoria());
                    Lp.setText(String.format("prezzo: %f",ug.carrello.get(riga).getPrezzo()));
                    Lq.setText(String.format("quantità: %d",ug.carrello.get(riga).getQuantità()));
                    pic.setText("percorso");
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
        if(e.getSource() == Back)
        {
            dispose();
            new HomeCliente(ug);
        }
        if(e.getSource() == Pagamento)
        {

        }
    }

}
