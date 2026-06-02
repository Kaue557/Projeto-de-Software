import java.util.Scanner;
import java.util.Random;

public class SistemaCentral {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        imprimirCabecalho();

        // --- 1. CADASTRO DE ADMIN ---
        System.out.println("=== CADASTRO DE OPERADOR ===");
        System.out.print("Digite seu número de registro militar: ");
        int reg = scanner.nextInt();
        System.out.print("Defina o nível de acesso (1-Comum, 5-Admin): ");
        int nivel = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        System.out.print("Cadastre sua biometria (Digite uma senha de texto): ");
        String bio = scanner.nextLine();

        Operador admin = new Operador(reg, nivel, bio);
        System.out.println("\nOperador cadastrado com sucesso! Iniciando sistema...\n");
        Thread.sleep(1000);

        // --- 2. AUTENTICAÇÃO ---
        System.out.print("Reconhecimento Biométrico exigido. Insira biometria: ");
        String tentativa = scanner.nextLine();
        
        if (!admin.autenticar(tentativa)) {
            System.out.println("Acesso Negado. Acionando segurança.");
            return; // Encerra o programa
        }

        // --- 3. SETUP DA MISSÃO ---
        System.out.println("\n[ACESSO CONCEDIDO] Bem-vindo à Central Falcão Sombrio.");
        System.out.print("Quantos drones Aquila-X serão mobilizados? ");
        int qtdDrones = scanner.nextInt();
        
        System.out.println("\nIniciando Missão com " + qtdDrones + " drones...");
        System.out.println("Sincronizando telemetria...\n");
        Thread.sleep(2000);

        // --- 4. LOOP DE SIMULAÇÃO (EVENTOS ALEATÓRIOS) ---
        boolean missaoAtiva = true;
        int rodada = 1;

        while (missaoAtiva && qtdDrones > 0) {
            System.out.println("--- Tempo de Missão: " + rodada + "0 min ---");
            
            // Sorteia um evento aleatório de 1 a 10
            int evento = random.nextInt(10) + 1;

            if (evento <= 5) {
                // 50% de chance de tudo correr bem
                System.out.println("[STATUS] Frota operando nominalmente. Rota mantida.");
            
            } else if (evento <= 7) {
                // 20% de chance de bateria fraca
                System.out.println("[AVISO] Drone 1 com bateria em nível crítico (15%)!");
                System.out.print("Ação exigida (1: Retornar à base | 2: Ignorar e prosseguir): ");
                int decisao = scanner.nextInt();
                if (decisao == 1) {
                    System.out.println(">> Drone retornando. Removido da missão principal.");
                    qtdDrones--;
                } else {
                    System.out.println(">> Risco assumido. O drone pode cair a qualquer momento.");
                }

            } else if (evento == 8) {
                // 10% de chance de ataque inimigo
                System.out.println("[CRÍTICO] Ameaça detectada! Mísseis inimigos no radar!");
                System.out.print("Ação exigida (1: Evasiva Autônoma | 2: Assumir Controle Manual): ");
                int decisao = scanner.nextInt();
                if (decisao == 2) {
                    admin.assumirControle(1);
                    System.out.println(">> Manobra manual executada. Dano evitado.");
                } else {
                    System.out.println(">> IA tentou evasiva, mas um drone foi atingido e abatido!");
                    qtdDrones--;
                }

            } else {
                // 20% de chance de sucesso e fim da missão
                System.out.println("\n[SUCESSO] Alvos confirmados e neutralizados. Retornando ao ponto de extração.");
                missaoAtiva = false;
            }

            if (qtdDrones == 0) {
                System.out.println("\n[FALHA] Todos os drones foram perdidos. Missão abortada.");
                missaoAtiva = false;
            }

            rodada++;
            System.out.println();
            Thread.sleep(1500); // Pausa dramática entre os turnos
        }

        System.out.println("=== RELATÓRIO FINAL ===");
        System.out.println("Drones restantes: " + qtdDrones);
        System.out.println("Log de auditoria gerado e salvo no banco de dados.");
        scanner.close();
    }

    // Método privado auxiliar apenas para desenhar a interface
    private static void imprimirCabecalho() {
        System.out.println("=========================================");
        System.out.println("    ___   ____  __  __   _  __           ");
        System.out.println("   /   | / __ \\/ / / /  / |/ /_  __     ");
        System.out.println("  / /| |/ / / / / / /  /    / / / /      ");
        System.out.println(" / ___ / /_/ / /_/ /  / /|  / /_/ /       ");
        System.out.println("/_/  |_\\____/\\____/  /_/ |_/\\__,_/  ");
        System.out.println("                                         ");
        System.out.println("      SISTEMA FALCÃO SOMBRIO v1.0        ");
        System.out.println("=========================================\n");
    }
}