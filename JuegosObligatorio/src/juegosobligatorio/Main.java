package juegosobligatorio;
import java.util.*;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Sistema sistema=new Sistema();
        Scanner in = new Scanner(System.in);
        int opcion=0;
        while(opcion!=5){
            
            System.out.println("MENU DE OPCIONES\n");
            System.out.println("1. Ingresar un nuevo jugador");
            System.out.println("2. Jugar a Saltar");
            System.out.println("3. Jugar a Rectángulo");
            System.out.println("4. Bitácora");
            System.out.println("5. Salir");
            System.out.println("Seleccione su opción");
            opcion=in.nextInt();
            switch(opcion){
                case 1:
                    sistema.ingresarJugador(agregarJugadorALista(sistema));
                break;
                case 2:
                    /*jugarSaltar*/;
                break;
                case 3:
                    /*jugarRectangulo*/;
                break;
                case 4:
                    ordenar(sistema);
                break;
                case 5:
                break;}
        }
    }
    
    private static Jugador agregarJugadorALista(Sistema sistema){
        Scanner in =new Scanner (System.in);
        String nombre=pedirNombre(sistema);
        int edad=in.nextInt();
        String alias=pedirAlias(sistema);
        Jugador jugadorNuevo=new Jugador(nombre,edad,alias);
        return jugadorNuevo;
    }
    private static String pedirNombre(Sistema sistema){
    boolean ok=false;
    Scanner in =new Scanner (System.in);
    String nombre="S/N";
    while(!ok){
        
            System.out.println("Ingrese el nombre del siguiente jugador:");
        nombre=in.nextLine();
        if(nombreRepetido(nombre,sistema)){
            System.out.println("Ya existe un jugador con el nombre ingresado.");
        }else{
                ok=true;}
        

    }
            return nombre;}
    
    public static String pedirAlias(Sistema sistema){
            boolean ok=false;
    Scanner in =new Scanner (System.in);
    String alias="S/N";
    while(!ok){
        
            System.out.println("Ingrese el alias del siguiente jugador:");
        alias=in.nextLine();
        if(aliasRepetido(alias,sistema)){
            System.out.println("Ya existe un jugador con el alias ingresado.");
        }else{
                ok=true;}       
    }
            return alias;
    }
    
    private static boolean nombreRepetido(String Nombre,Sistema sistema){
        Iterator<Jugador> it= sistema.getListaJugadores().iterator();
        boolean repetido=false;
            while(it.hasNext()){
                if(it.next().getNombre()==Nombre){
                    repetido=true;
                }
            }
    return repetido;}
    

    private static boolean aliasRepetido(String Alias,Sistema sistema){
        Iterator<Jugador> it= sistema.getListaJugadores().iterator();
        boolean repetido=false;
            while(it.hasNext()){
                if(it.next().getAlias()==Alias){
                    repetido=true;
                }
            }
    return repetido;}
    
    private static void ordenar(Sistema sistema){
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
                sistema.mostrarPartidas();
            } else {
                if (opcion == 2) {
                    sistema.ordenarPorPuntaje();
                    sistema.mostrarPartidas();
                }
            }
        }}
    
    
    }

