package Esame;

import java.util.ArrayList;

enum Types {
    cliente,
    farmacista,
    amministratore
}
public class Utente {
    private Types t;
    private String nome,cognome,password,email,CF,img;
    public ArrayList<Farmaco> carrello;

    public Utente(Types t, String nome, String cognome, String password, String email, String CF, String img) {
        this.t = t;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.email = email;
        this.CF = CF;
        this.img = img;
        carrello=new ArrayList<Farmaco>();
    }

    public Utente()
    {}
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    public Utente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
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

}
