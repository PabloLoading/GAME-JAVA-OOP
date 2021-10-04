
package juegosobligatorio;

class Juego {
    private Tablero tablero;
    
    
    
    class Tablero{
        int filas;
        int cols;
        char valorDefecto;
        char[][] matriz;

        
        public Tablero(int filas, int cols,char valor){
            this.filas=filas;
            this.cols=cols;
            this.matriz=new char[filas][cols];
            
            for (int i = 0; i < this.filas; i++) {
                for (int j = 0; j < this.cols; j++) {
                    matriz[i][j]=valor;
                }
            } 
       
        }
    
        public void mostrarTablero(Tablero this){
            for (int i = 0; i < this.filas; i++) {
                System.out.println("");
                for (int j = 0; j < this.cols; j++) {
                    System.out.print(matriz[i][j]);
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
}
