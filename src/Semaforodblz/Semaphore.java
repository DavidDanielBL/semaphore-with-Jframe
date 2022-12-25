/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Semaforodblz;

/**
 *
 * @author red
 */
public class Semaphore
{
   private int value;

   public Semaphore(int value) {
      this.value = value;
   }

   public synchronized void acquire() {
      while (value <= 0) {
         try {
            wait();
         }
         catch (InterruptedException e) { }
      }

      value--;
   }

   public synchronized void release() {
      ++value;

      notify();
   }
}

