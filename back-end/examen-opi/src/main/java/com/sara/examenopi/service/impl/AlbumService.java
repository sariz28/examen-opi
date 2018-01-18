package com.sara.examenopi.service.impl;


import com.sara.examenopi.dao.IAlbumInfoDao;
import com.sara.examenopi.dto.AlbumDto;
import com.sara.examenopi.dto.ExamenOpiExcepcion;
import com.sara.examenopi.dto.ValidacionExcepcion;
import com.sara.examenopi.mapper.AlbumMapper;
import com.sara.examenopi.service.IAlbumService;
import com.sara.examenopi.util.ExceptionMessage;
import com.sara.examenopi.util.InfoErrorEnum;
import java.util.List;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService implements IAlbumService{
    
    @Autowired
    private IAlbumInfoDao albumDao;
    
    @Autowired
    private ExceptionMessage exceptionMessage;

    private final Logger log = LoggerFactory.getLogger(AlbumService.class);
    
    @Override
    public List<AlbumDto> obtenerListaAlbumes() {
        
        try {
            JSONArray albumesJson = albumDao.obtenerAlbumes();
            
            return AlbumMapper.convertirAListaDto(albumesJson);
            
        } catch (Exception e) {
            log.error("ERROR AL OBTENER LISTA DE ÁLBUMES MUSICALES : ", e);
            throw  new ExamenOpiExcepcion(exceptionMessage.getMessageError(InfoErrorEnum.ALBUM_CONSULTAR_ERROR.id()));
        }
    }

    @Override
    public AlbumDto crearAlbum(AlbumDto album) throws Exception{
        
      if(albumDao.existeAlbum(album.getNombre()))
          throw new ValidacionExcepcion(exceptionMessage.getMessageError(InfoErrorEnum.ALBUM_NOMBRE_ERROR.id()));
        try {
            album.setId(albumDao.obtenerNuevoId());
            albumDao.guardarAlbum(AlbumMapper.convertirDtoAJsonString(album));
      
            return album;
        } catch (Exception e) {
            log.error("ERROR AL AGREGAR ÁLBUM MUSICAL : ", e);
            throw  new ExamenOpiExcepcion(exceptionMessage.getMessageError(InfoErrorEnum.ALBUM_AGREGAR_ERROR.id()));
        }
     
    }

    @Override
    public void eliminarAlbum(Integer idAlbum) throws Exception{
        
        int indexAlbum = albumDao.obtenerIndexAlbum(idAlbum);
        
        if(indexAlbum == -1)
            throw new ValidacionExcepcion(exceptionMessage.getMessageError(InfoErrorEnum.ALBUM_ELIMINAR_ID_ERROR.id()));
        
        try {
            albumDao.eliminarAlbum(idAlbum, indexAlbum);
            
        } catch (Exception e) {
            log.error("ERROR AL ELIMINAR ÁLBUM MUSICAL : ", e);
            throw  new ExamenOpiExcepcion(exceptionMessage.getMessageError(InfoErrorEnum.ALBUM_ELIMINAR_ERROR.id()));
        }
        
    }

    
}
