//Pablo Duran (270956) Santiago Villar (256345) 

package juegosobligatorio;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        //Jugador de prueba
        Jugador jugador = new Jugador("Jorge diaz", 69, "El jorge");
        sistema.agregarJugador(jugador);
        menu(sistema);
    }

    private static void menu(Sistema sistema) {
        Scanner in = new Scanner(System.in);
        int opcion = 0;
        while (opcion != 5) {

            System.out.println("MENU DE OPCIONES\n");
            System.out.println("1) Ingresar un nuevo jugador");
            System.out.println("2) Jugar a Saltar");
            System.out.println("3) Jugar a Rectángulo");
            System.out.println("4) Bitácora");
            System.out.println("5) Salir");
            System.out.println("Seleccione su opción:");
            opcion = pedirNum(1,5);
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
                        preguntarTableroRectangulo(sistema);
                        jugarRectangulo(sistema);
                        sistema.agregarPartidaActual();
                    }
                    break;
                case 4:
                    ordenar(sistema);
                    break;
                default:
                    break;
            }
        }
    }

    private static void preguntarTableroSaltar(Sistema sistema) {
        System.out.println("Ingrese '0' para tablero predeterminado y '1' para tablero al azar");
        int respuesta = pedirNum(0,1);
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
                    String opcionJugada = pedirString("1234AX");
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
                        System.out.println(sistema.mostrarErrorSaltarS(letraColor, colJugada));;
                    }
                }
            } else {
                coloresNoValidos++;
                System.out.println("No hay jugadas con el color: " + colorTurno);
            }
            if (coloresNoValidos == 4) {
                mostrarTablero(sistema);
                System.out.println("Perdiste");
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

    private static void preguntarTableroRectangulo(Sistema sistema) {
        System.out.println("Ingrese '0' para tablero predeterminado y '1' para tablero al azar");
        int respuesta = pedirNum(0,1);
        if (respuesta == 1) {
            sistema.tableroRandomRectanguloS();
        }
    }

    private static void jugarRectangulo(Sistema sistema) {
        int color = 0;
        String colores = "RAVM";
        boolean salir = false;
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
            String respuestaAux[] = new String[4];
            while (!ok && condicion) {

                mostrarTableroRectangulo(sistema);
                System.out.println("Haz una jugada, o aprieta 'X' para finalizar la partida");
                String respuesta = pedirRectangulo().toUpperCase();
                if (respuesta.equals("X")) {
                    condicion = false;
                } else {
                    String arrayRespuesta[] = respuesta.split(" ");
                    if (sistema.jugadaValidaRectanguloS(arrayRespuesta, letraAnterior, primeraJugada)) {
                        sistema.hacerJugadaRectanguloS(arrayRespuesta, letraColor);
                        ok = true;
                        respuestaAux = arrayRespuesta;
                    } else {
                        System.out.println("Jugada incorrecta");
                        System.out.println(sistema.mostrarErrorRectanguloS(arrayRespuesta, letraColor, primeraJugada));
                    }
                }
            }
            if (condicion) {
                if (!sistema.hayJugadaRectanguloS(respuestaAux)) {
                    condicion = false;
                    mostrarTableroRectangulo(sistema);
                    System.out.println("Perdiste");
                }

                color++;
                contadorJugadas++;
                if (contadorJugadas == 10) {
                    condicion = false;
                    mostrarTableroRectangulo(sistema);
                    System.out.println("Felicitaciones, GANASTE!");
                }
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
        if(sistema.getListaPartidas().size()==0){
            System.out.println("No hay partidas.");
            System.out.println("");
        }
        else{
            Scanner in = new Scanner(System.in);
            int opcion = 0;
            while (opcion != 3) {
                System.out.println("\nORDENAR\n");
                System.out.println("1) Por alias creciente ");
                System.out.println("2) Por puntaje decreciente");
                System.out.println("3) Salir");
                opcion = pedirNum(1,3);
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
    }

    public static void elegirJugador(Sistema sistema) {
        mostrarJugadores(sistema);
        System.out.println("Ingrese el número de jugador");
        int respuesta = pedirNum(1,sistema.getListaJugadores().size());
        Jugador jugador = sistema.getListaJugadores().get(respuesta - 1);
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
        for (int i = 0; i < sistema.getListaJugadores().size(); i++) {
            System.out.println((i + 1) + ") " + sistema.getListaJugadores().get(i));
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

    public static int pedirNum(int min, int max) {
        int dato=0;
        Scanner in=new Scanner(System.in);
        boolean ok=false;
        while (!ok) {
            try {
                dato = in.nextInt();
                in.nextLine();
                if (dato < min || dato > max) {
                    System.out.println("Valor fuera de rango ("
                            + min + "-" + max + ")");
                } else {
                    ok = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingrese solo números");
                in.nextLine();
            }
        }
        return dato;
    }
    
    public static String pedirString(String valido){
        Scanner in=new Scanner(System.in);
        boolean ok=false;
        String dato="";
        String aux="";
        for (int i = 0; i < valido.length(); i++) {
            aux+=valido.charAt(i)+" ";
        }
        while (!ok) {
                dato = in.nextLine();
                if(valido.contains(dato.toUpperCase()) && dato.length()==1){
                    ok=true;
                } 
                else {
                    System.out.println("Ingrese un valor valido: "+aux);
                }
        }
        return dato.toUpperCase();
    }
    
    public static String pedirRectangulo(){
        String resp="";
        Scanner in=new Scanner(System.in);
        boolean ok=false;
        boolean sonNums=true;
        String posTablero="1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20";
        while(!ok){
            resp=in.nextLine();
            sonNums=true;
            if(resp.toUpperCase().equals("X")){
                ok=true;
            }
            else{
                String array[]=resp.split(" ");
                for (int i = 0; i < array.length && sonNums; i++) {
                    if(!posTablero.contains(array[i]) || array[i].length()>2){
                        sonNums=false;
                    }
                }
                if(array.length==4 && sonNums){
                    ok=true;
                }
                if(!ok){
                    System.out.println("Ingrese un valor valido. Formato 'y x dy dx' o 'X' para salir");
                }
            }
        }
        
        return resp;
    }
}
