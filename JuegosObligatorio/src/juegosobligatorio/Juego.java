
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
    
    
    //Metodos set y get
    
    public Tablero getTablero() {
        return tablero;
    }
    
    public void setTablero(Tablero tablero) 
    {
        this.tablero = tablero;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    //Metodos utiles
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    public String getFicha(char letraColor){
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
        return aux;
    }
    
    
    //Metodos Saltar
    
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
        
        int ordenFilas[]=new int[4];
        
        for (int j = 0; j < 4; j++) {
            boolean condicion=true;
            while(condicion){
                
                int randomNum = this.getRandomNumber(1,5);
                
                boolean estaNum=false;
                for (int k = 0; k < ordenFilas.length; k++) {
                    if(ordenFilas[k]==randomNum*2){
                        estaNum=true;
                    }
                }
                if(!estaNum){
                    condicion=false;
                    ordenFilas[j]=randomNum*2;
                }
            }
        }
        int ordenCols[]=new int[4];
        
        for (int j = 0; j < 4; j++) {
            boolean condicion=true;
            while(condicion){
                
                int randomNum = this.getRandomNumber(2,6);
                
                boolean estaNum=false;
                for (int k = 0; k < ordenCols.length; k++) {
                    if(ordenCols[k]==randomNum*2){
                        estaNum=true;
                    }
                }
                if(!estaNum){
                    condicion=false;
                    ordenCols[j]=randomNum*2;
                }
            }
        }
        //Creacion de fichas
        mat[ordenFilas[0]][ordenCols[0]]=fichaAmarilla;
        mat[ordenFilas[0]][ordenCols[1]]=fichaVerde;
        mat[ordenFilas[0]][ordenCols[2]]=fichaRoja;
        mat[ordenFilas[0]][ordenCols[3]]=fichaAzul;
        
        mat[ordenFilas[1]][ordenCols[0]]=fichaVerde;
        mat[ordenFilas[1]][ordenCols[1]]=fichaAmarilla;
        mat[ordenFilas[1]][ordenCols[2]]=fichaAzul;
        mat[ordenFilas[1]][ordenCols[3]]=fichaRoja;
        
        mat[ordenFilas[2]][ordenCols[0]]=fichaAzul;
        mat[ordenFilas[2]][ordenCols[1]]=fichaRoja;
        mat[ordenFilas[2]][ordenCols[2]]=fichaAmarilla;
        mat[ordenFilas[2]][ordenCols[3]]=fichaVerde;
        
        mat[ordenFilas[3]][ordenCols[0]]=fichaRoja;
        mat[ordenFilas[3]][ordenCols[1]]=fichaAzul;
        mat[ordenFilas[3]][ordenCols[2]]=fichaVerde;
        mat[ordenFilas[3]][ordenCols[3]]=fichaAmarilla;
        
        this.tablero.setMatriz(mat);
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
            if(posY<9){
                for (int j = 4; j < mat[0].length; j=j+2) {
                    if(mat[posY][posX].equals(mat[posY+fichasFila*2][j])){
                        sePuede=false;
                    }
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
        String aux=getFicha(letraColor);
        for (int i = 0; i < mat.length; i++) {
            if(mat[i][col].equals(aux)){
                colJugable=jugadaValidaSaltar(i,col);
            }
        }
        return colJugable;
    }
    
    public String mostrarErrorSaltar(char letraColor, int col){
        String mensaje="Errores: ";
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
        int posY=0;
        for (int i = 0; i < mat.length; i++) {
            if(mat[i][col].equals(aux)){
                posY=i;
            }
        }
        
        int fichasFila=4;
        for (int j = 4; j < mat[0].length; j=j+2) {
            if(" ".equals(mat[posY][j])){
                fichasFila--;
            }
        }    
        boolean seVa=false;
        if((posY+fichasFila*2)>22){
            mensaje+=" \n-Destino fuera del tablero";
            seVa=true;
        }
        if(!seVa && !" ".equals(mat[posY+fichasFila*2][col])){
            mensaje+=" \n-Destino ocupado";
        }
        
        boolean fichasIgualesEnFila=false;
        if (posY < 9) {
            for (int j = 4; j < mat[0].length; j = j + 2) {
                if (mat[posY][col].equals(mat[posY + fichasFila * 2][j])) {
                    fichasIgualesEnFila=true;
                }
            }
        }
        if(fichasIgualesEnFila){
            mensaje+=" \n-2 fichas en misma fila en área base";
        }
        
        if(fichasFila==1){
            boolean todosEspacios=true;
            for (int i = posY+2; i < mat.length; i=i+2) {
                for (int j = 4; j < mat[0].length; j=j+2) {
                    if(!" ".equals(mat[i][j])){
                        todosEspacios=false;
                    }
                }
            }
            if(todosEspacios){
                mensaje+=" \n-Ficha más adelantada se mueve una posición";
            }
        }
        return mensaje;
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
        String aux=getFicha(letraColor);
        int fichasFila=4;
        int posY=0;
        
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
    
    //Metodos Rectangulo
    
    public void tableroDefaultRectangulo(){
        String mat[][]=this.tablero.getMatriz();
        
        //Pone '-' en el tablero
        for (int i = 2; i < mat.length; i++) {
            for (int j = 2; j < mat[0].length; j=j+2) {
                mat[i][j]="-";
            }
        }
        
        mat[0][0]="  ";
        mat[1][0]="  ";
        
        //Pone valores de la izq
        for (int i = 2; i < mat.length; i++) {
            String num=(i-1)+"";
            if(i-1<10){
                num="0"+num;
            }
            mat[i][0]=num;
        }
        //Nums de arriba
        
        int suma=0;
        for (int j = 2; j < 20; j=j+2) {
            suma++;
            mat[0][j]=suma+"";
            
        }
        suma=0;
        for (int j = 20; j <40; j=j+2) {
            mat[1][j]=suma+"";
            suma++;
        }
        for (int j = 20; j < 40; j=j+2) {
            mat[0][j]=1+"";
        }
        mat[0][40]="2";
        mat[1][40]="0";
        
        //Poner '*' en el tablero
        mat[2][6]="*";
        mat[4][6]="*";
        mat[5][10]="*";
        mat[7][32]="*";
        mat[10][20]="*";
        mat[10][22]="*";
        mat[13][20]="*";
        mat[13][40]="*";
        mat[14][36]="*";
        mat[15][34]="*";
        mat[16][12]="*";
        mat[16][22]="*";
        mat[16][40]="*";
        mat[17][34]="*";
        mat[18][10]="*";
        mat[19][8]="*";
        mat[19][22]="*";
        mat[20][6]="*";
        mat[20][28]="*";
        mat[21][30]="*";
        
        this.tablero.setMatriz(mat);
    }

    public void tableroRandomRectangulo(){
        String mat[][]=this.tablero.getMatriz();
        for (int i = 2; i < mat.length; i++) {
            for (int j = 2; j < mat[0].length; j=j+2) {
                mat[i][j]="-";
            }
        }
        for (int j = 0; j < 20; j++) {
            boolean ok = false;
            while(!ok){
            int filaRandom=getRandomNumber(2,22);
            int columnaRandom=getRandomNumber(1, 21);
            if(mat[filaRandom][columnaRandom*2].equals("-")){
                ok=true;
                mat[filaRandom][columnaRandom*2]="*";
            }
        }
            
        }
        this.tablero.setMatriz(mat);
    }
    
    public boolean jugadaValidaRectangulo(String respuesta[],char letraColor,boolean primeraJugada){
        boolean jugadaValida=true;
        String mat[][]=this.tablero.getMatriz();
        
        int posY=Integer.parseInt(respuesta[0])+1;
        int posX=Integer.parseInt(respuesta[1])*2;
        int dy=Integer.parseInt(respuesta[2]);
        int dx=(Integer.parseInt(respuesta[3])-1)*2;
        
        
        for (int i = posY; i<posY+dy; i++) {
            for (int j = posX; j<=posX+dx; j=j+2) {
                if(!"-".equals(mat[i][j])){
                    jugadaValida=false;
                }
                
            }
        }
        String aux=getFicha(letraColor);
        if(jugadaValida && !primeraJugada){
            boolean enContacto=false;
            for (int i = posY-1 ; i <posY+dy+1 && i<mat.length ; i++) {
                for (int j = posX-2; j <=posX + dx+2 && j<mat[0].length; j++) {
                    
                    if(mat[i][j].equals(aux)){
                        enContacto=true;
                    }
                }
            }
            if(!enContacto){
                jugadaValida=false;
            }
        }
        
        return jugadaValida;
    }
 
    public String mostrarErrorRectangulo(String respuesta[],char letraColor,boolean primeraJugada){
        String mensaje="Errores: ";
        String mat[][]=this.tablero.getMatriz();
        boolean tocaAsterisco=false;
        boolean tocaRectangulo=false;
        int posY=Integer.parseInt(respuesta[0])+1;
        int posX=Integer.parseInt(respuesta[1])*2;
        int dy=Integer.parseInt(respuesta[2]);
        int dx=(Integer.parseInt(respuesta[3])-1)*2;
        
        
        for (int i = posY; i<posY+dy; i++) {
            for (int j = posX; j<=posX+dx; j=j+2) {
                if(!"-".equals(mat[i][j])){
                    if(mat[i][j].equals("*") && !tocaAsterisco){
                        tocaAsterisco=true;
                        mensaje+=" \n-El rectángulo ingresado superpone a celda no válida";
                    }else if(!tocaRectangulo){
                        mensaje+="\n-El rectángulo ingresado superpone a otro rectángulo";
                        tocaRectangulo=true;

                    }
                }
                
            }
        }
        String aux=getFicha(letraColor);
        if(!primeraJugada){
            boolean enContacto=false;
            for (int i = posY-1 ; i <posY+dy+1 && i<mat.length ; i++) {
                for (int j = posX-2; j <=posX + dx+2 && j<mat[0].length; j++) {
                    
                    if(mat[i][j].equals(aux)){
                        enContacto=true;
                    }
                }
            }
            if(!enContacto){
                mensaje+=" \n-El rectángulo ingresado no es adyacente al último rectángulo";
            }
        }
        return mensaje;
    }
    
    public void hacerJugadaRectangulo(String respuesta[],char letraColor){
        String mat[][]=this.tablero.getMatriz();
        String aux=getFicha(letraColor);
        
        int posY=Integer.parseInt(respuesta[0])+1;
        int posX=Integer.parseInt(respuesta[1])*2;
        int dy=Integer.parseInt(respuesta[2]);
        int dx=(Integer.parseInt(respuesta[3])-1)*2;
        
        
        for (int i = posY; i <posY+dy; i++) {
            for (int j = posX; j <=posX+dx; j=j+2) {
                mat[i][j]=aux;
            }
        }
        this.tablero.setMatriz(mat);
    }
    
    public int calcularPuntajeRectangulo(){
        String mat[][]=this.tablero.getMatriz();
        int puntaje=0;
        for (int i = 2; i < mat.length; i++) {
            for (int j = 2; j < mat[0].length; j=j+2) {
                if(mat[i][j].equals(fichaRoja) || mat[i][j].equals(fichaAzul) ||
                        mat[i][j].equals(fichaVerde)|| mat[i][j].equals(fichaAmarilla) ){
                    puntaje++;
                }
            }
        }
        return puntaje;
    }
    
    public boolean hayJugadaRectangulo(String respuesta[]){
        String mat[][]=this.tablero.getMatriz();
        boolean hayJugada=false;
        int posY=Integer.parseInt(respuesta[0])+1;
        int posX=Integer.parseInt(respuesta[1])*2;
        int dy=Integer.parseInt(respuesta[2]);
        int dx=(Integer.parseInt(respuesta[3])-1)*2;
        for (int i = posY-1;i<mat.length && i < posY+dy+1; i++) {
            for (int j = posX-2;j<mat[0].length  && j <=posX+dx+2; j++) {
                if("-".equals(mat[i][j])){
                    hayJugada=true;
                }
            }
        }
        return hayJugada;
    }
}

