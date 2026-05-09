// classe "mãe", abstrata
public abstract class Drone {

    // ATRIBUTOS PRIVADOS (Segurança máxima e encapsulamento)
    private int idDrone;
    private String modelo;
    private double statusBateria;
    private Posicao posicaoGPS;

    // CONSTRUTOR PARA DRONE + validações básicas
    public Drone(int idDrone, String modelo, double bateriaInicial) {
        if (bateriaInicial < 0 || bateriaInicial > 100) { // fora do range possível
            throw new IllegalArgumentException("Carga de bateria inválida.");
        }
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("Modelo não pode ser nulo ou vazio.");
        }

        this.idDrone = idDrone;
        this.modelo = modelo;
        this.statusBateria = bateriaInicial;
    }

    // GETTERS
    public int getIdDrone() {
        return this.idDrone;
    }

    public String getModelo() {
        return this.modelo;
    }

    protected double getStatusBateria() {
        return this.statusBateria;
    }

    // MÉTODOS ABSTRATOS (Cada modelo implementa o seu)
    public abstract void decolar();

    // MÉTODOS CONCRETOS (Lógica compartilhada por todos os drones)

    public void enviarTelemetria() {
        // Como todos enviam telemetria da mesma forma, fazemos aqui
        System.out.println("Drone " + this.idDrone + " enviando dados criptografados para a Central.");
    }

    public void atualizarPosicao(Posicao novaPosicao) {
        // parâmetro e validação contra nulos
        if (novaPosicao == null) {
            throw new IllegalArgumentException("Coordenadas inválidas recebidas.");
        }
        this.posicaoGPS = novaPosicao;
    }

    public void atualizarFirmware(String versao) {
        // Validação de formato (ex: 2.1.0) usando Regex para evitar injeção
        if (versao == null || !versao.matches("^\\d+\\.\\d+\\.\\d+$")) {
            throw new IllegalArgumentException("Formato de versão inválido. Use X.Y.Z");
        }
        System.out.println("Atualizando firmware do drone " + this.idDrone + " para a versão " + versao);
    }

    public boolean validarConexao() {
        // Simulação de verificação de latência e TLS
        System.out.println("Verificando conexão ponta a ponta. . .");
        return true;
    }

    public void alternarServidorBackup() {
        System.out.println("Conexão perdida. Drone " + this.idDrone + " conectando ao servidor secundário...");
    }

    public void receberComando(String comando) {
        if (comando == null || comando.isBlank()) {
            throw new IllegalArgumentException("Comando vazio!");
        }
        System.out.println("Drone " + this.idDrone + " executando comando: " + comando);
    }
}