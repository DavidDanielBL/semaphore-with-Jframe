/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Semaforodblz;

public class Semaforo extends Objeto
{
    public Semaforo(Escenario escenario) 
    {
        super(escenario);
     
        setNombreImagen(new String[] {"semaforo.jpg"});
        setVelo_Marco(35);
    }
      
    public void accion() {
        super.accion();
      
      }
   
    
  
      
}
