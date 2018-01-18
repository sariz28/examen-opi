package com.sara.examenopi.service.impl;

import com.sara.examenopi.dao.IAlbumInfoDao;
import com.sara.examenopi.dao.ICancionDao;
import com.sara.examenopi.dto.AlbumDto;
import com.sara.examenopi.dto.CancionDto;
import com.sara.examenopi.dto.ExamenOpiExcepcion;
import com.sara.examenopi.dto.ValidacionExcepcion;
import com.sara.examenopi.mapper.AlbumMapper;
import com.sara.examenopi.mapper.CancionMapper;
import com.sara.examenopi.service.ICancionService;
import com.sara.examenopi.util.ExceptionMessage;
import com.sara.examenopi.util.InfoErrorEnum;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancionService implements ICancionService{
    
    @Autowired
    private ICancionDao cancionDao;
    
    @Autowired
    private IAlbumInfoDao albumDao;
    
    @Autowired
    private ExceptionMessage exceptionMessage;
    
     private final Logger log = LoggerFactory.getLogger(CancionService.class);

    @Override
    public CancionDto crearCancionAlbum(CancionDto cancionDto) throws Exception {
        
        if(albumDao.obtenerIndexAlbum(cancionDto.getIdAlbum()) == -1)
            throw new ValidacionExcepcion(exceptionMessage.getMessageError(InfoErrorEnum.ALBUM_EXISTENTE_ERROR.id()));
        
        if(cancionDao.existeCancion(cancionDto.getIdAlbum(), cancionDto.getNombre()))
            throw new ValidacionExcepcion(exceptionMessage.getMessageError(InfoErrorEnum.CANCION_NOMBRE_ERROR.id()));
        
        try {
            cancionDto.setId(cancionDao.obtenerNuevoId(cancionDto.getIdAlbum()));
            cancionDao.guardarCancion(cancionDto.getIdAlbum(),CancionMapper.convertirDtoAJsonString(cancionDto));
      
            return cancionDto;
        } catch (Exception e) {
            log.error("ERROR AL AGREGAR CANCIÓN AL ÁLBUM MUSICAL : " + cancionDto.getIdAlbum(), e);
            throw  new ExamenOpiExcepcion(exceptionMessage.getMessageError(InfoErrorEnum.CANCION_AGREGAR_ERROR.id()));
        }
    }

    @Override
    public void eliminarCancionAlbum(Integer idAlbum, Integer idCancion) throws Exception {
                
        if(albumDao.obtenerIndexAlbum(idAlbum) == -1)
            throw new ValidacionExcepcion(exceptionMessage.getMessageError(InfoErrorEnum.ALBUM_EXISTENTE_ERROR.id()));
        
        int indexCancion = cancionDao.obtenerIndexCancion(idAlbum, idCancion);
        if(indexCancion == -1)
            throw new ValidacionExcepcion(exceptionMessage.getMessageError(InfoErrorEnum.CANCION_ELIMINAR_ID_ERROR.id()));
        
        try {
            cancionDao.eliminarCancion(idAlbum, idCancion, indexCancion);
            
        } catch (Exception e) {
            log.error("ERROR AL ELIMINAR ÁLBUM MUSICAL : ", e);
            throw  new ExamenOpiExcepcion(exceptionMessage.getMessageError(InfoErrorEnum.CANCION_ELIMINAR_ERROR.id()));
        }
    }
    
    @Override
    public AlbumDto obtenerListaCanciones(Integer idAlbum) throws Exception{
        
        int indexAlbum = albumDao.obtenerIndexAlbum(idAlbum);
        
        if(indexAlbum == -1)
            throw new ValidacionExcepcion(exceptionMessage.getMessageError(InfoErrorEnum.ALBUM_EXISTENTE_ERROR.id()));
        
        try {
            JSONObject albumJson = albumDao.obtenerListaCanciones(idAlbum);
            
            return AlbumMapper.convertirADto(albumJson);
            
        } catch (Exception e) {
            log.error("ERROR AL OBTENER LISTA DE CANCIONES DE ALBUM : " + idAlbum , e);
            throw  new ExamenOpiExcepcion(exceptionMessage.getMessageError(InfoErrorEnum.ALBUM_CONSULTAR_CANCIONES_ERROR.id()));
        }
    }
    
}
