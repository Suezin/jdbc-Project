package Model;

import java.util.Date;

public class Professor {
    private String id;
    private String nome;
    private Date dataNasc;
    private Long cpf;

    public Professor(String nome, String id, Long cpf, Date dataNasc) {
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

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public Long getCpf() {
        return cpf;
    }
}
