package Model;

public class Professor {
    private String id;
    private String nome;
    private Integer dataNasc;
    private Integer cpf;

    public Professor(String nome, String id, Integer cpf, Integer dataNasc) {
        this.nome = nome;
        this.id = id;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
    }

    public Professor(){
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNasc(Integer dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getDataNasc() {
        return dataNasc;
    }

    public Integer getCpf() {
        return cpf;
    }
}
