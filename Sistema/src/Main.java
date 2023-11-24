//Lucas Marmitt Catarino
import Paciente.Paciente;
import Consulta.Atendimento;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatoBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ArrayList<Paciente> pacientes = new ArrayList<>();
        int opcao;
        do {
            System.out.println("\n=== Sistema de Atendimento Médico ===");
            System.out.println("1. Incluir Paciente");
            System.out.println("2. Alterar Paciente");
            System.out.println("3. Realizar Consulta");
            System.out.println("4. Listar Pacientes");
            System.out.println("5. Mostrar Paciente");
            System.out.println("6. Apagar Paciente");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    incluirPaciente(scanner, formatoBr, pacientes);
                    break;
                case 2:
                    alterarPaciente(scanner, pacientes);
                    break;
                case 3:
                    realizarConsulta(scanner, formatoBr, pacientes);
                    break;
                case 4:
                    listarPacientes(pacientes);
                    break;
                case 5:
                    mostrarPaciente(scanner, pacientes);
                    break;
                case 6:
                    apagarPaciente(scanner, pacientes);
                    break;
                case 7:
                    System.out.println("Saindo do sistema...");
                    System.out.println("Sistema finalizado com sucesso!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);
    }

    private static void aguardarEnter() {
        System.out.println("\nPressione Enter para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        for (int i = 0; i < 30; i++){
            System.out.println();
        }
    }

    private static void incluirPaciente(Scanner scanner, DateTimeFormatter formatoBr, ArrayList<Paciente> pacientes) {
        Paciente novoPaciente = new Paciente();

        System.out.print("Digite o nome do paciente: ");
        String nome = scanner.next();
        novoPaciente.setNome(nome);

        System.out.print("Digite o sobrenome do paciente: ");
        String sobrenome = scanner.next();
        novoPaciente.setSobrenome(sobrenome);

        System.out.print("Digite a data de nascimento do paciente (dd/MM/yyyy): ");
        String dataNascimentoStr = scanner.next();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatoBr);
        novoPaciente.setDataNascimento(dataNascimento);

        pacientes.add(novoPaciente);
        System.out.println("Paciente cadastrado com sucesso!");
        aguardarEnter();
    }


    private static void alterarPaciente(Scanner scanner, ArrayList<Paciente> pacientes) {
        System.out.print("Digite o nome do paciente que deseja alterar: ");
        String nomePesquisado = scanner.next();

        DateTimeFormatter formatoBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Paciente paciente : pacientes) {
            if (paciente.getNome().equalsIgnoreCase(nomePesquisado)) {
                System.out.print("Digite o novo nome do paciente: ");
                String novoNome = scanner.next();
                paciente.setNome(novoNome);

                System.out.print("Digite o novo sobrenome do paciente: ");
                String novoSobrenome = scanner.next();
                paciente.setSobrenome(novoSobrenome);

                System.out.print("Digite a nova data de nascimento do paciente (dd/MM/yyyy): ");
                String novaDataNascimentoStr = scanner.next();
                LocalDate novaDataNascimento = LocalDate.parse(novaDataNascimentoStr, formatoBr);
                paciente.setDataNascimento(novaDataNascimento);

                System.out.println("Paciente alterado com sucesso!");
                aguardarEnter();
                return;
            }
        }

        System.out.println("Paciente não encontrado.");
        aguardarEnter();
    }
    private static void realizarConsulta(Scanner scanner, DateTimeFormatter formatoBr, ArrayList<Paciente> pacientes) {
        System.out.print("Digite o nome do paciente para a consulta: ");
        String nomePesquisado = scanner.next();
        scanner.nextLine(); // Consumir quebra de linha pendente

        for (Paciente paciente : pacientes) {
            if (paciente.getNome().equalsIgnoreCase(nomePesquisado)) {
                Atendimento novaConsulta = new Atendimento();

                System.out.print("Digite a descrição da consulta: ");
                String descricaoConsulta = scanner.nextLine();
                novaConsulta.setDescricao(descricaoConsulta);

                paciente.adicionarConsulta(novaConsulta);
                System.out.println("Consulta realizada com sucesso!");
                aguardarEnter();
                return;
            }
        }

        System.out.println("Paciente não encontrado.");
        aguardarEnter();
    }



    private static void listarPacientes(ArrayList<Paciente> pacientes) {
        DateTimeFormatter formatoBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("\nLista de Pacientes:");
        for (Paciente paciente : pacientes) {
            System.out.println("Nome: " + paciente.getNome() + " " + paciente.getSobrenome());
            System.out.println("Data de Nascimento: " + paciente.getDataNascimento().format(formatoBr));
            System.out.println("------------------------------");
        }
        aguardarEnter();
    }

    private static void mostrarPaciente(Scanner scanner, ArrayList<Paciente> pacientes) {
        System.out.print("Digite o nome do paciente que deseja visualizar: ");
        String nomePesquisado = scanner.next();

        for (Paciente paciente : pacientes) {
            if (paciente.getNome().equalsIgnoreCase(nomePesquisado)) {
                System.out.println("=== Dados do Paciente ===");
                System.out.println(paciente);

                ArrayList<Atendimento> atendimentos = paciente.getAtendimentos();
                if (!atendimentos.isEmpty()) {
                    System.out.println("\n=== Atendimentos ===");
                    for (Atendimento atendimento : atendimentos) {
                        System.out.println(atendimento);
                        System.out.println("------------------------------");
                    }
                } else {
                    System.out.println("O paciente não possui atendimentos registrados.");
                }
                aguardarEnter();
                return;
            }
        }

        System.out.println("Paciente não encontrado.");
        aguardarEnter();
    }

    private static void apagarPaciente(Scanner scanner, ArrayList<Paciente> pacientes) {
        System.out.print("Digite o nome do paciente que deseja apagar: ");
        String nomePesquisado = scanner.next();

        for (Paciente paciente : pacientes) {
            if (paciente.getNome().equalsIgnoreCase(nomePesquisado)) {
                pacientes.remove(paciente);
                System.out.println("Paciente apagado com sucesso!");
                aguardarEnter();
                return;
            }
        }

        System.out.println("Paciente não encontrado.");
        aguardarEnter();
    }
}
