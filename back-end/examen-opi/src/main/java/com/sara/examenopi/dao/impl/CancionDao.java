
package com.sara.examenopi.dao.impl;

import com.sara.examenopi.dao.ICancionDao;
import com.sara.examenopi.util.AlbumJson;
import com.sara.examenopi.util.Validador;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CancionDao implements ICancionDao{
    
    @Value("${album.canciones.archivo.url}")
    private String albumCancionesArchivoUrl;

    @Override
    public void guardarCancion(Integer idAlbum, JSONObject cancionJson) throws Exception {
        
        JSONObject albumJson = obtenerInfoAlbum(idAlbum);
        JSONArray cancionesJson = (JSONArray) albumJson.get(AlbumJson.CAMPO_CANCIONES);
        cancionesJson.add(cancionJson);
        Long numCanciones = (long) albumJson.get(AlbumJson.CAMPO_NUMERO_CANCIONES);
        albumJson.put(AlbumJson.CAMPO_CANCIONES, cancionesJson);
        albumJson.put(AlbumJson.CAMPO_NUMERO_CANCIONES, numCanciones.intValue() + 1);
        guardarInfoAlbum(idAlbum, albumJson);

    }

    @Override
    public void eliminarCancion(Integer idAlbum, Integer idCancion, Integer indexObjectJson) throws Exception {
        
        JSONObject albumJson = obtenerInfoAlbum(idAlbum);
        JSONArray cancionesJson = (JSONArray) albumJson.get(AlbumJson.CAMPO_CANCIONES);
        cancionesJson.remove(indexObjectJson.byteValue());
        albumJson.put(AlbumJson.CAMPO_CANCIONES, cancionesJson);
        guardarInfoAlbum(idAlbum, albumJson);
    }

    @Override
    public Integer obtenerNuevoId(Integer idAlbum) throws Exception {
        
        JSONObject albumJson = obtenerInfoAlbum(idAlbum);
        Long numCanciones = (long) albumJson.get(AlbumJson.CAMPO_NUMERO_CANCIONES);
        
        return numCanciones.intValue() + 1;
    }

    @Override
    public boolean existeCancion(Integer idAlbum, String nombreCancion) throws Exception {
        
        JSONObject albumJson = obtenerInfoAlbum(idAlbum);
        JSONArray cancionesJson = (JSONArray) albumJson.get(AlbumJson.CAMPO_CANCIONES);
        
        return Validador.existeObjeto(cancionesJson, nombreCancion);
    }

    @Override
    public Integer obtenerIndexCancion(Integer idAlbum, Integer idCancion) throws Exception {
        
        JSONObject albumJson = obtenerInfoAlbum(idAlbum);
        JSONArray cancionesJson = (JSONArray) albumJson.get(AlbumJson.CAMPO_CANCIONES);
        
        return Validador.indexOf(cancionesJson , idCancion);
    }
    
    private JSONObject obtenerInfoAlbum(Integer idAlbum) throws Exception {
        
        JSONParser parser = new JSONParser();
        String urlcompleta = albumCancionesArchivoUrl + AlbumJson.ALBUM_ARCHIVO + idAlbum + AlbumJson.EXTENSION_ARCHIVO;
        Object obj = parser.parse(new FileReader(urlcompleta));
           
        return (JSONObject) obj;
    }
    
    private void guardarInfoAlbum(Integer idAlbum, JSONObject albumJson) throws Exception{
      
        String urlcompleta = albumCancionesArchivoUrl + AlbumJson.ALBUM_ARCHIVO + idAlbum + AlbumJson.EXTENSION_ARCHIVO;
        FileWriter file = new FileWriter(urlcompleta);
        file.write(albumJson.toJSONString());
        file.flush();
    }
    
}
