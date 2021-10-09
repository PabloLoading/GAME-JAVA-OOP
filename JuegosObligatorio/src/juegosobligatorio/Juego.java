
package juegosobligatorio;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Juego {
    private Tablero tablero;
    private String nombre;
    
    //Creacion de metodos que dan fichas de colores
    public static final String fichaRoja = "\u001B[31m"+"#"+"\u001B[0m";
    public static final String fichaAzul = "\u001B[34m"+"#"+"\u001B[0m";
    public static final String fichaVerde = "\u001B[32m"+"#"+"\u001B[0m";
    public static final String fichaAmarilla = "\u001B[33m"+"#"+"\u001B[0m";

    
    public void tableroDefaultSaltar(){
        String mat[][]=this.tablero.getMatriz();
        
        //Creacion de fichas
        mat[2][4]=fichaAmarilla;
        mat[2][6]=fichaVerde;
        mat[2][8]=fichaRoja;
        mat[2][10]=fichaAzul;
        
        mat[4][4]=fichaVerde;
        mat[4][6]=fichaAmarilla;
        mat[4][8]=fichaAzul;
        mat[4][10]=fichaRoja;
        
        mat[6][4]=fichaAzul;
        mat[6][6]=fichaRoja;
        mat[6][8]=fichaAmarilla;
        mat[6][10]=fichaVerde;
        
        mat[8][4]=fichaRoja;
        mat[8][6]=fichaAzul;
        mat[8][8]=fichaVerde;
        mat[8][10]=fichaAmarilla;
        
        //espacio doble en col de los puntajes, asi printea prolijo
        for (int i = 0; i < mat.length; i++) {
            mat[i][0]="  ";
        }
        //columnas indicadas
        mat[0][4]="1";
        mat[0][6]="2";
        mat[0][8]="3";
        mat[0][10]="4";
        //Ponemos puntajes
        mat[22][0]="60";
        mat[20][0]="40";
        mat[18][0]="30";
        mat[16][0]="20";
        mat[14][0]="10";
        //Dibujamos las lineas del tablero
        for (int i=1; i < mat.length; i++) {
            for (int j = 3; j < mat[0].length; j++) {
                if(i%2!=0){
                    if(j%2!=0){
                        mat[i][j]="+";
                    }
                    else mat[i][j]="-";
                }
                else{
                    if(j%2!=0){
                        mat[i][j]="|";
                    }
                }
            }
        }
        //Setea a la matriz del tablero
        this.tablero.setMatriz(mat);
    }
    
    public void tableroRandomSaltar(){
        String mat[][]=this.tablero.getMatriz();
        
        Random rn = new Random();
        int n = maximum - minimum + 1;
        int i = rn.nextInt() % n;
        randomNum =  minimum + i;
        
        
        this.tablero.setMatriz(mat);
    }
    
    public Tablero getTablero() {
        return tablero;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setTablero(Tablero tablero) 
    {
        this.tablero = tablero;
    }
    public String getNombre(){
        return this.nombre;
    }
    
    public int calcularPuntajeSaltar(){
        String mat[][]=this.tablero.getMatriz();
        int puntaje=0;
        for (int i = 14; i < 23; i=i+2) {
            int valorFila=Integer.parseInt(mat[i][0]);
            for (int j = 4; j <11; j=j+2) {
                if(!" ".equals(mat[i][j])){
                    puntaje+=valorFila;
                }
            }
        }
        return puntaje;
    }
            
    public boolean jugadaValidaSaltar(int posY, int posX){
        String mat[][]=this.tablero.getMatriz();
        boolean sePuede=true;
        int fichasFila=4;
        for (int j = 4; j < mat[0].length; j=j+2) {
            if(" ".equals(mat[posY][j])){
                fichasFila--;
            }
        }    
        if((posY+fichasFila*2)<=22 && " ".equals(mat[posY+fichasFila*2][posX])){
            for (int j = 4; j < mat[0].length; j=j+2) {
                if(mat[posY][posX].equals(mat[posY+fichasFila*2][j])){
                    sePuede=false;
                }
            } 
        }
        else{
            sePuede=false;
        }
        if(fichasFila==1 && sePuede){

            boolean todosEspacios=true;
            for (int i = posY+2; i < mat.length; i=i+2) {
                for (int j = 4; j < mat[0].length; j=j+2) {
                    if(!" ".equals(mat[i][j])){
                        todosEspacios=false;
                    }
                }
            }
            if(todosEspacios){
                sePuede=false;
            }
        }
        return sePuede;
    }
    
    public boolean colJugableSaltar(char letraColor,int col){
        boolean colJugable=false;
        
        String mat[][]=this.tablero.getMatriz();
        String aux="";
        switch(letraColor){
            case'R':aux=fichaRoja;
            break;
            case 'A':aux=fichaAzul;
            break;
            case 'V':aux=fichaVerde;
            break;
            case 'M':aux=fichaAmarilla;
            break;
        }
        for (int i = 0; i < mat.length; i++) {
            if(mat[i][col].equals(aux)){
                colJugable=jugadaValidaSaltar(i,col);
            }
        }
        return colJugable;
    }
    
    public boolean colorJugableSaltar(char letraColor){
        String mat[][]=this.tablero.getMatriz();
        boolean colorJugable=false;
        for (int j = 4; j < mat[0].length; j=j+2) {
            if(colJugableSaltar(letraColor,j)){
                colorJugable=true;
            }
        }
        return colorJugable;
    }
    
    public String ayudaSaltar(char letraColor){
        String retorno="";
        if(colJugableSaltar(letraColor,4)){
            retorno+="1 ";
        }
        if(colJugableSaltar(letraColor,6)){
            retorno+="2 ";
        }
        if(colJugableSaltar(letraColor,8)){
            retorno+="3 ";
        }
        if(colJugableSaltar(letraColor,10)){
            retorno+="4 ";
        }
        return retorno;
    }
    
    public void hacerJugadaSaltar(char letraColor,int col){
        String mat[][]=this.tablero.getMatriz();
        String aux="";
        int fichasFila=4;
        int posY=0;
        switch(letraColor){
            case'R':aux=fichaRoja;
            break;
            case 'A':aux=fichaAzul;
            break;
            case 'V':aux=fichaVerde;
            break;
            case 'M':aux=fichaAmarilla;
            break;
        }
        for (int i = 0; i < mat.length; i++) {
            if(mat[i][col].equals(aux)){
                posY=i;
            }
        }
        for (int j = 4; j < mat[0].length; j=j+2) {
            if(" ".equals(mat[posY][j])){
                fichasFila--;
            }
        }
        
        mat[posY+fichasFila*2][col]=aux;
        mat[posY][col]=" ";
        
        this.tablero.setMatriz(mat);
    }
    //Fijarse si esta bien
    public boolean ganoSaltar(){
       String mat [][]= this.tablero.getMatriz();
       boolean gano=false;
       int espaciosVacios=0;
       for (int i =2 ; i < 9; i=i+2) {
           for (int j = 4; j < 11; j=j+2) {
               if(" ".equals(mat[i][j])){
                   espaciosVacios++;
               }
               
           }
       }
       if(espaciosVacios==14){
           gano = true;
       }
        return gano;
    }
}
