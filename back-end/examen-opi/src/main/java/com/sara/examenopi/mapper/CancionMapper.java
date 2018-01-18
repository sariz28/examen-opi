package com.sara.examenopi.mapper;

import com.sara.examenopi.dto.CancionDto;
import com.sara.examenopi.util.AlbumJson;
import org.json.simple.JSONObject;


public class CancionMapper {
    
    public static JSONObject convertirDtoAJsonString (CancionDto cancionDto) throws Exception{
        
        JSONObject cancionJson = new JSONObject();
        cancionJson .put(AlbumJson.CAMPO_ID, cancionDto.getId());
        cancionJson .put(AlbumJson.CAMPO_NOMBRE, cancionDto.getNombre());

        return  cancionJson;
    }
}
