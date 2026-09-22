package model;

import java.time.LocalDate;
import java.util.Date;

public class Pagamentos {

    int id ;
    int idAluno = 0 ;
    double amount = 0;
    LocalDate dataDePagamento;

    public Pagamentos(int id, int idAluno, double amount, LocalDate dataDePagamento) {
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

    public void setDataDePagamento(LocalDate dataDePagamento) {
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

    public LocalDate getDataDePagamento() {
        return dataDePagamento;
    }
}
