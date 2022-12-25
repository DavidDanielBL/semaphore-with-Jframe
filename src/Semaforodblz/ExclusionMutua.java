/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Semaforodblz;

public class ExclusionMutua
{
   /**
    * critical and non-critical sections are simulated by sleeping
    * for a random amount of time between 0 and 3 seconds.
    */
   public static void criticalSection(String name) {
      
       SleepUtilities.nap(3);
      System.out.println(name + "y");
   }

   public static void nonCriticalSection(String name) {
  
      
      SleepUtilities.nap(3);
       System.out.println(name + "n");
   }
   
 
   
        
 
   
}
