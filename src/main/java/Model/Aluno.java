package Model;

import java.io.Reader;
import java.util.Date;

public class Aluno {

    // Atributos
    private int id;
    private String nome;
    private Date dataNasc;
    private String cpf;
    private String tipoDePlano;
    private Date proximoPagamento;

    // Construtores
    public Aluno(int id, String nome, Date dataNasc, String cpf, String tipoDePlano, Date proximoPagamento) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.tipoDePlano = tipoDePlano;
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

    public String getTipoDePlano() {
        return tipoDePlano;
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

    public void setTipoDePlano(String tipoDePlano) {
        this.tipoDePlano = tipoDePlano;
    }

    public void setProximoPagamento(Date proximoPagamento) {
        this.proximoPagamento = proximoPagamento;
    }
}
