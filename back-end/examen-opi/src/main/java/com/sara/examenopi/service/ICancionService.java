
package com.sara.examenopi.service;

import com.sara.examenopi.dto.AlbumDto;
import com.sara.examenopi.dto.CancionDto;



public interface ICancionService {
    
   /**
     * Obtiene lista de canciones registradas que son guardados en un archivo json 
     * @param idAlbum  Integer. Identificador de álbum
     * @return AlbumDto
     * @exception  Exception, ExamenOpiExcepcion
     */
    AlbumDto obtenerListaCanciones (Integer idAlbum) throws Exception;
    
    /**
     * Valida y guarda un objeto de tipo cancion en archivo json que contiene 
     * las canciones del album 
     * @param cancionDto CancionDto
     * @return CancionDto
     * @exception  Exception,ExamenOpiExcepcion, ValidaciondacionExcepcion
     */
    CancionDto crearCancionAlbum (CancionDto cancionDto)throws Exception;
    
    /**
     * Valida y elimina un objeto de tipo cancion en archivo json que contiene 
     * las canciones del albumn 
     * @param idAlbum  Integer. Identificador de álbum padre
     * @param idCancion Integer. Identificador canción
     * @exception  Exception, ExamenOpiExcepcion, ValidaciondacionExcepcion
     */
    void eliminarCancionAlbum (Integer idAlbum, Integer idCancion)throws Exception;
    
}
