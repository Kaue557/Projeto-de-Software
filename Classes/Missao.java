public class Missao {

    // ATRIBUTOS PRIVADOS
    private int codigoMissao;
    private String dataInicio;
    private String objetivo;
    private boolean emAndamento; // Variável de controle de estado

    // CONSTRUTOR
    public Missao(int codigoMissao, String dataInicio, String objetivo) {
        if (codigoMissao <= 0) {
            throw new IllegalArgumentException("O código da missão deve ser um número positivo.");
        }
        if (objetivo == null || objetivo.trim().isEmpty()) {
            throw new IllegalArgumentException("A missão não pode ser criada sem um objetivo definido.");
        }
        if (dataInicio == null || dataInicio.trim().isEmpty()) {
            throw new IllegalArgumentException("A data de início não pode ser vazia.");
        }

        this.codigoMissao = codigoMissao;
        this.dataInicio = dataInicio;
        this.objetivo = objetivo;
        this.emAndamento = false; // Toda missão nasce pausada/aguardando início
    }

    // MÉTODOS DE AÇÃO
    public void iniciar() {
        if (this.emAndamento) {
            System.out.println("Aviso: A missão " + this.codigoMissao + " já está em andamento.");
            return; // Impede que o método continue executando
        }

        this.emAndamento = true;
        System.out.println("Missão " + this.codigoMissao + " iniciada com sucesso. Objetivo: " + this.objetivo);
    }

    public void finalizar() {
        if (!this.emAndamento) {
            System.out.println("Erro: Não é possível finalizar uma missão que não está em andamento.");
            return;
        }

        this.emAndamento = false;
        System.out.println("Missão " + this.codigoMissao + " finalizada. Preparando logs de auditoria.");
    }

    // INTEGRAÇÃO COM AUDITORIA
    public String gerarLog() {
        // Uso do StringBuilder para performance, formatando o texto de forma estruturada para o banco
        StringBuilder log = new StringBuilder();

        log.append("[MISSÃO ").append(this.codigoMissao).append("] | ");
        log.append("Início: ").append(this.dataInicio).append(" | ");
        log.append("Objetivo: ").append(this.objetivo).append(" | ");
        log.append("Status Atual: ").append(this.emAndamento ? "EM EXECUÇÃO" : "CONCLUÍDA");

        // Retorna a String final imutável para ser enviada e gravada no Banco de Dados
        return log.toString();
    }

    // 5. GETTERS (Acesso seguro aos dados)
    public int getCodigoMissao() {
        return this.codigoMissao;
    }

    public String getObjetivo() {
        return this.objetivo;
    }

    public boolean isEmAndamento() {
        return this.emAndamento;
    }
}