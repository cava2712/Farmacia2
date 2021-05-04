package text;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class provafile {
    public static void main(String[] args){
        char[] a = new char[256];
        try {
            FileReader f = new FileReader("test.txt");
            f.read(a);
        } catch (FileNotFoundException e) {
           throw (new RuntimeException());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
