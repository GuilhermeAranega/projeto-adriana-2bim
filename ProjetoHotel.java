import java.util.Scanner;

public class ProjetoHotel {

    static Hospede[] hospedes = new Hospede[100];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            Menu();

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    reservarQuarto(scanner);
                    break;
                case 2:
                    cancelarReserva(scanner);
                    break;
                case 3:
                    listarReservas();
                    break;
                case 4:
                    consultarHospede(scanner);
                    break;
                case 5:
                    editarHospede(scanner);
                    break;
            }
        } while (opcao != 6);

        scanner.close();
    }

    public static void Menu() {
        System.out.println("----- Menu -----");
        System.out.println("1 - Reservar quarto");
        System.out.println("2 - Cancelar reserva");
        System.out.println("3 - Listar reservas");
        System.out.println("4 - Consultar hospede");
        System.out.println("5 - Editar hospede");
        System.out.println("6 - Sair");
        System.out.println("----------------");
        System.out.println();
        System.out.println();
        System.out.print("Opcao: ");
    }

    public static void reservarQuarto(Scanner scanner) {
        System.out.print("Nome do hospede: ");
        String nome = scanner.nextLine();
        System.out.print("Email do hospede: ");
        String email = scanner.nextLine();

        if (!VerificarInfos(nome, email)) {
            return;
        }

        System.out.print("Numero do quarto (1-100): ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        if (numero < 1 || numero > 100) {
            System.out.println("Numero invalido.");
            return;
        }

        if (hospedes[numero - 1] == null) {
            hospedes[numero - 1] = new Hospede(nome, email);
            System.out.println("Reserva efetuada com sucesso.");
        } else {
            System.out.println("Quarto ja esta ocupado.");
        }
    }

    public static boolean VerificarInfos(String nome, String email) {
        for (int i = 0; i < 100; i++) {
            if (hospedes[i] != null && hospedes[i].getEmail() == email) {
                System.out.println("Hospede ja possui reserva.");
                return false;
            }
        }
        if (nome == "" || email == "") {
            System.out.println("Nome e email sao obrigatorios.");
            return false;
        }
        return true;
    }

    public static void cancelarReserva(Scanner scanner) {
        System.out.print("Numero do quarto a cancelar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        if (numero < 1 || numero > 100) {
            System.out.println("Numero invalido.");
            return;
        }

        if (hospedes[numero - 1] != null) {
            hospedes[numero - 1] = null;
            System.out.println("Reserva cancelada.");
        } else {
            System.out.println("Quarto ja estava livre.");
        }
    }

    public static void listarReservas() {
        boolean temReserva = false;
        System.out.println("Listagem de quartos reservados:");
        System.out.println("-------------------------");
        for (int i = 0; i < 100; i++) {
            if (hospedes[i] != null) {
                System.out.println(
                        "Quarto " + (i + 1) + " Nome: " + hospedes[i].getNome() + " - Email: "
                                + hospedes[i].getEmail());
                temReserva = true;
            }
        }
        if (!temReserva) {
            System.out.println("Nenhuma reserva encontrada.");
        }
        System.out.println("-------------------------");
    }

    public static void consultarHospede(Scanner scanner) {
        System.out.print("Numero do quarto: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        if (numero < 1 || numero > 100) {
            System.out.println("Numero invalido.");
            return;
        }

        Hospede h = hospedes[numero - 1];
        if (h != null) {
            System.out.println("Nome: " + h.getNome());
            System.out.println("Email: " + h.getEmail());
        } else {
            System.out.println("Quarto esta vazio.");
        }
    }

    public static void editarHospede(Scanner scanner) {
        System.out.print("Numero do quarto: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        if (numero < 1 || numero > 100 || hospedes[numero - 1] == null) {
            System.out.println("Quarto invalido ou vazio.");
            return;
        }

        System.out.print("Novo nome (pressione Enter para manter): ");
        String novoNome = scanner.nextLine();
        System.out.print("Novo email (pressione Enter para manter): ");
        String novoEmail = scanner.nextLine();

        if (novoNome != "") {
            hospedes[numero - 1].setNome(novoNome);
        }
        if (novoEmail != "") {
            hospedes[numero - 1].setEmail(novoEmail);
        }

        System.out.println("Dados atualizados com sucesso.");
    }
}
