package com.sara.examenopi.web;

import com.sara.examenopi.dto.AlbumDto;
import com.sara.examenopi.dto.CancionDto;
import com.sara.examenopi.dto.RespuestaDto;
import com.sara.examenopi.service.ICancionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="/cancion", tags = "Operaciones para Canción")
@RequestMapping("/cancion")
public class CancionController {
    
    private final ICancionService cancionService;
    
    @Autowired
    public CancionController(ICancionService cancionService) {
        this.cancionService= cancionService;
    }
    
    @RequestMapping(value="/lista/{idAlbum}", method=RequestMethod.GET)
    @ApiOperation(value = "Consultar lista de canciones por id de álbum", 
                  notes = "Retorna un objeto AlbumDto el cual contiene la lista de canciones que pertenecen a un álbum")
    public AlbumDto obtenerListCancionesAlbum(@PathVariable Integer idAlbum) throws Exception{
        
        return cancionService.obtenerListaCanciones(idAlbum);
    }

    
    @RequestMapping(value="", method=RequestMethod.POST)
    @ApiOperation(value = "Agregar canción a un álbum", 
                  notes = "Agrega y retorna el objeto CancionDto dado de alta en determinado álbum")
    public RespuestaDto agregarCancionAlbum(@Valid @RequestBody CancionDto cancionDto) throws Exception {
        
        cancionService.crearCancionAlbum(cancionDto);
        
        return new RespuestaDto(false);
    }
    
    @RequestMapping(value="/{idAlbum}/{idCancion}", method=RequestMethod.DELETE)
    @ApiOperation(value = "Eliminar una canción ", 
                  notes = "Elimina el objeto canción (CancionDto) selecionado por el usuario")
    public RespuestaDto eliminarCancionAlbum(@PathVariable Integer idAlbum,
                                             @PathVariable Integer idCancion) throws Exception{
        
        cancionService.eliminarCancionAlbum(idAlbum, idCancion);
        
        return new RespuestaDto(false);
            
    }  
    
}

