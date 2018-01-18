
package com.sara.examenopi.service;

import com.sara.examenopi.dto.AlbumDto;
import java.util.List;

public interface IAlbumService {
    
    /**
     * Obtiene lista de ábumes registrados que son guardados en un archivo json 
     * @return Lista de objetos AlbumDto
     */
    List<AlbumDto> obtenerListaAlbumes ();
    
    /**
     * Valida y guarda un objeto de tipo album en archivo json
     * @param album AlbumDto 
     * @return AlbumDto objeto guardado con id con el que se guardo
     * @exception  Exception,ExamenOpiExcepcion, ValidaciondacionExcepcion
     */
    AlbumDto crearAlbum (AlbumDto album)throws Exception;
    
    /**
     * Valida y elimina un objeto de tipo álbum en archivo json 
     * @param idAlbum  Integer. Identificador de álbum 
     * @exception  Exception, ExamenOpiExcepcion, ValidaciondacionExcepcion
     */
    void eliminarAlbum (Integer idAlbum)throws Exception;
    
}
