/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Semaforodblz;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 *
 * @author red1
 */
public class EstiloHalloween extends DefaultMetalTheme
{
   public String getName() { return "Halloween"; }
   
   private final ColorUIResource primary1 = new ColorUIResource(0, 0, 0);
   private final ColorUIResource primary2 = new ColorUIResource(0, 0, 0);
   private final ColorUIResource primary3 = new ColorUIResource(0,0,0);
   
   protected ColorUIResource getPrimary1() { return primary1; }
   protected ColorUIResource getPrimary2() { return primary2; }
   protected ColorUIResource getPrimary3() { return primary3; }
}
