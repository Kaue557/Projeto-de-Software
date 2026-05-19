public class Posicao {

    // Atributos privados
    private double latitude;
    private double longitude;
    private double altitude;

    // Construtor: Exige os dados na criação e já os valida
    public Posicao(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;

        // Se a validação falhar, o sistema impede a criação
        if (!validarCoordenadas()) {
            throw new IllegalArgumentException("Coordenadas geográficas inválidas fornecidas ao sistema.");
        }
    }

    // Lógica real de validação
    public boolean validarCoordenadas() {
        // A Latitude no globo terrestre vai de -90 a 90 graus
        if (this.latitude < -90.0 || this.latitude > 90.0) {
            return false;
        }

        // A Longitude no globo terrestre vai de -180 a 180 graus
        if (this.longitude < -180.0 || this.longitude > 180.0) {
            return false;
        }

        // Para um drone, não faz sentido uma altitude negativa (abaixo do solo/nível do mar base)
        if (this.altitude < 0.0) {
            return false;
        }

        return true; // Se passar por tudo, tudo certo :)
    }

    // GETTERS
    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getAltitude() {
        return this.altitude;
    }
}