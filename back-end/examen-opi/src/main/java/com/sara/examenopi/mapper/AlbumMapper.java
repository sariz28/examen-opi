
package com.sara.examenopi.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sara.examenopi.dto.AlbumDto;
import com.sara.examenopi.util.AlbumJson;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AlbumMapper {
    
    public static List<AlbumDto> convertirAListaDto (JSONArray albumesJson) throws Exception{
        
        ObjectMapper mapper = new ObjectMapper();
   
        return  mapper.readValue(albumesJson.toJSONString(), new TypeReference<List<AlbumDto>>(){});
    }
    
    public static AlbumDto convertirADto (JSONObject albumJson) throws Exception{
        
        ObjectMapper mapper = new ObjectMapper();
   
        return  mapper.readValue(albumJson.toJSONString(), AlbumDto.class);
    }
    
    public static JSONObject convertirDtoAJsonString (AlbumDto album) throws Exception{
        
        JSONObject albumJson = new JSONObject();
        albumJson.put(AlbumJson.CAMPO_ID, album.getId());
        albumJson.put(AlbumJson.CAMPO_NOMBRE, album.getNombre());

        return  albumJson;
    }
    
}
