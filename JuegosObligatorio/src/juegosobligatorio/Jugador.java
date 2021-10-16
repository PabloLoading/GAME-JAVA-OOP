//Pablo Duran (270956) Santiago Villar (256345) 

package juegosobligatorio;

class Jugador {
    private String nombre;
    private int edad;
    private String alias;
    
    
    public String getNombre(){
        return this.nombre;
    }
    
    private void setNombre(String unNombre){
        this.nombre=unNombre;
    }
    
    public int getEdad(){
         return this.edad;
    }
    
    private void setEdad (int unaEdad){
    this.edad=unaEdad;
    }
    
    public String getAlias(){
        return this.alias;}
    
    private void setAlias(String unAlias){
        this.alias=unAlias;
    }
    
    public Jugador(String unNombre,int unaEdad,String unAlias){
        this.setNombre(unNombre);
        this.setEdad(unaEdad);
        this.setAlias(unAlias);
    }
    
    
    
    @Override
    public String toString() {
        return "Nombre: " + this.getNombre() + " Edad: "+
            this.getEdad() +" Alias: "+ this.getAlias();
    }
}
