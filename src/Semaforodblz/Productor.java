/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Semaforodblz;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author red1
 */
public class Productor extends Objeto {

    public static final int MAX_ALMACEN = 200;
    private int Zonacritica;
    protected int vx;
    protected int vy;
    //  private int vez = 1;
    private int velocidadx,  cont = 0,  conta = 0;
    private int velocidady;
    public boolean estadoaccion;
    protected static Semaphore oFinP1,  oFinP3;
    public boolean productorA;
    protected double Frecuencia_Salida = 0.01;
    protected double Frecuencia_Salida2 = 0.01;
    public Worker worker;

    public Productor(Escenario escenario) {
        super(escenario);

        setNombreImagen(new String[]{"productor.jpg"});
        setVelo_Marco(35);
        Zonacritica = MAX_ALMACEN;
        //  super.accion();

        worker = new Worker();



    }

    public void accion() {

       
        generacion();


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

    public void salidadedatos() {
        datoproductor m = new datoproductor(escenario);
        m.setX(x + getAncho() / 2 + 60);
        m.setY(y + getAltura() - 70);

        escenario.addActor(m);
    // addZonacritica(-30);


    }

    public void salidadedatos2() {

        datobuffer m = new datobuffer(escenario);
        m.setX(x + 400);
        m.setY(y - 100);
        addZonacritica(+30);
        escenario.addActor(m);



    }

    public void Llegada(Objeto a) {

        if (a instanceof datoproductor) {
            //   poner musica xD escenario.getSonidoCache().playSound("bb.wav"); 
            a.Elimina();
            addZonacritica(-30);

        }




    }

    public boolean ocupado() {

        return worker.isOcupado();

    }

    public boolean libre() {
        return worker.isLibre();
    }

    public void generacion() {
        if (Math.random() < Frecuencia_Salida) {
            cont = cont + 1;
            salidadedatos();
            if (cont >= 5) {
                Frecuencia_Salida = -1;
            }

        }

        if (Math.random() < Frecuencia_Salida2 && cont >= 5) {
            conta = conta + 1;


            salidadedatos2();

            if (conta >= 4) {
                Frecuencia_Salida2 = -1;
            }
        }
    }

    public void accionsinsemaforo() {
       // if (Math.random() < Frecuencia_Salida) 
            
       //     salidadedatos();
        if (Math.random() < Frecuencia_Salida) 
            
        
        {    salidadedatos2();
        salidadedatos();
        }
    }
}

