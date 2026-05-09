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

    public String obterResumo() {
        // 1. Validação de segurança: Verifica se a frota está vazia antes de processar
        if (this.drones.isEmpty()) {
            return "A Frota " + this.idFrota + " não possui drones ativos no momento.";
        }

        // 2. Uso do StringBuilder para melhor performance em concatenação de textos
        StringBuilder resumo = new StringBuilder();
        resumo.append("=== Relatório da Frota ID: ").append(this.idFrota).append(" ===\n");
        resumo.append("Total de Drones Ativos: ").append(this.drones.size()).append("\n");
        resumo.append("Detalhamento Operacional:\n");

        // 3. Laço de repetição (for-each) para extrair os dados de cada drone
        for (Drone drone : this.drones) {
            resumo.append(" -> Drone [ID: ").append(drone.getIdDrone())
                    .append(" | Modelo: ").append(drone.getModelo())
                    .append("]\n");
            // Nota: Se a classe Frota e Drone estiverem no mesmo "package" (pasta),
            // a Frota consegue acessar o metodo protected getStatusBateria() do Drone.
        }

        // 4. Converte o objeto StringBuilder de volta para uma String final
        return resumo.toString();
    }
}
