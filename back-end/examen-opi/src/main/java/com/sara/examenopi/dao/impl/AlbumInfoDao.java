
package com.sara.examenopi.dao.impl;

import com.sara.examenopi.dao.IAlbumInfoDao;
import com.sara.examenopi.util.AlbumJson;
import com.sara.examenopi.util.Validador;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AlbumInfoDao implements IAlbumInfoDao{
    
    @Value("${albumes.archivo.url}")
    private String albumesArchivoUrl;
    
    @Value("${album.canciones.archivo.url}")
    private String albumCancionesArchivoUrl;
     

    @Override
    public JSONArray obtenerAlbumes() throws Exception {

        JSONObject albumesInfoJson = obtenerInFoAlbumes();
        
        return (JSONArray) albumesInfoJson.get(AlbumJson.CAMPO_ALBUMES);
    }

    @Override
    public void guardarAlbum(JSONObject albumJson) throws Exception {
        
        JSONObject albumesInfoJson = obtenerInFoAlbumes();
        JSONArray albumes = (JSONArray) albumesInfoJson.get(AlbumJson.CAMPO_ALBUMES);
        albumes.add(albumJson);
        Long numAlbumes = (long) albumesInfoJson.get(AlbumJson.CAMPO_NUM_ALBUMES);
        albumesInfoJson.put(AlbumJson.CAMPO_ALBUMES, albumes);
        albumesInfoJson.put(AlbumJson.CAMPO_NUM_ALBUMES, numAlbumes.intValue() + 1);
        guardarInfoJson(albumesInfoJson);
        guardarArchivoCanciones((Integer)albumJson.get(AlbumJson.CAMPO_ID), albumJson);
        
    }
    
    @Override
    public void eliminarAlbum(Integer idAlbum, Integer indexObjectJson) throws Exception {
        JSONObject albumesInfoJson = obtenerInFoAlbumes();
        JSONArray albumes = (JSONArray) albumesInfoJson.get(AlbumJson.CAMPO_ALBUMES);
        albumes.remove(indexObjectJson.intValue());
        albumesInfoJson.put(AlbumJson.CAMPO_ALBUMES, albumes);
        guardarInfoJson(albumesInfoJson);
        eliminarArchivoCanciones(idAlbum);
    }
    
    @Override
    public JSONObject obtenerListaCanciones(Integer idAlbum) throws Exception {
           JSONParser parser = new JSONParser();
           String urlcompleta = albumCancionesArchivoUrl + AlbumJson.ALBUM_ARCHIVO + idAlbum + AlbumJson.EXTENSION_ARCHIVO;
           Object obj = parser.parse(new FileReader(urlcompleta));
           
           return (JSONObject) obj;
    }
    
    @Override
    public Integer obtenerNuevoId() throws Exception{
        JSONObject albumesInfoJson = obtenerInFoAlbumes();
        Long numAlbumes = (long) albumesInfoJson.get(AlbumJson.CAMPO_NUM_ALBUMES);
        
        return numAlbumes.intValue() + 1;
        
    }

    @Override
    public boolean existeAlbum(String nombreAlbum) throws Exception {
        JSONArray albumesJson = obtenerAlbumes();
        
        return Validador.existeObjeto(albumesJson, nombreAlbum);
    }
    
    @Override
    public Integer obtenerIndexAlbum(Integer idAlbum) throws Exception {
        JSONArray albumesJson = obtenerAlbumes();
        
        return Validador.indexOf(albumesJson, idAlbum);
    }

    
    private JSONObject obtenerInFoAlbumes() throws Exception {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(albumesArchivoUrl));
        
        return (JSONObject) obj;
    }
    
    private void guardarInfoJson(JSONObject albumesInfoJson)throws Exception{
    
        FileWriter file = new FileWriter(albumesArchivoUrl);
        file.write(albumesInfoJson.toJSONString());
        file.flush();
    
    }
    
    private void guardarArchivoCanciones(Integer idAlbum, JSONObject albumJson) throws Exception{
        String urlCompletaAlbum = albumCancionesArchivoUrl + AlbumJson.ALBUM_ARCHIVO + idAlbum + AlbumJson.EXTENSION_ARCHIVO;
        File file = new File(urlCompletaAlbum);
        file.createNewFile();
        guardarInfoAlbumDetalle(urlCompletaAlbum, albumJson);
    }
   
    private void eliminarArchivoCanciones(Integer idAlbum) throws Exception{
        File file = new File(albumCancionesArchivoUrl + AlbumJson.ALBUM_ARCHIVO + idAlbum + AlbumJson.EXTENSION_ARCHIVO);
        file.delete();
    }
    
    private void guardarInfoAlbumDetalle(String albumCancionesUrl, JSONObject albumJson) throws Exception{
        albumJson.put(AlbumJson.CAMPO_CANCIONES, new JSONArray());
        albumJson.put(AlbumJson.CAMPO_NUMERO_CANCIONES, 0);
        FileWriter file = new FileWriter(albumCancionesUrl);
        file.write(albumJson.toJSONString());
        file.flush();
    }
}
