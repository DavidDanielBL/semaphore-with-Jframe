/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Semaforodblz;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/**
 *
 * @author red1
 */
public class SonidoCache  extends ResourceCache{
    
    
    
    protected Object loadResource(URL url) {
        return Applet.newAudioClip(url);
        
    }
    public AudioClip getAudioClip(String name) {
        return (AudioClip)getResource(name);
    }
    
    
    public void playSound(final String name) {
        new Thread(
                new Runnable() {
            public void run() {
                getAudioClip(name).play();
            }
        }
        ).start();
    }
    
    public void loopSound(final String name) {
        new Thread(
                new Runnable() {
            public void run() {
                getAudioClip(name).loop();
            }
        }
        ).start();  }
    
    
    
    
    
    
    
    

}
