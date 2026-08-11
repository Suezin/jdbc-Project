package Model;

import java.util.Date;

public class Aluno {

    // Atributos
    private String nome;
    private Date dataNasc;
    private Long cpf;
    private String tipoDePlano;
    private Boolean pagamentoPlano;
    private Integer idTreino;

    // Construtores
    public Aluno(String nome, Date dataNasc, Long cpf, String tipoDePlano, Boolean pagamentoPlano, Integer idTreino) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.cpf = cpf;
        this.tipoDePlano = tipoDePlano;
        this.pagamentoPlano = pagamentoPlano;
        this.idTreino = idTreino;
    }


    public Aluno() {
    }


    // Getters
    public String getNome() {
        return nome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public Long getCpf() {
        return cpf;
    }

    public String getTipoDePlano() {
        return tipoDePlano;
    }

    public Boolean getPagamentoPlano() {
        return pagamentoPlano;
    }

    public Integer getIdTreino() {
        return idTreino;
    }
    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public void setTipoDePlano(String tipoDePlano) {
        this.tipoDePlano = tipoDePlano;
    }

    public void setPagamentoPlano(Boolean pagamentoPlano) {
        this.pagamentoPlano = pagamentoPlano;
    }

    public void setIdTreino(Integer idTreino) {
        this.idTreino = idTreino;
    }
}
