package Semaforodblz;

import java.awt.image.ImageObserver;// ke hay dentro de imageobserver (i)

public interface Escenario extends ImageObserver // la clase escenario derivada de la clase interna de java ImageObserver
{
public static final int Ancho=1024	;
public static final int  Altura=800;
public static final int Velocidad=10;//velocidad de reproduccion
public ImagenCache getImagenCache();

public static final int Buffer_Alto = Altura-80;																//Metodo de se implementara en otra clase
public void addActor(Objeto a);
public Buffer getJugador();

public SonidoCache getSonidoCache();
                                   

}
