public class Sensor {

    // ENUMERADOR: Tipos restritos de sensores
    public enum Tipo {
        LIDAR,
        GPS,
        CAMERA
        // Adicionado com base no RF-04 (Visão Computacional)
    }

    // ATRIBUTOS PRIVADOS*
    private Tipo tipoSensor;
    private boolean estahOperacional;
    private double alcanceMaximo;

    // CONSTRUTOR: Regras para "fabricar" um sensor válido
    public Sensor(Tipo tipoSensor, double alcanceMaximo) {
        if (tipoSensor == null) {
            throw new IllegalArgumentException("O sensor deve ter um tipo definido.");
        }
        if (alcanceMaximo <= 0) {
            throw new IllegalArgumentException("O alcance máximo deve ser maior que zero.");
        }

        this.tipoSensor = tipoSensor;
        this.alcanceMaximo = alcanceMaximo;
        this.estahOperacional = true; // Por padrão, o sensor nasce funcionando
    }

    // GETTERS
    public Tipo getTipoSensor() {
        return this.tipoSensor;
    }

    public double getAlcanceMaximo() {
        return this.alcanceMaximo;
    }

    public boolean isOperacional() {
        return this.estahOperacional;
    }

    // MÉTODOS DE AÇÃO (Comportamento)
    public double scan() {
        // Validação defensiva: se o sensor estiver quebrado, não escaneia
        if (!this.estahOperacional) {
            System.out.println("Falha: Sensor " + this.tipoSensor + " está inoperante!");
            return -1.0; // Código de erro simulado
        }

        System.out.println("Sensor " + this.tipoSensor + " escaneando até " + this.alcanceMaximo + " metros...");
        // Simula a leitura retornando um valor entre 0 e o alcance máximo
        return Math.random() * this.alcanceMaximo;
    }

    public boolean verificarIntegridade() {
        System.out.println("Rodando diagnóstico no sensor " + this.tipoSensor + "...");
        // Em um sistema real, aqui o código conversaria com o hardware.
        // Se detectasse falha física, faríamos: this.estahOperacional = false;
        return this.estahOperacional;
    }
}