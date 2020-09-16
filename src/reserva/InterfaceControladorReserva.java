package reserva;

/**
 * 
 * Interface com sketch de metodos a ver com reserva
 */
public interface InterfaceControladorReserva {
    /**
     * 
     * Metodo para realizar uma reserva
     * @return true se foi possivel realizar a reserva e false caso contrario.
     */
    public abstract boolean fazerReserva();

    /**
     * 
     * Metodo para cancelar uma reserva
     * @return true se foi possivel cancelar a reserva e false caso contrario.
     */
    public abstract boolean cancelarReserva();

    /**
     * 
     * Metodo para mostrar um mapa com assentos livres/ocupados e total 
     * de assentos livres/ocupados
     */
    public abstract void mostrarMapaDaSala();
}
