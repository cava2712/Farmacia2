package Esame.Classi;

/**
 * <p>Questa classe rapperesenta l'oggetto "farmaco"</p>
 *
 * @author Luca Barbieri, Davide Cavazzuti
**/
public class Farmaco {
    /**
     * Indica il codice del farmaco auto-incrementato nel database in modo che sia univoco
     */
    private int codice;
    /**
     * Indica la quantità disponibile del farmaco
     */
    private int quantita;
    /**
     * Indica il nome del farmaco (univoco nel database)
     */
    private String nome;
    /**
     * Indica la marca di produzione del farmaco
     */
    private String marca;
    /**
     * Indica la categoria del farmaco
     */
    private String categoria;
    /**
     * Indica il percorso dell'immagine del farmaco
     */
    private String percorsoImg;
    /**
     * Indica il prezzo del farmaco
     */
    private float prezzo;
    /**
     * Può assumere 2 valori e non può essere modificato:
     * -"true" se il farmaco richiede la ricetta;
     * -"false" se il farmaco non richiede la ricetta.
     *
     */
    private String ricetta;

    public Farmaco()
    {

    }
    /**
     * Costruttore con tutti i parametri in input
     *
     * @param codice il codice del farmaco
     * @param quantita la quantità totale del farmaco
     * @param nome il nome del farmaco
     * @param marca la marca del farmaco
     * @param categoria la categoria del farmaco
     * @param prezzo il prezzo del farmaco
     * @param percorsoImg il percorso del farmaco
     * @param ricetta se il farmaco necessità ricetta
     */
    public Farmaco(int codice, int quantita, String nome, String marca, String categoria, float prezzo, String percorsoImg, String ricetta) {
        this.codice = codice;
        this.quantita = quantita;
        this.nome = nome;
        this.marca = marca;
        this.categoria = categoria;
        this.prezzo = prezzo;
        this.percorsoImg=percorsoImg;
        this.ricetta=ricetta;
    }

    /**
     * Ritorna il percorso dell'immagine
     *
     * @return percorso dell'immagine
     */
    public String getPercorsoImg() {
        return percorsoImg;
    }
    /**
     * Setta il percorso dell'immagine
     *
     * @param percorsoImg del farmaco
     */
    public void setPercorsoImg(String percorsoImg) {
        this.percorsoImg = percorsoImg;
    }
    /**
     * Ritorna il codice del farmaco
     *
     * @return codice del farmaco
     */
    public int getCodice() {
        return codice;
    }
    /**
     * Setta il codice del farmaco
     *
     * @param codice del farmaco
     */
    public void setCodice(int codice) {
        this.codice = codice;
    }
    /**
     * Ritorna la quantità del farmaco
     *
     * @return quantità del farmaco
     */
    public int getQuantita() {
        return quantita;
    }
    /**
     * Setta la quantità del farmaco
     *
     * @param quantita del farmaco
     */
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
    /**
     * Ritorna il nome del farmaco
     *
     * @return nome del farmaco
     */
    public String getNome() {
        return nome;
    }
    /**
     * Setta il nome del farmaco
     *
     * @param nome del farmaco
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Ritorna la marca del farmaco
     *
     * @return marca del farmaco
     */
    public String getMarca() {
        return marca;
    }
    /**
     * Setta la marca del farmaco
     *
     * @param marca del farmaco
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    /**
     * Ritorna la categoria del farmaco
     *
     * @return categoria del farmaco
     */
    public String getCategoria() {
        return categoria;
    }
    /**
     * Setta la categoria del farmaco
     *
     * @param categoria del farmaco
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    /**
     * Ritorna il prezzo del farmaco
     *
     * @return prezzo del farmaco
     */
    public float getPrezzo() {
        return prezzo;
    }
    /**
     * Setta il prezzo del farmaco
     *
     * @param prezzo del farmaco
     */
    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
    /**
     * Ritorna se il seguente farmaco ha bisogno della ricetta ("true"/"false")
     *
     * @return "true"/"false"
     */
    public String getRicetta() {
        return ricetta;
    }

    @Override
    public String toString() {
        return "Farmaco{" +
                "codice=" + codice +
                ", quantità=" + quantita +
                ", nome='" + nome + '\'' +
                ", marca='" + marca + '\'' +
                ", categoria='" + categoria + '\'' +
                ", prezzo=" + prezzo +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getCodice()== ((Farmaco) obj).getCodice())
            return true;
        else
            return false;
    }
}
