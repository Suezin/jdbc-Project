package Model;

import java.io.Reader;
import java.util.Date;

public class Aluno {

    // Atributos
    private int id;
    private String nome;
    private Date dataNasc;
    private String cpf;
    private int idPlano;
    private Date proximoPagamento;

    // Construtores
    public Aluno(int id, String nome, Date dataNasc, String cpf, int tipoDePlano, Date proximoPagamento) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.idPlano = idPlano;
        this.proximoPagamento = proximoPagamento;
    }


    public Aluno() {
    }

    public int getId(){
        return id;
    }
    // Getters
    public String getNome() {
        return nome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public Date getProximoPagamento() {
        return proximoPagamento;
    }

    // Setters
    public void setId(int id){
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setCpf(String cpf) {
        this.cpf =cpf;
    }

    public void setIdPlano(int tipoDePlano) {
        this.idPlano = idPlano;
    }

    public void setProximoPagamento(Date proximoPagamento) {
        this.proximoPagamento = proximoPagamento;
    }
}
