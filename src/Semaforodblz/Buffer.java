package Semaforodblz;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.util.concurrent.Semaphore;

public class Buffer extends Objeto {

    public static final int MAX_ALMACEN = 200;
    private int Zonacritica;
    private BufferedImage Fondo;
    private ImagenCache imagencache;
    private Graphics2D g;
    protected int vy,  c = 0;
    private int velocidadx;
    private int velocidady;
    protected int vx;
    protected double Frecuencia_Salida = 0.01;
    protected static Semaphore oFinP1,  oFinP3;
    private int vez = 1;
    public Thread hilo;
    public boolean band;

    public Buffer(Escenario escenario) {
        super(escenario);
        setNombreImagen(new String[]{"buffer.png"});

        Zonacritica = MAX_ALMACEN;




    }

    @SuppressWarnings("static-access")
    @Override
    public void accion() {

       // super.accion();

        x += vx;
        if (x < 10 || x > (Escenario.Ancho - 100)) {
            vx = -vx;
        }

    //  if (Math.random()< Frecuencia_Salida)
     //  salidadedatos();


///   System.out.println(Math.random());
    }

    public int getVelocidadx() {
        return velocidadx;
    }

    public void setVelocidadx(int i) {
        vx = i;
    }

    public int getVelocidady() {
        return velocidady;
    }

    public void setVelocidady(int i) {
        vy = i;
    }

    public int getZonacritica() {
        return Zonacritica;
    }

    public void setZonacritica(int i) {
        Zonacritica = i;
    }

    public void addZonacritica(int i) {
        Zonacritica = Zonacritica + i;
    //if (Escudos> MAX_Escudos)Escudos = MAX_Escudos;
    }

    
    

        
            
            
            public void Llegada(Objeto a) {
         
 if (a instanceof datoproductor) {
            //   poner musica xD escenario.getSonidoCache().playSound("bb.wav"); 
            a.Elimina();
            addZonacritica(-30);

        }

 


    }

    public void salidadedatos() {

         datobuffer m = new datobuffer(escenario);
         m.setX(x + 400);
        m.setY(y -100);
addZonacritica(30);
        escenario.addActor(m);



    }
}

