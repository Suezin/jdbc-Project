package br.com.matheus;

import Controller.ControllerConnection;
import Controller.ControllerUsusario;
import Model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class gerenciadorAcademia {

    public static void main(String[] args){
        ControllerUsusario conn = new ControllerUsusario();
        List<Aluno> alunoList = conn.getUser();
        for(Aluno aluno : alunoList) {
            System.out.println(aluno.getNome());
            System.out.println(aluno.getCpf());
            System.out.println(aluno.getDataNasc());
            System.out.println(aluno.getTipoDePlano());
            System.out.println(aluno.getPagamentoPlano());

        }
//        int id = 1;
//        String name = "Leonardo";
//         conn.alterUser(id,name);
    }
}
