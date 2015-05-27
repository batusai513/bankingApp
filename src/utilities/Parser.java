/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.Hashtable;

/**
 *
 * @author andresroberto
 */
public class Parser {
    public static Hashtable<String, String> getParams(String cadena) {
        Hashtable<String, String> params = new Hashtable<>();
        
        String[] paramsArray = cadena.split("&");
        
        for (int i = 0; i < paramsArray.length; i++) {
            String[] attrArray = paramsArray[i].split("=");
            params.put(attrArray[0], attrArray[1]);
        }
        
        return params;
    }
}
