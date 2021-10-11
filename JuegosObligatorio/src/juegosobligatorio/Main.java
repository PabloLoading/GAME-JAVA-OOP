package juegosobligatorio;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        menu(sistema);
    }

    private static void menu(Sistema sistema) {
        Scanner in = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 5) {

            System.out.println("MENU DE OPCIONES\n");
            System.out.println("1. Ingresar un nuevo jugador");
            System.out.println("2. Jugar a Saltar");
            System.out.println("3. Jugar a Rectángulo");
            System.out.println("4. Bitácora");
            System.out.println("5. Salir");
            System.out.println("Seleccione su opción");
            opcion = in.nextInt();
            switch (opcion) {
                case 1:
                    sistema.agregarJugador(crearJugador(sistema));
                    break;
                case 2:
                    if (sistema.getListaJugadores().isEmpty()) {
                        System.out.println("No hay jugadores ingresados. Por favor ingrese un nuevo jugador antes de empezar a jugar.");
                    } else {
                        sistema.crearJuegoSaltar();
                        elegirJugador(sistema);
                        preguntarTableroSaltar(sistema);
                        jugarSaltar(sistema);
                        sistema.agregarPartidaActual();
                    }
                    break;
                case 3:
                    if (sistema.getListaJugadores().isEmpty()) {
                        System.out.println("No hay jugadores ingresados. Por favor ingrese un nuevo jugador antes de empezar a jugar.");
                    } else {
                        sistema.crearJuegoRectangulo();
                        elegirJugador(sistema);
                        jugarRectangulo(sistema);
                        sistema.agregarPartidaActual();
                    }
                    break;
                case 4:
                    ordenar(sistema);
                    break;
                case 5:
                    break;
            }
        }
    }

    private static void preguntarTableroSaltar(Sistema sistema) {
        System.out.println("Ingrese '0' para tablero predeterminado y '1' para tablero al azar");
        Scanner in = new Scanner(System.in);
        int respuesta = in.nextInt();
        if (respuesta == 1) {
            sistema.tableroRandomSaltarS();
        }
    }

    private static void jugarSaltar(Sistema sistema) {
        int color = 0;
        String colores = "RAVM";
        int coloresNoValidos = 0;

        boolean condicion = true;
        while (condicion) {
            if (color == 4) {
                color = 0;
            }
            char letraColor = colores.charAt(color);
            String colorTurno = "";
            switch (letraColor) {
                case 'R':
                    colorTurno = "Rojo";
                    break;
                case 'A':
                    colorTurno = "Azul";
                    break;
                case 'V':
                    colorTurno = "Verde";
                    break;
                case 'M':
                    colorTurno = "Amarillo";
                    break;

            }
            if (sistema.colorJugableSaltarS(letraColor)) {
                coloresNoValidos = 0;
                boolean ok = false;
                while (!ok && condicion) {
                    mostrarTablero(sistema);

                    Scanner in = new Scanner(System.in);
                    System.out.println("Haz una jugada con el color " + colorTurno + ", apretar 'A' para ayuda o 'X' para terminar partida");
                    String opcionJugada = in.nextLine().toUpperCase();
                    int colJugada = 0;
                    switch (opcionJugada) {

                        case "A":
                            System.out.println("Jugadas validas: " + sistema.ayudaSaltarS(letraColor));
                            break;
                        case "X":
                            condicion = false;
                            break;
                        case "1":
                            colJugada = 4;
                            break;
                        case "2":
                            colJugada = 6;
                            break;
                        case "3":
                            colJugada = 8;
                            break;
                        case "4":
                            colJugada = 10;
                            break;
                    }

                    if (colJugada != 0 && sistema.colJugableSaltarS(letraColor, colJugada)) {
                        sistema.hacerJugadaSaltarS(letraColor, colJugada);
                        ok = true;
                    } else if (colJugada != 0) {
                        System.out.println("Jugada incorrecta");
                    }
                }
            } else {
                coloresNoValidos++;
            }
            if (coloresNoValidos == 4) {
                mostrarTablero(sistema);
                System.out.println("PERDISTE, QUE TRISTE, DAS LASTIMA DE VOS");
                condicion = false;
            }
            if (sistema.ganoSaltarS()) {
                condicion = false;
                mostrarTablero(sistema);
                System.out.println("Felicitaciones, GANASTE!");

            }
            color++;
        }
        int puntaje = sistema.calcularPuntajeSaltarS();
        System.out.println("Su puntaje es: " + puntaje);
        sistema.ponerPuntaje(puntaje);
    }

    private static void jugarRectangulo(Sistema sistema) {
        int color = 0;
        String colores = "RAVM";
        boolean condicion = true;
        int contadorJugadas = 0;
        boolean primeraJugada = true;
        char letraColor = ' ';
        char letraAnterior = ' ';
        while (condicion) {
            if (contadorJugadas != 0) {
                primeraJugada = false;
            }
            if (color == 4) {
                color = 0;
            }

            if (color == 0) {
                letraAnterior = colores.charAt(3);
            } else {
                letraAnterior = colores.charAt(color - 1);
            }
            letraColor = colores.charAt(color);
            boolean ok = false;
            Scanner in = new Scanner(System.in);
            String respuestaAux[]=new String[4];
            while (!ok && condicion) {

                mostrarTableroRectangulo(sistema);
                System.out.println("Haz una jugada");
                String respuesta = in.nextLine();
                String arrayRespuesta[] = respuesta.split(" ");
                if (sistema.jugadaValidaRectanguloS(arrayRespuesta, letraAnterior, primeraJugada)) {
                    sistema.hacerJugadaRectanguloS(arrayRespuesta, letraColor);
                    ok = true;
                    respuestaAux=arrayRespuesta;
                } else {
                    System.out.println("Jugada incorrecta");
                }
            }
            
            if(!sistema.hayJugadaRectanguloS(respuestaAux)){
                condicion=false;
                mostrarTableroRectangulo(sistema);
                System.out.println("Perdiste");
            }
            
            color++;
            contadorJugadas++;
            if(contadorJugadas==10){
                condicion=false;
                mostrarTableroRectangulo(sistema);
                System.out.println("Felicitaciones, GANASTE!");
            }
        }
        int puntaje = sistema.calcularPuntajeRectanguloS();
        System.out.println("Su puntaje es: " + puntaje);
        sistema.ponerPuntaje(puntaje);

    }

    private static Jugador crearJugador(Sistema sistema) {
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese su nombre");
        String nombre = in.nextLine();
        System.out.println("Ingrese edad");
        int edad = in.nextInt();
        String alias = pedirAlias(sistema);
        Jugador jugadorNuevo = new Jugador(nombre, edad, alias);
        System.out.println("Jugador creado correctamente:" + jugadorNuevo);
        return jugadorNuevo;
    }

    public static String pedirAlias(Sistema sistema) {
        boolean ok = false;
        Scanner in = new Scanner(System.in);
        String alias = "S/N";
        while (!ok) {

            System.out.println("Ingrese el alias del siguiente jugador:");
            alias = in.nextLine();
            while (sistema.existeAlias(alias)) {
                System.out.println("Ya existe un jugador con el alias ingresado.");
                alias = in.nextLine();
            }
            ok = true;
        }
        return alias;
    }

    private static void ordenar(Sistema sistema) {
        Scanner in = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("\nORDENAR\n");
            System.out.println("1. Por alias creciente ");
            System.out.println("2. Por puntaje decreciente");
            System.out.println("3. Salir");
            opcion = in.nextInt();
            if (opcion == 1) {
                sistema.ordenarPorAlias();
                mostrarPartidas(sistema);
            } else {
                if (opcion == 2) {
                    sistema.ordenarPorPuntaje();
                    mostrarPartidas(sistema);
                }
            }

        }
    }

    public static void elegirJugador(Sistema sistema) {
        mostrarJugadores(sistema);
        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese el alias del jugador que quiere elegir");
        String alias = in.nextLine();
        while (!sistema.existeAlias(alias)) {
            mostrarJugadores(sistema);
            System.out.println("Ingrese un alias existente");
            alias = in.nextLine();
        }
        Jugador jugador = sistema.buscarJugadorAlias(alias);
        sistema.setearJugador(jugador);
    }

    public static void mostrarPartidas(Sistema sistema) {
        System.out.println("\nLista de Partidas");
        for (Partida unaPartida : sistema.getListaPartidas()) {
            System.out.println(unaPartida);
        }
        System.out.println("");
    }

    public static void mostrarJugadores(Sistema sistema) {
        System.out.println("\nLista de Jugadores");
        for (Jugador jugador : sistema.getListaJugadores()) {
            System.out.println(jugador);
        }
        System.out.println("");
    }

    public static void mostrarTablero(Sistema sistema) {
        Tablero tablero = sistema.getTableroActual();
        String mat[][] = tablero.getMatriz();
        for (int i = mat.length - 1; i >= 0; i--) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println("");
        }
    }

    public static void mostrarTableroRectangulo(Sistema sistema) {
        Tablero tablero = sistema.getTableroActual();
        String mat[][] = tablero.getMatriz();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println("");
        }
    }
}
