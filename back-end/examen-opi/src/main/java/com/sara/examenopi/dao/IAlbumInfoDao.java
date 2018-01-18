package com.sara.examenopi.dao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IAlbumInfoDao {
    
    JSONArray obtenerAlbumes() throws Exception;
    void guardarAlbum(JSONObject albumesJson) throws Exception;
    void eliminarAlbum(Integer idAlbum, Integer indexObjectJson) throws Exception;
    Integer obtenerNuevoId ()throws Exception;
    boolean existeAlbum (String nombreAlbum) throws Exception;
    Integer obtenerIndexAlbum (Integer idAlbum)throws Exception;
    JSONObject obtenerListaCanciones (Integer idAlbum) throws Exception;
}
