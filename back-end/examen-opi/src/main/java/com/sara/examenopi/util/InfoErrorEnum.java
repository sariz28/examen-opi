
package com.sara.examenopi.util;

public enum InfoErrorEnum {
    
    ALBUM_CONSULTAR_ERROR("album_consultar_error"),
    ALBUM_AGREGAR_ERROR("album_agregar_error"),
    ALBUM_NOMBRE_ERROR("album_nombre_error"),
    ALBUM_ELIMINAR_ERROR("album_eliminar_error"),
    ALBUM_ELIMINAR_ID_ERROR("album_eliminar_id_error"),
    ALBUM_EXISTENTE_ERROR("album_existente_error"),
    ALBUM_CONSULTAR_CANCIONES_ERROR("album_consultar_canciones_error"),
    ALBUM_REQ_ERROR("album_req_error"),
    CANCION_AGREGAR_ERROR("cancion_agregar_error"),
    CANCION_ELIMINAR_ERROR("cancion_eliminar_error"),
    CANCION_NOMBRE_ERROR("cancion_nombre_error"),
    CANCION_ELIMINAR_ID_ERROR("cancion_eliminar_id_error"),
    CANCION_REQ_ERROR("cancion_req_error");
    
    private final String id;

    private InfoErrorEnum(String id) {
        this.id = id;
    }
    
    public String id(){
           return id;
    }
    
}
