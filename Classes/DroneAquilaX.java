public class DroneAquilaX extends Drone implements Atacante {

    public DroneAquilaX(int id) {
        // Passa os dados para a superclasse validar e construir
        super(id, "Aquila-X", 100.0);
    }

    @Override
    public void decolar() {
        // Usa o getter para checar se a decolagem é segura
        if (this.getStatusBateria() > 10.0) {
            System.out.println("Drone " + getModelo() + " acionando motores principais. Decolagem autorizada.");
        } else {
            System.out.println("Bateria baixa. Decolagem abortada.");
        }
    }

    @Override
    public boolean executarAtaque() {
        // Cumpre o contrato da interface Atacante
        System.out.println(getModelo() + " detectou AMEAÇA. Executando protocolo de ataque de precisão.");
        return true;
    }
}