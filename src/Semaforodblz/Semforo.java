/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Semaforodblz;

/**
 *
 * @author red
 */
public class Semforo {
    
    
    public void smgforo(){
    

      Semaphore sem = new Semaphore(1);

      Thread[] bees = new Thread[5];

      for (int i = 0; i < 5; i++)
         bees[i] = new Thread(new Worker(sem, "Worker " + (new Integer(i)).toString() ));

      for (int i = 0; i < 5; i++)
         bees[i].start();
   
    
    }

}
