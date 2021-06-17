package Esame;

public class Farmaco {
    private int codice,quantità;
    private String nome;
    private String marca;
    private String categoria;
    private String percorsoImg;
    private float prezzo;
    public Farmaco(int codice, int quantità, String nome, String marca, String categoria, float prezzo) {
        this.codice = codice;
        this.quantità = quantità;
        this.nome = nome;
        this.marca = marca;
        this.categoria = categoria;
        this.prezzo = prezzo;
    }
    public String getPercorsoImg() {
        return percorsoImg;
    }

    public void setPercorsoImg(String percorsoImg) {
        this.percorsoImg = percorsoImg;
    }
    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public int getQuantità() {
        return quantità;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }


    @Override
    public String toString() {
        return "Farmaco{" +
                "codice=" + codice +
                ", quantità=" + quantità +
                ", nome='" + nome + '\'' +
                ", marca='" + marca + '\'' +
                ", categoria='" + categoria + '\'' +
                ", prezzo=" + prezzo +
                '}';
    }
}
