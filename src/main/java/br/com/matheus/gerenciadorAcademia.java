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
                    if (j == 1){
                        List<Aluno> alunoList =conn.getAluno();
                        for(Aluno aluno : alunoList){
                            System.out.println("================================");
                            System.out.println("Nome: " + aluno.getNome());
                            System.out.println("Data de Nascimento : " + aluno.getDataNasc());
                            System.out.println("Cpf : " + aluno.getCpf());
                            System.out.println("Tipo de Plano : " + aluno.getTipoDePlano());
                            if (aluno.getPagamentoPlano() == false)
                                System.out.println("Pagamento do Plano: Não pago");
                            System.out.println("Pagamento do Plano : Pago");
                            System.out.println("Treino : " + aluno.getIdTreino());
                            System.out.println("================================");

                        }
                        System.out.println(" 0. Sair");
                        int sair = 0;
                        sc.nextInt();
                        if (sair>= 0) {
                            j = 0;
                        }

                    }else if(j == 2){
                        String nome = "";
                        int diaNasc = 0;
                        int mesNasc = 0;
                        int anoNasc = 0;
                        String cpf = "";
                        String plano = "";
                        LocalDate dataNasc;
                        int idTreino = 0;
                        sc.nextLine();

                        System.out.println("================================");
                        System.out.println("          Inserir Aluno         ");
                        System.out.println("Informe o nome: ");
                        nome = sc.nextLine();

                        System.out.println("Informe a data de Nascimento:");
                        System.out.println("Dia : ");
                        diaNasc = sc.nextInt();
                        System.out.println("Mês : ");
                        mesNasc = sc.nextInt();
                        System.out.println("Ano : ");
                        anoNasc = sc.nextInt();
                        dataNasc = LocalDate.of(anoNasc,mesNasc,diaNasc);

                        sc.nextLine();
                        System.out.println("Informe o cpf :");
                        cpf = sc.nextLine();
                        if (cpf.length()!= 11 ) {
                            System.out.println("Numero de cpf Inválido!");
                            System.out.println("Informe o cpf : ");
                            cpf = sc.nextLine();
                        }

                        System.out.println("Informe o tipo de plano que deseja (Ex: Mensal, trimestral, etc) : ");
                        plano = sc.nextLine();
                        sc.nextLine();
                        Boolean pagamento = true;

                        System.out.println("Informe o tipo de Treino de preferência: ");
                        idTreino = sc.nextInt();

                        conn.insertAluno(nome,dataNasc,cpf,plano,pagamento,idTreino);
                        System.out.println("Aluno Inserido com sucesso !" );
                    }

                }
            }
        }
    }
}
