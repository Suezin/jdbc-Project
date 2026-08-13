package br.com.matheus;

import Controller.ControllerAluno;
import Model.Aluno;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class gerenciadorAcademia {

    public static void main(String[] args){
        ControllerAluno conn = new ControllerAluno();
        String nome = "A";
        List<Aluno> alunoList = conn.getAlunoById(nome);
        if(!alunoList.isEmpty()){
            for(Aluno aluno : alunoList) {
            System.out.println(aluno.getNome());
            System.out.println(aluno.getCpf());
            System.out.println(aluno.getDataNasc());
            System.out.println(aluno.getTipoDePlano());
            System.out.println(aluno.getPagamentoPlano());
             
        }
        }else {
            System.out.println("Nenhum Aluno encontrado");

        }
//        int id = 1;
//        String name = "Leonardo";
//         conn.alterAluno(id,name);

//        String nome = "Alessandra";
//        LocalDate dateofbirth = LocalDate.of(2008, 4, 10);
//        String cpf = "12345678911";
//        String plain = "Semestral";
//        Boolean payment = true;
//        int trainnigid = 1;
//
//        conn.insertAluno(nome, dateofbirth, cpf,plain,payment,trainnigid );

    }
}
