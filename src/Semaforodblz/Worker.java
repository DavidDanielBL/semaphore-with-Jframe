/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Semaforodblz;

/**
 *
 * @author red
 */
public class Worker implements Runnable {

    private Semaphore sem;
    private String name;
    public boolean ocupado;

    public boolean isLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    public boolean libre;
    

    public Worker(Semaphore sem, String name) {
        this.name = name;
        this.sem = sem;
    }

    public Worker() {
    }

    public void run() {
        while (true) {
            sem.acquire();
            ExclusionMutua.criticalSection(name);
            //ExclusionMutua.critica() ;
             System.out.println(" Estassss en zona critica");
             ocupado=true;
             setOcupado(true);
             if (ocupado==true)
             System.out.println("ocupado");
              
            sem.release();

            ExclusionMutua.nonCriticalSection(name);
            //ExclusionMutua.nocritica() ;
             System.out.println(" NO OOO  Estassss en zona critica");
            libre=true;
            setLibre(true);
         if (libre==true)
            System.out.println("libre");
        }
    }
}
