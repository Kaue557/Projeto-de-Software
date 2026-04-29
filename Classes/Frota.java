import java.util.List;
import java.util.ArrayList;

public class Frota {
    private List<Drone> drones;
    private int idFrota;

    // Construtor para inicializar a lista, evitando NullPointerException
    public Frota(int idFrota) {
        this.idFrota = idFrota;
        this.drones = new ArrayList<>();
    }

    public void adicionarDrone(Drone drone) { // O parâmetro deve ter o tipo (Drone) e o nome (drone)
        if (drone != null) {
            this.drones.add(drone);
        }
    }

    public boolean removerDrone(int idDrone) {
        // Lógica para remover o drone da lista pelo ID
        return this.drones.removeIf(d -> d.getIdDrone() == idDrone);
    }
}
