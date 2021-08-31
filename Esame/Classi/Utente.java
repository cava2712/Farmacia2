package Esame.Classi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 * <p>Questa classe rapperesenta l'oggetto "Utente"</p>
 * <p>Questa classe è caratterizzata dall'utilizzo dell'enumeratore "Types" per descrivere che tipo di Utente sta utilizzando l'applicazione </p>
 *
 * @author Luca Barbieri, Davide Cavazzuti
 **/
public class Utente {
    /**
     *Enumeratore utilizzato per indicare il ruolo di un utente (non modificabile)
      */
    private Types t;
    /**
     * Indica il nome dell'utente
     */
    private String nome;
    /**
     * Indica il cognome dell'utente
     */
    private String cognome;
    /**
     * Indica la password dell'utente
     */
    private String password;
    /**
     * Indica la mail dell'utente
     */
    private String email;
    /**
     * Indica il codice fiscale dell'utente
     */
    private String CF;
    /**
     * Indica il nome del file .jpg dell'immagine del profilo dell'utente
     */
    private String img;
    /**
     * Lista contenente le attuali medicine nel carrello (solo per il ruolo "cliente")
     */
    public ArrayList<Farmaco> carrello;
    /**
     * indica la data di nascita dell'utente
     */
    public Date dataDiNascita;
    /**
     * istanza dell'oggetto "Calendar" necessario per gestire la data
     */
    private Calendar c;
    public Utente ()
    {}
    /**
     * Costruttore con tutti i parametri in input
     *
     * @param t il ruolo dell'utente
     * @param nome il nome dell'utente
     * @param cognome il cognome del farmaco
     * @param password la password dell'utente
     * @param email l'email dell'utente
     * @param CF il codice fiscale dell'utente
     * @param img il percorso dell'immagine dell'utente
     * @param dataDiNascita la data di nascita dell'utente
     * Infine istanzio la lista per il carrello.
     */
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
    /**
     * Ritorna la data di nascita dell'utente
     *
     * @return la data di nascita
     */
    public Date getDataDiNascita() {
        return dataDiNascita;
    }
    /**
     * Setta la data di nascita dell'utente
     *
     * @param dataDiNascita dell'utente
     */
    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
    /**
     * Ritorna il percorso dell'immagine dell'utente
     *
     * @return il percorso dell'immagine
     */
    public String getImg() {
        return img;
    }
    /**
     * Setta il percorso dell'immagine dell'utente
     *
     * @param img del profilo dell'utente
     */
    public void setImg(String img) {
        this.img = img;
    }
    /**
     * Ritorna il ruolo dell'utente
     *
     * @return il ruolo
     */
    public Types getT() {
        return t;
    }
    /**
     * Ritorna il nome dell'utente
     *
     * @return il nome
     */
    public String getNome() {
        return nome;
    }
    /**
     * Setta il nome dell'utente
     *
     * @param nome dell'utente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Ritorna il cognome dell'utente
     *
     * @return il cognome
     */
    public String getCognome() {
        return cognome;
    }
    /**
     * Setta il cognome dell'utente
     *
     * @param cognome dell'utente
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    /**
     * Ritorna la password dell'utente
     *
     * @return la password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Setta la password dell'utente
     *
     * @param password dell'utente
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Ritorna l'email dell'utente
     *
     * @return l'email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Setta la email dell'utente
     *
     * @param email dell'utente
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Ritorna il codice fiscale dell'utente
     *
     * @return il codice fiscale
     */
    public String getCF() {
        return CF;
    }
    /**
     * Setta la codice fiscale dell'utente
     *
     * @param CF dell'utente
     */
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

    /**
     * Ritorna la quantità totale di ogni farmaco attualmente nel carrello
     *
     * @return la quantità totale dei farmaci
     */
    public int numCarrello() //numero di elementi nel carrello
    {
        int conta=0;
        for (Farmaco f: this.carrello) {
            conta+=f.getQuantita();
        }
        return conta;
    }

    /**
     * Ritorna l'anno di nascita dell'utente come intero
     *
     * @return anno di nascita
     */
    public int AnnoNascita()
    {
        c = Calendar.getInstance();
        c.setTime(dataDiNascita);
        return c.get(Calendar.YEAR);
    }
    /**
     * Ritorna il mese di nascita dell'utente come intero
     *
     * @return mese di nascita
     */
    public int MeseNascita()
    {
        c = Calendar.getInstance();
        c.setTime(dataDiNascita);
        return c.get(Calendar.MONTH);
    }
    /**
     * Ritorna il giorno di nascita dell'utente come intero
     *
     * @return giorno di nascita
     */
    public int GiornoNascita()
    {
        c = Calendar.getInstance();
        c.setTime(dataDiNascita);
        return c.get(Calendar.DAY_OF_MONTH);
    }
}
