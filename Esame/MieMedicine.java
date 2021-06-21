package Esame;
import com.mindfusion.scheduling.Calendar;
import org.eclipse.swt.*;
import javax.swing.*;

public class MieMedicine extends JFrame {
    Calendar c;
    public MieMedicine()
    {
        c= new Calendar();
        this.add(c);

    }

    public static void main(String[] args) {
        new MieMedicine();
    }
}
