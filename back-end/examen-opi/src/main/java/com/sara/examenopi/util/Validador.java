
package com.sara.examenopi.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Validador {
    
    public static int indexOf(JSONArray list,  Integer id){
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            JSONObject object = (JSONObject) list.get(i); 
            Long idObjectJson = (long)object.get("id");
            if(idObjectJson.intValue() == id.intValue()){
               index = i;
               break;
            }
        }
        return index;
    
    }
    
    public static boolean existeObjeto(JSONArray list,  String nombre){
        boolean existe = false ;
        for (int i = 0; i < list.size(); i++) {
            JSONObject object = (JSONObject) list.get(i); 
            if(object.get("nombre").equals(nombre)){
               existe = true;
               break;
            }
        }
        return existe;
    
    }
}
