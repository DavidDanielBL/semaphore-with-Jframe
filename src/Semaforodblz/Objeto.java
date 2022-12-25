/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Semaforodblz;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;// (i)
public class Objeto {    
        
   
    
    protected int x, y;  //Coordenadas x y en donde aparecera el mounstro
    protected int ancho , altura;  
    private String [] NombreImagen; //Es para definir el nombre de la imagen a mostrar, 
    protected int Marco_Actual;
    protected int Velo_Marco;
    protected int t;
    protected Escenario escenario; //Carga en donde aparecera el actor 

   
    protected ImagenCache imgcache; //Es para cargar el actor 
    
    protected boolean Marca_Elimina;
	
  
    
    public Objeto(Escenario escenario) { 
        this.escenario = escenario;
        imgcache=escenario.getImagenCache();
        Marco_Actual=0;
        Velo_Marco=1;
        t=0;           
     }
    public void Elimina() {
        Marca_Elimina = true;
      }
     public void accionsinsemaforo() {
       t++;
        if (t % Velo_Marco == 0){
          t=0;
          Marco_Actual = ( Marco_Actual+ 1) % NombreImagen.length;
        }  

    }  
    
    
      public boolean EsMarca_Elimina() {
        return Marca_Elimina;
      }
      
    public void pintar(Graphics2D g)
    { 
        g.drawImage( imgcache.getImagen(NombreImagen[Marco_Actual]),x,y,escenario);
        //metodo que sirve para dibujar para ello se necesita el nombre ,coordenadas y en donde aparecera (escenario)  
    }
    public int getX() {return x; }
    public void setX(int i) { x = i;}
    public int getY() { return y; }
    public void setY(int i) {y = i;}
    
    public void setNombreImagen(String[] nombrenuevo)
    {    	
        NombreImagen = nombrenuevo;
        ancho = 0;
        altura=0;
        for (int i = 0; i < nombrenuevo.length; i++ )
        {
        BufferedImage image = imgcache.getImagen(NombreImagen[i]); 
        altura = Math.max(altura, image.getHeight());                   
        ancho = Math.max(ancho, image.getWidth());                           
     }
    }
    public int getAltura() { return altura;   }
    public int getAncho() {   return ancho;  }

    public void setAncho(int i) { ancho =i;   }
    public void setAltura(int i) { altura = i; }
    
    
    
    public int getVelo_Marco() {return Velo_Marco;  }
    public void setVelo_Marco(int i) {Velo_Marco = i; }
       
    public void accion() {
        t++;
        if (t % Velo_Marco == 0){
          t=0;
          Marco_Actual = ( Marco_Actual+ 1) % NombreImagen.length;
        }
      }
    
    public Rectangle Limite () {
        return new Rectangle(x,y,ancho,altura);
      }
      
    public void Llegada(Objeto a){
        
    }
	
       
}
