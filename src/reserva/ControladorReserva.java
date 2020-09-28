package reserva;

import java.util.ArrayList;
import java.util.Scanner;

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

    /**
     * 
     * Metodo getCodReservas Extrai codigos de assentos da lista de reservas
     * 
     * @return lista com codigos de assentos
     */
    public ArrayList<String> getCodReservas() {
        ArrayList<String> codReservas = new ArrayList<>();

        for (int i = 0; i < this.getListReservas().size(); i++)
            codReservas.add(this.getListReservas().get(i).getCodigoAssento());

        return codReservas;
    }

    @Override
    public boolean fazerReserva(String nome, String tituloAtracao) {
        // Solicita codigo de assento
        String codigoAssento = "";
        System.out.println("Informe o assento desejado.");
        System.out.println("O assento deve ser feito selecionando uma letra [A-L] e um numero [1-14]. Ex: C4");
        System.out.print("Assento desejado: ");
        Scanner sc = new Scanner(System.in);
        codigoAssento = sc.nextLine().toUpperCase();

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
        String[] fileiras = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };
        String linha = " ______________________________________________________      ______________________________________________________ ";
        String[][] mapa = new String[fileiras.length + 1][17];
        String[] codAssentos = new String[this.getListReservas().size()];

        for (int i = 0; i < this.getListReservas().size(); i++)
            codAssentos[i] = this.getListReservas().get(i).getCodigoAssento();

        System.out.println(linha);
        for (int i = 0; i <= fileiras.length; i++) {
            for (int j = 0; j < mapa[0].length; j++) {
                // Desenha numero das fileiras
                if (i == fileiras.length) {
                    int x = 0; // ajuda a controlar index de numeros
                    if (j > 0 && j < 16) {
                        x = j < 8 ? j : j - 1; // Leva em conta o espacamento na oitava posicao
                        if (j == 8) {
                            // Coloca espacamento entre colunas (conjunto de 7)
                            mapa[i][j] = "    ";
                        } else {
                            // Coloca numero de identificacao
                            if (j < 11) {
                                // mapa[i][j] = String.format("|__%d__|", (j - x));
                                mapa[i][j] = "|__" + x + "__|";
                            } else {
                                // mapa[i][j] = String.format("|__%d_|", (j - x));
                                mapa[i][j] = "|__" + x + "_|";
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
                                break;
                            } else {
                                // Assento livre
                                mapa[i][j] = "|__ __|";
                                continue;
                            }
                        }
                    }
                }
            }
        }

        for (String[] l : mapa) {
            for (String c : l) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
