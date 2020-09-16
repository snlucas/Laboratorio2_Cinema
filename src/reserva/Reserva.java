package reserva;

/**
 * 
 * Classe para realizar uma reserva 
 */
public class Reserva {
    private String nome;
    private String tituloAtracao;
    private String codigoAssento;

    /**
     * 
     * Construtor da classe Reserva
     * @param nome
     * @param tituloAtracao
     * @param codigoAssento
     */
    public Reserva(String nome, String tituloAtracao, String codigoAssento) {
        this.nome = nome;
        this.tituloAtracao = tituloAtracao;
        this.codigoAssento = codigoAssento;
    }

    /**
     * 
     * @return nome do reservista
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @return titulo da atracao
     */
    public String getTituloAtracao() {
        return tituloAtracao;
    }

    /**
     * 
     * @return codigo do assento do reservista
     */
    public String getCodigoAssento() {
        return codigoAssento;
    }
}
