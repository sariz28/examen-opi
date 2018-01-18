package com.sara.examenopi.dao;

import org.json.simple.JSONObject;

public interface ICancionDao {
    
    void guardarCancion(Integer idAlbum,JSONObject cancionJson) throws Exception;
    void eliminarCancion(Integer idAlbum,Integer idCancion, Integer indexObjectJson) throws Exception;
    Integer obtenerNuevoId (Integer idAlbum)throws Exception;
    boolean existeCancion (Integer idAlbum, String nombreCancion) throws Exception;
    Integer obtenerIndexCancion (Integer idAlbum, Integer idCancion)throws Exception;
    
}
