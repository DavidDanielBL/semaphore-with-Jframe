/*
 * ResourceCache.java
 *
 * Created on 16 de abril de 2008, 15:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Semaforodblz;

/**
 *
 * @author richard
 */
import java.net.URL;
import java.util.HashMap;

public abstract class ResourceCache {
    protected HashMap resources;
    
    public ResourceCache() {
        resources = new HashMap();
    }
    
    protected Object loadResource(String name) {
        URL url=null;
        url = getClass().getClassLoader().getResource(name);
        return loadResource(url);
    }
    
    protected Object getResource(String name) {
        Object res = resources.get(name);
        if (res == null) {
            res = loadResource("Imagenes/"+name);
            resources.put(name,res);
        }
        return res;
    }
    
    protected abstract Object loadResource(URL url);
    
}


