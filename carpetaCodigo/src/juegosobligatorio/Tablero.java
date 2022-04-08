//Pablo Duran (270956) Santiago Villar (256345) 

package juegosobligatorio;

public class Tablero {
    private int filas;
    private int cols;
    private char valorDefecto;
    private String[][] matriz;

    public char getValorDefecto() {
        return valorDefecto;
    }

    public String[][] getMatriz() {
        return matriz;
    }
    public void setMatriz(String[][] matriz){
        this.matriz=matriz;
    }

    public Tablero(int filas, int cols, char valor) {
        this.filas = filas;
        this.cols = cols;
        this.matriz = new String[filas][cols];

        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.matriz[i][j] = valor+"";
            }
        }

    }

   

    //Metodos get
    public int getFilas() {
        return filas;
    }

    public int getCols() {
        return cols;
    }
}
