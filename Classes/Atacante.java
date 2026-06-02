// O Contrato de Segurança (Interface)
public interface Atacante {
    boolean executarAtaque(); // Contrato restrito: só expõe o necessário
}

// A Base Genérica e Segura (Classe Abstrata)
public abstract class Drone {
    protected int idDrone;
    protected double statusBateria;
    protected Posicao posicaoGPS;

    public Drone(int idDrone, double bateriaInicial) {
        // Validação na "fronteira" do objeto para garantir estado válido 
        if (bateriaInicial < 0 || bateriaInicial > 100) {
            throw new IllegalArgumentException("Carga de bateria inválida.");
        }
        this.idDrone = idDrone;
        this.statusBateria = bateriaInicial;
    }

    public abstract void decolar(); // Cada modelo decide "como" decola
}

// A Especialização Concreta (Modelo Específico) [cite: 597]
public class DroneAquilaX extends Drone implements Atacante {
    
    public DroneAquilaX(int id) {
        super(id, 100.0); // Garante a validação da classe pai
    }

    @Override
    public void decolar() {
        // Lógica específica do Aquila-X
    }

    @Override
    public boolean executarAtaque() {
        // Lógica de ataque específica deste modelo
        return true;
    }
}