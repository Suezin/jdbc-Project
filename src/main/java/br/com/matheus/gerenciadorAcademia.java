package br.com.matheus;

import Controller.ControllerAluno;
import Model.Aluno;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class gerenciadorAcademia {

    public static void main(String[] args){
        ControllerAluno conn = new ControllerAluno();
//1
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

        Scanner sc = new Scanner(System.in);
        int i = 0 ;
        while(i>=0 && i<=3) {
            System.out.println("================================");
            System.out.println("             Opções             ");
            System.out.println(" 1. Alunos");
            System.out.println(" 2. Professores");
            System.out.println(" 3. Treinos ");
            System.out.println(" 0. Sair");
            System.out.println("Informe a sua opção : ");
            i = sc.nextInt();
            if (i == 1) {
                int j = 0;
                while (j >= 0 && j <= 4) {
                    System.out.println("================================");
                    System.out.println("             Opções             ");
                    System.out.println(" 1. Mostrar todos os Alunos");
                    System.out.println(" 2. Adicionar novos Alunos");
                    System.out.println(" 3. Alterar Alunos");
                    System.out.println(" 4. Pesquisar Alunos");
                    System.out.println(" 5. Sair");
                    System.out.println(" Informe a sua opção :");
                    j = sc.nextInt();
                }
                if (j == 1){
                    List<Aluno> alunoList = new ArrayList<>();
                    alunoList = conn.getAluno();
                    for(Aluno aluno : alunoList){
                    }
                }
            }
        }
    }
}
