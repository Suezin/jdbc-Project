package br.com.matheus;

import Dao.AlunoDAO;
import Dao.ProfessorDAO;
import Dao.TreinoDAO;
import Model.Aluno;
import Model.Professor;
import Model.Treino;
import com.mysql.cj.protocol.a.SqlDateValueEncoder;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class gerenciadorAcademia {

    public static void main(String[] args) {
        AlunoDAO alunoConn = new AlunoDAO();
        TreinoDAO treinoConn = new TreinoDAO();
        ProfessorDAO professorConn = new ProfessorDAO();
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
                            System.out.println("Tipo de Plano : " + aluno.getTipoDePlano());
                            if (aluno.getPagamentoPlano() == false)
                                System.out.println("Pagamento do Plano: Não pago");
                            System.out.println("Pagamento do Plano : Pago");
                            System.out.println("Treino : " + aluno.getIdTreino());
                        }
                        j = 0;


                    } else if (j == 2) {
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

                        System.out.println("Informe o tipo de plano que deseja (Ex: Mensal, trimestral, etc) : ");
                        plano = sc.nextLine();
                        sc.nextLine();
                        Boolean pagamento = true;

                        System.out.println("Informe o tipo de Treino de preferência: ");
                        idTreino = sc.nextInt();

                        alunoConn.insertAluno(nome, dataNasc, cpf, plano, pagamento, idTreino);
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
                            System.out.println("Tipo de Plano : " + aluno.getTipoDePlano());
                            if (aluno.getPagamentoPlano() == false)
                                System.out.println("Pagamento do Plano: Não pago");
                            System.out.println("Pagamento do Plano : Pago");
                            System.out.println("Treino : " + aluno.getIdTreino());
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

                        List<Aluno> alunoList = alunoConn.getAlunoById(nome);
                        ;
                        for (Aluno aluno : alunoList) {
                            System.out.println("Nome :" + aluno.getNome());
                            System.out.println("Data de Nacimento :" + aluno.getDataNasc());
                            System.out.println("Cpf :" + aluno.getCpf());
                            System.out.println("Tipo de plano:" + aluno.getTipoDePlano());
                            if (aluno.getPagamentoPlano() == false)
                                System.out.println("Pagamento do Plano: Não pago");
                            System.out.println("Pagamento do Plano : Pago");
                            System.out.println("Treino : " + aluno.getIdTreino());

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
                            System.out.println("Tipo de Plano : " + aluno.getTipoDePlano());
                            if (!aluno.getPagamentoPlano())
                                System.out.println("Pagamento do Plano: Não pago");
                            System.out.println("Pagamento do Plano : Pago");
                            System.out.println("Treino : " + aluno.getIdTreino());
                        }
                        System.out.println("Informe o id do Aluno que deseja deletar: ");
                        int id = sc.nextInt();
                        if (alunoConn.findId(id)) {
                            alunoConn.deleteById(id);
                            System.out.println("Aluno deletado com sucesso");
                        } else {
                            System.out.println("Informe um número de id válido");
                        }
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
                    System.out.println("3. Excluir Treino");
                    System.out.println("2. Adicionar Treino");
                    System.out.println("Informe o número da opção que deseja :");
                    j = sc.nextInt();


                    if (j == 1) {

                        List<Treino> treinoList = treinoConn.getTrainning();
                        for (Treino treinos : treinoList) {
                            System.out.println("================================");
                            System.out.println("Id : " + treinos.getId());
                            System.out.println("Tipo de treinamento: " + treinos.getTipoDeTreino());
                            System.out.println("Dias de treino: " + treinos.getDiasDeTreino());
                            System.out.println("Quantidade de exercícios: " + treinos.getQuantidadeDeExercicios());
                            System.out.println("Id do professor : " + treinos.getIdProfessor() + "\n");
                        }
                    } else if (j == 2) {
                        sc.nextLine();
                        System.out.println("Infome o tipo de treino (Ex: PPL/ABC, ABCD, Full body...):");
                        String tipoDeTreino = sc.nextLine();
                        System.out.println("Informe a quantidade de dias de treino : ");
                        int diasDeTreino = sc.nextInt();
                        System.out.println("Informe a quantidade de exercícios: ");
                        int quantidadeDeExercicios = sc.nextInt();
                        System.out.println("Informe o id do Professor: ");
                        int idProfessor = sc.nextInt();

                        treinoConn.addTrainning(tipoDeTreino, diasDeTreino, quantidadeDeExercicios, idProfessor);
                    } else if (j == 3) {
                        sc.nextLine();
                        List<Treino> treinoList = treinoConn.getTrainning();
                        for (Treino treinos : treinoList) {
                            System.out.println("================================");
                            System.out.println("Id : " + treinos.getId());
                            System.out.println("Tipo de treinamento: " + treinos.getTipoDeTreino());
                            System.out.println("Dias de treino: " + treinos.getDiasDeTreino());
                            System.out.println("Quantidade de exercícios: " + treinos.getQuantidadeDeExercicios());
                            System.out.println("Id do professor : " + treinos.getIdProfessor() + "\n");
                        }
                        System.out.println("Informe o número do exercício que deseja excluir: \n");
                        int id = sc.nextInt();
                        treinoConn.deleteTrainning(id);
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
                    System.out.println("2. Excluir Professor ");
                    System.out.println("Informe o número da opção que deseja :");
                    j = sc.nextInt();

                    if(j == 1){
                        List<Professor> professorList = professorConn.getTrainner();
                        for(Professor prof : professorList){
                            System.out.println("================================");
                            System.out.println("Nome : " + prof.getNome());
                            System.out.println("Cpf : " + prof.getCpf());
                            System.out.println("Data de Nascimento : " + prof.getDataNasc());
                            System.out.println("\n");
                        }
                    }
                }
            }
        }
    }
}
