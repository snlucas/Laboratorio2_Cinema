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

    /**
     * 
     * Metodo getCodReservas
     * Extrai codigos de assentos da lista de reservas
     * @return lista com codigos de assentos
     */
    public ArrayList<String> getCodReservas() {
        ArrayList<String> codReservas = new ArrayList<>();

        for (int i = 0; i < this.getListReservas().size(); i++)
            codReservas.add(this.getListReservas().get(i).getCodigoAssento());

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
    public void mostrarMapaDaSala() {
        String[] fileiras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
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
                    int x = 0;  // ajuda a controlar index de numeros
                    if (j > 0 && j < 16) {
                        if (j == 8) {
                            // Coloca espacamento entre colunas (conjunto de 7)
                            mapa[i][j] = "    ";
                            x = 2;
                        } else {
                            // Coloca numero de identificacao
                            if (j < 10) {
                                //mapa[i][j] = String.format("|__%d__|", (j - x));
                                mapa[i][j] = "|__" + x + "__|";
                            } else {
                                //mapa[i][j] = String.format("|__%d_|", (j - x));
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
                            if ((cod.split("")[0].equals(fileiras[i])) && (cod.split("")[1].equals(Integer.toString(j)))) {
                                // Assento ocupado
                                mapa[i][j] = "|__X__|";
                                break;
                            } else {
                                // Assento livre
                                mapa[i][j] = "|__ __|";
                                break;
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

    /**
     * 
     * Metodo getMapaDaSala
     * Atribui a uma matriz 
     * todo o mapa da Sala com
     * assentos livres/ocupados
     * 
     * @return mapa da sala
     */
    /*
    public String[][] getMapaDaSala() {
        String[] fileiras = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };
        String linha = " ______________________________________________________      ______________________________________________________ ";
        String[][] mapa = new String[fileiras.length + 2][18];
        ArrayList<String> codAssentos = this.getCodReservas();


        mapa[0][0] = linha;
        for (int i = 1; i <= fileiras.length; i++) {
            for (int j = 1; j < mapa[0].length; j++) {
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

        return mapa;

    }
    */
}
