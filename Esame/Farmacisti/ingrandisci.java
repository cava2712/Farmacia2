package Esame.Farmacisti;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * <p>Questa è la finestra che compare quando il farmacista vuole visualizzare una ricetta più in grande </p>
 *
 * @author Luca Barbieri, Davide Cavazzuti
 **/
public class ingrandisci extends JFrame implements ActionListener{
    private  JLabel pic;
    public ingrandisci(String nome,String farm)
    {
        super(String.format("Ricetta %s",farm));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(720, 700);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLayout(null);

        pic= new JLabel();
        pic.setSize(720, 700);
        pic.setLocation(0, 0);
        ImageIcon icon = new ImageIcon(String.format("Esame/pic/Ricette/%s_%s.jpg",nome,farm));
        Image image = icon.getImage();
        Image Nimage = image.getScaledInstance(720,700, Image.SCALE_SMOOTH);
        pic.setIcon(new ImageIcon(Nimage));
        this.add(pic);

        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
