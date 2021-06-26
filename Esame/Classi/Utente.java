package Esame.Classi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Utente {
    private Types t;
    private String nome,cognome,password,email,CF,img;
    public ArrayList<Farmaco> carrello;
    public Date dataDiNascita;
    private Calendar c;
    public Utente ()
    {}

    public Utente(Types t, String nome, String cognome, String password, String email, String CF, String img, Date dataDiNascita) {
        this.t = t;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.email = email;
        this.CF = CF;
        this.img = img;
        this.dataDiNascita = dataDiNascita;
        carrello=new ArrayList<Farmaco>();

    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Types getT() {
        return t;
    }

    public void setT(Types t) {
        this.t = t;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "t=" + t +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", CF='" + CF + '\'' +
                ", img='" + img + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                '}';
    }


    public int numCarrello()
    {
        int conta=0;
        for (Farmaco f: this.carrello) {
            conta+=f.getQuantità();
        }
        return conta;
    }

    public int AnnoNascita()
    {
        c = Calendar.getInstance();
        c.setTime(dataDiNascita);
        return c.get(Calendar.YEAR);
    }
    public int MeseNascita()
    {
        c = Calendar.getInstance();
        c.setTime(dataDiNascita);
        return c.get(Calendar.MONTH);
    }
    public int GiornoNascita()
    {
        c = Calendar.getInstance();
        c.setTime(dataDiNascita);
        return c.get(Calendar.DAY_OF_MONTH);
    }
}
