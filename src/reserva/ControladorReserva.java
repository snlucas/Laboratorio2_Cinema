package reserva;

import java.util.ArrayList;

/**
 * 
 * Classe com algoritmos de reserva
 */
public class ControladorReserva implements InterfaceControladorReserva {
    private ArrayList<Reserva> listReservas;

    /**
     * 
     * Construtor da classe ControladorReserva
     * @param reservas lista de reservas do tipo Reserva
     */
    public ControladorReserva(ArrayList<Reserva> reservas) {
        this.listReservas = reservas;
    }

    /**
     * 
     * Construtor padrao da classe ControladorReserva
     */
    public ControladorReserva() {
        this.listReservas = new ArrayList<>();
    }

    public ArrayList<Reserva> getListReservas() {
        return this.listReservas;
    }

    @Override
    public boolean fazerReserva(String nome, String tituloAtracao, String codigoAssento) {
        // Verifica se existe reserva para o mesmo assento
        for (Reserva reserva : listReservas)
            if (reserva.getCodigoAssento() == codigoAssento)
                return false;

        // Cria uma nova reserva e adiciona a lista
        Reserva reserva = new Reserva(nome, tituloAtracao, codigoAssento);
        return this.listReservas.add(reserva);
    }

    @Override
    public boolean cancelarReserva(Reserva reserva) {
        return this.listReservas.remove(reserva);
    }

    @Override
    public void mostrarMapaDaSala() {
        

    }
    
}
