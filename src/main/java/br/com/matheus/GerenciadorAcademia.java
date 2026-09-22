package br.com.matheus;

import dao.AlunoDAO;
import dao.PagamentoDAO;
import dao.PlanoDAO;
import model.Aluno;
import model.Pagamentos;
import model.Planos;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Menu principal, organizado em um método por tela (menuPrincipal,
 * menuAlunos, menuPlanos, menuPagamentos). Cada um é um simples
 * do-while + switch: fácil de ler, fácil de seguir o fluxo, fácil de
 * adicionar uma opção nova sem duplicar lógica.
 */
public class GerenciadorAcademia {

    static AlunoDAO alunoDAO = new AlunoDAO();
    static PlanoDAO planoDAO = new PlanoDAO();
    static PagamentoDAO pagamentoDAO = new PagamentoDAO();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        menuPrincipal();
        sc.close();
    }

    // ---------- Menu principal ----------

    static void menuPrincipal() throws SQLException {
        int opcao;
        do {
            System.out.println("================================");
            System.out.println("      GERENCIADOR DE ACADEMIA");
            System.out.println("================================");
            System.out.println("1. Alunos");
            System.out.println("2. Planos");
            System.out.println("3. Pagamentos");
            System.out.println("0. Sair");

            opcao = lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> menuAlunos();
                case 2 -> menuPlanos();
                case 3 -> menuPagamentos();
                case 0 -> System.out.println("Volte sempre!");
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 0);
    }

    // ---------- Menu: Alunos ----------

    static void menuAlunos() throws SQLException {
        int opcao;
        do {
            System.out.println("---- ALUNOS ----");
            System.out.println("1. Mostrar todos os Alunos");
            System.out.println("2. Adicionar novo Aluno");
            System.out.println("3. Alterar Aluno");
            System.out.println("4. Pesquisar Aluno");
            System.out.println("5. Deletar Aluno");
            System.out.println("0. Voltar");

            opcao = lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> listarAlunos();
                case 2 -> adicionarAluno();
                case 3 -> alterarAluno();
                case 4 -> pesquisarAluno();
                case 5 -> deletarAluno();
                case 0 -> { /* volta pro menu principal */ }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 0);
    }

    static void listarAlunos() {
        List<Aluno> alunos = alunoDAO.getAluno();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (Aluno a : alunos) {
            System.out.println("================================");
            System.out.println("Id: " + a.getId());
            System.out.println("Nome: " + a.getNome());
            System.out.println("Data de nascimento: " + a.getDataNasc());
            System.out.println("Cpf: " + a.getCpf());
            System.out.println("Id do plano: " + a.getIdPlano());
            System.out.println("Próximo pagamento: " + a.getProximoPagamento());
        }
    }

    static void adicionarAluno() throws SQLException {
        System.out.println("---- Inserir Aluno ----");
        sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        int dia = lerInt("Dia de nascimento: ");
        int mes = lerInt("Mês de nascimento: ");
        int ano = lerInt("Ano de nascimento: ");
        LocalDate dataNasc = LocalDate.of(ano, mes, dia);

        String cpf = lerCpfValido();
        List<Planos> planosList = planoDAO.getPlanos();
        for(Planos p : planosList){
            System.out.println("Planos :");
            System.out.println("Id: " + p.getId());
            System.out.println("Tipo de Plano: " + p.getName());
            System.out.println("Preço: " + p.getPreco());
            System.out.println("Dias de duração do plano: " + p.getDiasDoPlano());
        }

        int idPlano = lerInt("Id do plano desejado: ");

        alunoDAO.insertAluno(nome, dataNasc, cpf, idPlano);
        System.out.println("Aluno inserido com sucesso!");
    }

    static void alterarAluno() {
        listarAlunos();
        int id = lerInt("Id do aluno que deseja alterar: ");
        if (!alunoDAO.findId(id)) {
            System.out.println("Id de aluno inválido.");
            return;
        }
        sc.nextLine();
        System.out.print("Novo nome: ");
        String nome = sc.nextLine();
        alunoDAO.alterAluno(id, nome);
        System.out.println("Aluno alterado com sucesso!");
    }

    static void pesquisarAluno() {
        sc.nextLine();
        System.out.print("Nome do aluno: ");
        String nome = sc.nextLine();
        List<Aluno> alunos = alunoDAO.getAlunoByName(nome);
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado.");
            return;
        }
        for (Aluno a : alunos) {
            System.out.println("Id: " + a.getId() + " | Nome: " + a.getNome());
        }
    }

    static void deletarAluno() {
        listarAlunos();
        int id = lerInt("Id do aluno que deseja deletar: ");
        if (!alunoDAO.findId(id)) {
            System.out.println("Id de aluno inválido.");
            return;
        }
        alunoDAO.deleteById(id);
        System.out.println("Aluno deletado com sucesso!");
    }

    // ---------- Menu: Planos ----------

    static void menuPlanos() {
        int opcao;
        do {
            System.out.println("---- PLANOS ----");
            System.out.println("1. Mostrar todos os Planos");
            System.out.println("2. Adicionar novo Plano");
            System.out.println("3. Deletar Plano");
            System.out.println("0. Voltar");

            opcao = lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> listarPlanos();
                case 2 -> adicionarPlano();
                case 3 -> deletarPlano();
                case 0 -> { /* volta pro menu principal */ }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 0);
    }

    static void listarPlanos() {
        List<Planos> planos = planoDAO.getPlanos();
        if (planos.isEmpty()) {
            System.out.println("Nenhum plano cadastrado.");
            return;
        }
        for (Planos p : planos) {
            System.out.println("================================");
            System.out.println("Id: " + p.getId());
            System.out.println("Nome: " + p.getName());
            System.out.println("Preço: " + p.getPreco());
            System.out.println("Duração (dias): " + p.getDiasDoPlano());
        }
    }

    static void adicionarPlano() {
        System.out.println("---- Inserir Plano ----");
        sc.nextLine();
        System.out.print("Nome do plano: ");
        String nome = sc.nextLine();
        double preco = lerDouble("Preço: ");
        int duracao = lerInt("Duração em dias: ");

        planoDAO.insertPlano(nome, preco, duracao);
        System.out.println("Plano inserido com sucesso!");
    }

    static void deletarPlano() {
        listarPlanos();
        int id = lerInt("Id do plano que deseja deletar: ");
        planoDAO.deleteById(id);
    }

    // ---------- Menu: Pagamentos ----------

    static void menuPagamentos() {
        int opcao;
        do {
            System.out.println("---- PAGAMENTOS ----");
            System.out.println("1. Mostrar todos os Pagamentos");
            System.out.println("2. Registrar novo Pagamento");
            System.out.println("0. Voltar");

            opcao = lerInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> listarPagamentos();
                case 0 -> { /* volta pro menu principal */ }
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 0);
    }

    static void listarPagamentos() {
        List<Pagamentos> pagamentos = pagamentoDAO.getPagamentos();
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento registrado.");
            return;
        }
        for (Pagamentos p : pagamentos) {
            System.out.println("================================");
            System.out.println("Id do aluno: " + p.getIdAluno());
            System.out.println("Valor: " + p.getAmount());
            System.out.println("Data do pagamento: " + p.getDataDePagamento());
        }
    }


    // ---------- Leitura segura de entrada ----------

    static int lerInt(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida, informe um número.");
                sc.next();
            }
        }
    }

    static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida, informe um valor numérico.");
                sc.next();
            }
        }
    }

    static String lerCpfValido() {
        System.out.print("Cpf: ");
        String cpf = sc.nextLine();
        while (cpf.length() != 11) {
            System.out.println("CPF inválido (deve ter 11 dígitos).");
            System.out.print("Cpf: ");
            cpf = sc.nextLine();
        }
        return cpf;
    }
}