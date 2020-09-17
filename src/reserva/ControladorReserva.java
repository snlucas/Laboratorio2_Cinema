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
     * 
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

    public ArrayList<String> getCodReservas(ArrayList<Reserva> listReservas) {
        ArrayList<String> codReservas = new ArrayList<>();

        for (int i = 0; i < listReservas.size(); i++)
            codReservas.add(listReservas.get(i).getCodigoAssento());

        return codReservas;
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
    public void mostrarMapaDaSala(ArrayList<Reserva> listReservas) {
        String[] fileiras = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };
        String linha = " ______________________________________________________      ______________________________________________________ ";
        String[][] mapa = new String[fileiras.length + 1][17];
        ArrayList<String> codAssentos = this.getCodReservas(listReservas);


        System.out.println(linha);
        for (int i = 0; i <= fileiras.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                // Desenha numero das fileiras
                if (i == fileiras.length) {
                    if (j > 0 && j < 16) {
                        if (j == 8) {
                            // Coloca espacamento entre colunas (conjunto de 7)
                            mapa[i][j] = "    ";
                        } else {
                            // Coloca numero de identificacao
                            if (j < 10) {
                                mapa[i][j] = String.format("|__%d__|", j);
                            } else {
                                mapa[i][j] = String.format("|__%d_|", j);
                            }
                        }
                    } else {
                        // Coloca espacamento inicial e final
                        mapa[i][j] = "       ";
                    }
                } else {
                    for (String cod : codAssentos) {
                        if (j == 0 || j == 16) {
                            // Coloca letra de identificacao da fileira
                            mapa[i][j] = String.format("|__%s__|", fileiras[i]);
                        } else if (j == 8) {
                            // Coloca espacamento entre colunas (conjunto de 7)
                            mapa[i][j] = "    ";
                        } else {
                            if ((cod.split("")[0].equals(fileiras[i]))
                                    && (cod.split("")[1].equals(Integer.toString(j)))) {
                                // Assento ocupado
                                mapa[i][j] = "|__X__|";
                            } else {
                                // Assento livre
                                mapa[i][j] = "|__ __|";
                            }
                        }
                    }
                }
            }
        }

    }
}
