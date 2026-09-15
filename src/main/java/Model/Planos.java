package Model;

public class Planos {

    private int id ;
    private String name = "";
    private Double preco = 0.0;
    private int diasDoPlano = 0;

    public Planos(String name, int id, Double preco, int diasDoPlano) {
        this.name = name;
        this.id = id;
        this.preco = preco;
        this.diasDoPlano = diasDoPlano;
    }

    public Planos() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setDiasDoPlano(int diasDoPlano) {
        this.diasDoPlano = diasDoPlano;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPreco() {
        return preco;
    }

    public int getDiasDoPlano() {
        return diasDoPlano;
    }
}
