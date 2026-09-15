package br.com.matheus;

import Dao.AlunoDAO;
import Model.Aluno;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class gerenciadorAcademia {

    public static void main(String[] args) {
        AlunoDAO alunoConn = new AlunoDAO();

        Scanner sc = new Scanner(System.in);
        int i = 0;
        while (i >= 0 && i <= 3) {
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
                while (j >= 0 && j <= 5) {
                    System.out.println("================================");
                    System.out.println("             Opções             ");
                    System.out.println(" 1. Mostrar todos os Alunos");
                    System.out.println(" 2. Adicionar novos Alunos");
                    System.out.println(" 3. Alterar Alunos");
                    System.out.println(" 4. Pesquisar Alunos");
                    System.out.println(" 5. Deletar Alunos");
                    System.out.println(" 6. Sair");
                    System.out.println(" Informe a sua opção :");
                    j = sc.nextInt();
                    if (j == 1) {
                        List<Aluno> alunoList = alunoConn.getAluno();
                        for (Aluno aluno : alunoList) {
                            System.out.println("================================");
                            System.out.println("Id : " + aluno.getId());
                            System.out.println("Nome: " + aluno.getNome());
                            System.out.println("Data de Nascimento : " + aluno.getDataNasc());
                            System.out.println("Cpf : " + aluno.getCpf());
                            System.out.println("Tipo de Plano : " + aluno.getIdPlano());
                            System.out.println("Próximo pagamento :" + aluno.getProximoPagamento());
                            System.out.println("Pagamento do Plano : Pago");

                        }
                        j = 0;


                    } else if (j == 2) {
                        String nome = "";
                        int diaNasc = 0;
                        int mesNasc = 0;
                        int anoNasc = 0;
                        String cpf = "";
                        int idplano = 0;
                        LocalDate dataNasc;
                        sc.nextLine();

                        System.out.println("================================");
                        System.out.println("          Inserir Aluno        \n ");
                        System.out.println("Informe o nome: ");
                        nome = sc.nextLine();

                        System.out.println("Informe a data de Nascimento:");
                        System.out.println("Dia : ");
                        diaNasc = sc.nextInt();
                        System.out.println("Mês : ");
                        mesNasc = sc.nextInt();
                        System.out.println("Ano : ");
                        anoNasc = sc.nextInt();
                        dataNasc = LocalDate.of(anoNasc, mesNasc, diaNasc);

                        sc.nextLine();
                        System.out.println("Informe o cpf :");
                        cpf = sc.nextLine();
                        if (cpf.length() != 11) {
                            System.out.println("Numero de cpf Inválido!");
                            System.out.println("Informe o cpf : ");
                            cpf = sc.nextLine();
                        }

                        System.out.println("Informe o id do plano que deseja : ");
                        idplano = sc.nextInt();
                        sc.nextLine();
                       LocalDate pagamento = LocalDate.now();

                        alunoConn.insertAluno(nome, dataNasc, cpf, idplano, pagamento);
                        System.out.println("Aluno Inserido com sucesso !");
                    } else if (j == 3) {
                        System.out.println("================================");
                        System.out.println("          Alterar Aluno         \n");
                        List<Aluno> alunoList = alunoConn.getAluno();
                        for (Aluno aluno : alunoList) {
                            System.out.println("================================");
                            System.out.println("Id : " + aluno.getId());
                            System.out.println("Nome: " + aluno.getNome());
                            System.out.println("Data de Nascimento : " + aluno.getDataNasc());
                            System.out.println("Cpf : " + aluno.getCpf());
                            System.out.println("Tipo de Plano : " + aluno.getIdPlano());
                            System.out.println("Próximo Pagamento : " + aluno.getProximoPagamento());
                        }
                        System.out.println("Informe o id do Aluno que deseja alterar : ");
                        int id = sc.nextInt();
                        if (alunoConn.findId(id)) {
                            sc.nextLine();
                            System.out.println("Informe o nome que você deseja inserir : ");
                            String nome = sc.nextLine();
                            alunoConn.alterAluno(id, nome);
                            System.out.println("Aluno Alterado com sucesso!");
                        } else {
                            System.out.println("Informe um id de aluno válido");
                        }
                    } else if (j == 4) {
                        sc.nextLine();
                        System.out.println("================================");
                        System.out.println("        Pesquisar Alunos      \n");
                        System.out.println("Informe o nome do aluno :");
                        String nome = sc.nextLine();

                        List<Aluno> alunoList = alunoConn.getAlunoByName(nome);
                        ;
                        for (Aluno aluno : alunoList) {
                            System.out.println("Nome :" + aluno.getNome());
                            System.out.println("Data de Nacimento :" + aluno.getDataNasc());
                            System.out.println("Cpf :" + aluno.getCpf());
                            System.out.println("Id do plano:" + aluno.getIdPlano());
                            System.out.println("Próximo pagamento :" + aluno.getProximoPagamento());

                        }
                    } else if (j == 5) {
                        System.out.println("================================");
                        System.out.println("        Deletar Alunos        \n");
                        List<Aluno> alunoList = alunoConn.getAluno();
                        for (Aluno aluno : alunoList) {
                            System.out.println("================================");
                            System.out.println("Id : " + aluno.getId());
                            System.out.println("Nome: " + aluno.getNome());
                            System.out.println("Data de Nascimento : " + aluno.getDataNasc());
                            System.out.println("Cpf : " + aluno.getCpf());
                            System.out.println("Id do plano : " + aluno.getIdPlano());
                            System.out.println("Próximo pagamento : " + aluno.getProximoPagamento());

                        }
                        System.out.println("Informe o id do Aluno que deseja deletar: ");
                        int id = sc.nextInt();
                        if (alunoConn.findId(id)) {
                            alunoConn.deleteById(id);
                            System.out.println("Aluno deletado com sucesso");
                        } else {
                            System.out.println("Informe um número de id válido");
                        }
                    }else if(j == 6){
                        i =0;
                    }
                }
            } else if (i == 0) {
                System.out.println("Volte sempre!");
                i = 4;
            } else if (i == 3) {
                int j = 0;
                while (j >= 0 && j <= 5) {
                    sc.nextLine();
                    System.out.println("================================");
                    System.out.println("        Opções de Treino      \n");
                    System.out.println("1. Listar todos os tipos de treinos");
                    System.out.println("2. Adicionar Treino");
                    System.out.println("3. Excluir Treino");
                    System.out.println("4. Sair ");
                    System.out.println("Informe o número da opção que deseja :");
                    j = sc.nextInt();


                    if (j == 1) {
                    } else if (j == 2) {

                    } else if (j == 3) {

                    }else if(j == 4){
                        i = 0;
                    }
                }

            } else if (i == 2) {
                int j = 0;
                while (j >= 0 && j <= 5) {
                    sc.nextLine();
                    System.out.println("================================");
                    System.out.println("     Opções dos Professores   \n");
                    System.out.println("1. Listar todos os Professores");
                    System.out.println("2. Adicionar novo Professor");
                    System.out.println("3. Alterar Professor");
                    System.out.println("4. Excluir Professor ");
                    System.out.println("5. Sair ");
                    System.out.println("Informe o número da opção que deseja :");
                    j = sc.nextInt();

                    if(j == 1){

                    }else if (j==2){

                    }else if(j==3){

                    }else if(j == 4){

                    }else if(j == 5){
                        i = 0;
                    }
                }
            }
        }
    }
}
