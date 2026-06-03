import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

/*
Kauê Lima Rodrigues Meneses     10410594
Rayana Pimentel Marques Lopes   10435370
*/

public class SistemaCentral {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        imprimirCabecalho();

        // --- 1. CADASTRO DE ADMIN ---
        System.out.println("=== CADASTRO DE OPERADOR ===");

        int reg = lerInteiroSeguro(scanner, "Digite seu número de registro militar: ");

        int nivel = 0;
        while (nivel != 1 && nivel != 5) {
            nivel = lerInteiroSeguro(scanner, "Defina o nível de acesso (1-Comum, 5-Admin): ");
            if (nivel != 1 && nivel != 5) System.out.println("[ERRO] Nível inválido. Escolha 1 ou 5.\n");
        }

        String bio = "";
        while (true) {
            System.out.print("Cadastre sua biometria (Digite uma senha contendo letras): ");
            bio = scanner.nextLine();
            if (bio.matches("^[0-9]+$")) {
                System.out.println("[ERRO] Formato inválido! A biometria não pode ser apenas numérica.\n");
            } else if (bio.trim().isEmpty()) {
                System.out.println("[ERRO] A biometria não pode ser vazia.\n");
            } else {
                break;
            }
        }

        Operador admin = new Operador(reg, nivel, bio);
        System.out.println("\nOperador cadastrado com sucesso! Iniciando sistema...\n");
        Thread.sleep(2500);

        // --- 2. AUTENTICAÇÃO ---
        System.out.print("Reconhecimento Biométrico exigido. Insira biometria: ");
        String tentativa = scanner.nextLine();

        if (!admin.autenticar(tentativa)) {
            System.out.println("Acesso Negado. Acionando segurança.");
            scanner.close();
            return;
        }

        // --- 3. SETUP DA MISSÃO ---
        System.out.println("\n[ACESSO CONCEDIDO] Bem-vindo à Central Falcão Sombrio.");

        int qtdDrones = 0;
        while (qtdDrones <= 0) {
            qtdDrones = lerInteiroSeguro(scanner, "Quantos drones Aquila-X serão mobilizados? ");
            if (qtdDrones <= 0) System.out.println("[ERRO] A missão precisa de pelo menos 1 drone ativo.\n");
        }

        System.out.println("\nIniciando Missão com " + qtdDrones + " drones...");
        System.out.println("Sincronizando telemetria...\n");
        Thread.sleep(3000);

        // --- 4. LOOP DE SIMULAÇÃO (EVENTOS ALEATÓRIOS) ---
        boolean missaoAtiva = true;
        int rodada = 1;

        // VARIÁVEL DE ESTADO: Lembra quantos drones estão voando "no cheiro" da gasolina
        int dronesEmRisco = 0;

        while (missaoAtiva && qtdDrones > 0) {
            System.out.println("--- Tempo de Missão: " + rodada + "0 min ---");

            // =========================================================
            // AÇÃO DAS CONSEQUÊNCIAS: Checagem contínua de risco
            // =========================================================
            if (dronesEmRisco > 0) {
                for (int i = 0; i < dronesEmRisco; i++) {
                    // 30% de chance de queda para cada drone que foi ignorado
                    if (random.nextInt(100) < 30) {
                        System.out.println("[FALHA DE ENERGIA] A bateria esgotou completamente! Um drone caiu e foi perdido.");
                        qtdDrones--;
                        dronesEmRisco--; // Remove o drone da lista de risco, pois ele já caiu
                    }
                }

                // Se as quedas zerarem a frota, quebra o laço na hora
                if (qtdDrones <= 0) {
                    System.out.println("\n[FALHA] Todos os drones foram perdidos devido à falta de energia. Missão abortada.");
                    missaoAtiva = false;
                    break;
                }
            }
            // =========================================================

            int evento = random.nextInt(10) + 1;

            if (evento <= 5) {
                System.out.println("[STATUS] Frota operando nominalmente. Rota mantida.");

            } else if (evento <= 7) {
                System.out.println("[AVISO] Um drone reportou bateria em nível crítico (15%)!");

                int decisao = 0;
                while (decisao != 1 && decisao != 2) {
                    decisao = lerInteiroSeguro(scanner, "Ação exigida (1: Retornar à base | 2: Ignorar e prosseguir): ");
                    if (decisao != 1 && decisao != 2) System.out.println("[ERRO] Opção inválida. Escolha 1 ou 2.\n");
                }

                if (decisao == 1) {
                    System.out.println(">> Drone retornando. Removido da missão principal.");
                    qtdDrones--;
                } else {
                    System.out.println(">> Risco assumido. Este drone passará a operar no limite crítico.");
                    dronesEmRisco++; // Marca o drone na lista de risco para as próximas rodadas
                }

            } else if (evento == 8) {
                System.out.println("[CRÍTICO] Ameaça detectada! Mísseis inimigos no radar!");

                int decisao = 0;
                while (decisao != 1 && decisao != 2) {
                    decisao = lerInteiroSeguro(scanner, "Ação exigida (1: Evasiva Autônoma | 2: Assumir Controle Manual): ");
                    if (decisao != 1 && decisao != 2) System.out.println("[ERRO] Opção inválida. Escolha 1 ou 2.\n");
                }

                if (decisao == 2) {
                    admin.assumirControle(1);
                    System.out.println(">> Manobra manual executada. Dano evitado.");
                } else {
                    System.out.println(">> IA tentou evasiva, mas um drone foi atingido e abatido!");
                    qtdDrones--;

                    // Garante que não tenhamos "drones fantasmas" em risco se o míssil abater o que estava sem bateria
                    if (dronesEmRisco > qtdDrones) {
                        dronesEmRisco = qtdDrones;
                    }
                }

            } else {
                System.out.println("\n[SUCESSO] Alvos confirmados e neutralizados. Retornando ao ponto de extração.");
                missaoAtiva = false;
            }

            if (qtdDrones == 0 && missaoAtiva) { // Checagem final de segurança
                System.out.println("\n[FALHA] Todos os drones foram perdidos. Missão abortada.");
                missaoAtiva = false;
            }

            rodada++;
            System.out.println();
            Thread.sleep(2500);
        }

        System.out.println("=== RELATÓRIO FINAL ===");
        System.out.println("Drones restantes: " + qtdDrones);
        System.out.println("Log de auditoria gerado e salvo no banco de dados.");
        scanner.close();
    }

    // ====================================================================
    // MÉTODOS AUXILIARES
    // ====================================================================

    private static int lerInteiroSeguro(Scanner scanner, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("[ERRO] Formato inválido! Por favor, digite apenas números inteiros.\n");
                scanner.nextLine();
            }
        }
    }

    private static void imprimirCabecalho() {
        System.out.println("=====================================  ");
        System.out.println("    ___   ____  __  __ __    ___       ");
        System.out.println("   /   | / __ \\/ / /// /    /   |      ");
        System.out.println("  / /| |/ / / / / / // /   / /| |      ");
        System.out.println(" / ___ / /_/ / /_/ // /__ / ___ |      ");
        System.out.println("/_/  |_\\____/\\__//_____ //  | |      ");
        System.out.println("                                       ");
        System.out.println("      SISTEMA FALCÃO SOMBRIO v1.0      ");
        System.out.println("=====================================\n");
    }
}