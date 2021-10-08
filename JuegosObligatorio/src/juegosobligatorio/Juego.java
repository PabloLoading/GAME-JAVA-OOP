
package juegosobligatorio;

public class Juego {
    private Tablero tablero;
    
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
        mat[23][0]="60";
        mat[21][0]="40";
        mat[19][0]="30";
        mat[17][0]="20";
        mat[15][0]="10";
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
        
        this.tablero.setMatriz(mat);
    }
    
    public Tablero getTablero() {
        return tablero;
    }
    
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    
}
