package reserva;

/**
 * 
 * Interface com sketch de metodos a ver com reserva
 */
public interface InterfaceControladorReserva {
    /**
     * 
     * Metodo para realizar uma reserva
     * 
     * @param nome          nome da pessoa que quer fazer a reserva.
     * @param tituloAtracao titulo da atracao, nome do filme, ou qualquer atracao
     *                      que se deseja reservar.
     * @return true se foi possivel realizar a reserva e false caso contrario.
     */
    public abstract boolean fazerReserva(String nome, String tituloAtracao);

    /**
     * 
     * Metodo para cancelar uma reserva
     * 
     * @param reserva reserva que se deseja cancelar
     * @return true se foi possivel cancelar a reserva e false caso contrario.
     */
    public abstract boolean cancelarReserva(Reserva reserva);

    /**
     * 
     * Metodo para mostrar um mapa com assentos livres/ocupados e total de assentos
     * livres/ocupados
     */
    public abstract void mostrarMapaDaSala();
}
