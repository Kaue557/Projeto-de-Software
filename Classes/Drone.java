public class Drone{ //classe Drone (criar uma apenas para Frota)

    // ATRIBUTOS
    private int idDrone;
    private String modelo;
    private double statusBateria;
    private Posicao posicaoGPS;


    // METODOS
    public void decolar(){

    }

    public void enviarTelemetria(){

    }

    public boolean executarAtaque(){


        return true;
    }

    public void atualizarPosicao(){

    }

    public void atualizarFirmware(String versao){

    }

    public boolean validarConexao(){


        return true;
    }

    public void enviarComando(comando){

    }

    public String obterResumo(){

    }

    public void alternarServidorBackup(){
        
    }

    public int getIdDrone(){
        return idDrone;
    }
}