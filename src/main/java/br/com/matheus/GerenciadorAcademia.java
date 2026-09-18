package br.com.matheus;
import dao.AlunoDAO;
import model.Aluno;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GerenciadorAcademia {
        private static final AlunoDAO alunoDAO = new AlunoDAO();
        private static final Scanner sc = new Scanner(System.in);

        // Marca de controle: quando uma ação retorna false, o menu atual encerra.
        private record MenuOption(int codigo, String rotulo, java.util.function.BooleanSupplier acao) {}

        public static void main(String[] args) {
            runMenu("Opções", List.of(
                    new MenuOption(1, "Alunos", () -> { menuAlunos(); return true; }),
                    new MenuOption(2, "Professores", () -> { menuProfessores(); return true; }),
                    new MenuOption(3, "Treinos", () -> { menuTreinos(); return true; }),
                    new MenuOption(0, "Sair", () -> {
                        System.out.println("Volte sempre!");
                        return false; // encerra o loop principal
                    })
            ));
            sc.close();
        }

        // ---------- Submenu: Alunos ----------

        private static void menuAlunos() {
            runMenu("Alunos", List.of(
                    new MenuOption(1, "Mostrar todos os Alunos", () -> { listarAlunos(); return true; }),
                    new MenuOption(2, "Adicionar novo Aluno", () -> { adicionarAluno(); return true; }),
                    new MenuOption(3, "Alterar Aluno", () -> { alterarAluno(); return true; }),
                    new MenuOption(4, "Pesquisar Aluno", () -> { pesquisarAluno(); return true; }),
                    new MenuOption(5, "Deletar Aluno", () -> { deletarAluno(); return true; }),
                    new MenuOption(0, "Voltar", () -> false)
            ));
        }

        private static void listarAlunos() {
            List<Aluno> alunos = alunoDAO.getAluno();
            if (alunos.isEmpty()) {
                System.out.println("Nenhum aluno cadastrado.");
                return;
            }
            alunos.forEach(GerenciadorAcademia::imprimirAluno);
        }

        private static void adicionarAluno() {
            System.out.println("---- Inserir Aluno ----");
            sc.nextLine(); // limpa buffer antes de ler texto
            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.println("Data de nascimento:");
            int dia = lerInt("Dia: ");
            int mes = lerInt("Mês: ");
            int ano = lerInt("Ano: ");
            LocalDate dataNasc = LocalDate.of(ano, mes, dia);

            String cpf = lerCpfValido();

            int idPlano = lerInt("Id do plano desejado: ");

            alunoDAO.insertAluno(nome, dataNasc, cpf, idPlano, LocalDate.now());
            System.out.println("Aluno inserido com sucesso!");
        }

        private static void alterarAluno() {
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

        private static void pesquisarAluno() {
            sc.nextLine();
            System.out.print("Nome do aluno: ");
            String nome = sc.nextLine();
            List<Aluno> alunos = alunoDAO.getAlunoByName(nome);
            if (alunos.isEmpty()) {
                System.out.println("Nenhum aluno encontrado.");
            }
            alunos.forEach(GerenciadorAcademia::imprimirAluno);
        }

        private static void deletarAluno() {
            listarAlunos();
            int id = lerInt("Id do aluno que deseja deletar: ");
            if (!alunoDAO.findId(id)) {
                System.out.println("Id de aluno inválido.");
                return;
            }
            alunoDAO.deleteById(id);
            System.out.println("Aluno deletado com sucesso!");
        }

        private static void imprimirAluno(Aluno a) {
            System.out.println("================================");
            System.out.println("Id: " + a.getId());
            System.out.println("Nome: " + a.getNome());
            System.out.println("Data de nascimento: " + a.getDataNasc());
            System.out.println("Cpf: " + a.getCpf());
            System.out.println("Id do plano: " + a.getIdPlano());
            System.out.println("Próximo pagamento: " + a.getProximoPagamento());
        }

        // ---------- Submenus ainda não implementados (mesma estrutura) ----------

        private static void menuProfessores() {
            runMenu("Professores", List.of(
                    new MenuOption(1, "Listar todos os Professores", () -> { /* TODO */ return true; }),
                    new MenuOption(2, "Adicionar novo Professor", () -> { /* TODO */ return true; }),
                    new MenuOption(3, "Alterar Professor", () -> { /* TODO */ return true; }),
                    new MenuOption(4, "Excluir Professor", () -> { /* TODO */ return true; }),
                    new MenuOption(0, "Voltar", () -> false)
            ));
        }

        private static void menuTreinos() {
            runMenu("Treinos", List.of(
                    new MenuOption(1, "Listar todos os tipos de treino", () -> { /* TODO */ return true; }),
                    new MenuOption(2, "Adicionar Treino", () -> { /* TODO */ return true; }),
                    new MenuOption(3, "Excluir Treino", () -> { /* TODO */ return true; }),
                    new MenuOption(0, "Voltar", () -> false)
            ));
        }

        // ---------- Motor genérico de menu ----------

        /**
         * Imprime as opções, lê a escolha com segurança e executa a ação
         * correspondente em loop, até que uma ação sinalize "false" (sair).
         */
        private static void runMenu(String titulo, List<MenuOption> opcoes) {
            boolean continuar = true;
            while (continuar) {
                System.out.println("================================");
                System.out.println("            " + titulo);
                opcoes.forEach(o -> System.out.println(" " + o.codigo() + ". " + o.rotulo()));

                int escolha = lerInt("Informe a sua opção: ");

                MenuOption selecionada = opcoes.stream()
                        .filter(o -> o.codigo() == escolha)
                        .findFirst()
                        .orElse(null);

                if (selecionada == null) {
                    System.out.println("Opção inválida, tente novamente.");
                    continue;
                }
                continuar = selecionada.acao().getAsBoolean();
            }
        }

        // ---------- Utilitários de entrada segura ----------

        private static int lerInt(String mensagem) {
            while (true) {
                System.out.print(mensagem);
                try {
                    int valor = sc.nextInt();
                    return valor;
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida, informe um número.");
                    sc.next(); // descarta o token inválido
                }
            }
        }

        private static String lerCpfValido() {
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

