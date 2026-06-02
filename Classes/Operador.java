public class Operador {

    // Atributos privados para evitar vazamento
    private int registro;
    private int nivelAcesso;
    private String biometriaHash; // Simulando a biometria com uma String segura

    // Construtor
    public Operador(int registro, int nivelAcesso, String biometriaHash) {
        this.registro = registro;
        this.nivelAcesso = nivelAcesso;
        this.biometriaHash = biometriaHash;
    }

    // Método de autenticação (validando a entrada contra o dado armazenado)
    public boolean autenticar(String tentativaBiometria) {
        if (tentativaBiometria == null || tentativaBiometria.isBlank()) {
            return false;
        }
        return this.biometriaHash.equals(tentativaBiometria);
    }

    public void assumirControle(int idDrone) {
        System.out.println("\n[ALERTA] Operador " + this.registro + " assumiu o controle manual do Drone " + idDrone + "!");
    }

    // Getters
    public int getRegistro() { return this.registro; }
    public int getNivelAcesso() { return this.nivelAcesso; }
}