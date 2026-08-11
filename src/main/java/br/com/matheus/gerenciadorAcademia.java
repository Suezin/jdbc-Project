package br.com.matheus;

import Controller.ControllerConnection;
import Controller.ControllerUsusario;
import Model.Aluno;

import java.util.List;

public class gerenciadorAcademia {

    public static void main(String[] args){
        ControllerUsusario conn = new ControllerUsusario();
        List<Aluno> alunoList = conn.getUser();
        for(Aluno aluno : alunoList){
            System.out.println(aluno.getNome());

        }
    }
}
