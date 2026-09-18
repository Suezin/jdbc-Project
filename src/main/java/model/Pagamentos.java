package model;

import java.util.Date;

public class Pagamentos {

    int id ;
    int idAluno = 0 ;
    double amount = 0;
    Date dataDePagamento;

    public Pagamentos(int id, int idAluno, double amount, Date dataDePagamento) {
        this.id = id;
        this.idAluno = idAluno;
        this.amount = amount;
        this.dataDePagamento = dataDePagamento;
    }

    public Pagamentos() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDataDePagamento(Date dataDePagamento) {
        this.dataDePagamento = dataDePagamento;
    }

    public int getId() {
        return id;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDataDePagamento() {
        return dataDePagamento;
    }
}
