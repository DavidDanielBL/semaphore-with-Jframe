/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package Semaforodblz;


public class datobuffer extends Objeto {
      protected static final int Velocidad_Salida_Dato=7;
      
      public datobuffer(Escenario escenario) {
        super(escenario);
        setNombreImagen( new String[] {"datos.jpg"});
        
        setVelo_Marco(10);
      }
      
      public void accion() {
        
        //  super.accion();
      
      
        x+=Velocidad_Salida_Dato;
        if(x>=700)
         { Elimina();
         }
        if (y >Escenario.Buffer_Alto)
          Elimina();
                
         //escenario.getSonidoCache().playSound("lazer.wav");
      }
        public void accionsinsemaforo() {
        
        //  super.accion();
      
      
        x+=Velocidad_Salida_Dato;
        if(x>=700)
         { Elimina();
         }
        if (y >Escenario.Buffer_Alto)
          Elimina();
                
         //escenario.getSonidoCache().playSound("lazer.wav");
      }
    }
