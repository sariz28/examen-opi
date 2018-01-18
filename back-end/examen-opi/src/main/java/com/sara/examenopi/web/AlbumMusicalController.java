package com.sara.examenopi.web;

import com.sara.examenopi.dto.AlbumDto;
import com.sara.examenopi.dto.RespuestaDto;
import com.sara.examenopi.service.IAlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="/album-musical", tags = "Operaciones para álbum musical")
@RequestMapping("/album-musical")
public class AlbumMusicalController {
    
    private final IAlbumService albumService;
    
    @Autowired
    public AlbumMusicalController(IAlbumService albumService) {
        this.albumService = albumService;
    }
    
    
    @RequestMapping(value="", method=RequestMethod.GET)
    @ApiOperation(value = "Consultar lista de álbunes musicales", 
                  notes = "Retorna una lista de objetos tipo álbum musical almacenados en la aplicación")
    public List<AlbumDto> obtenerListAlbumes() {
        
        return albumService.obtenerListaAlbumes();
    }
    
    @RequestMapping(value="", method=RequestMethod.POST)
    @ApiOperation(value = "Agregar álbum musical", 
                  notes = "Agrega y retorna el objeto álbum musical dado de alta")
    public RespuestaDto agregarAlbum(@Valid @RequestBody AlbumDto albumDto) throws Exception {
        
        albumService.crearAlbum(albumDto);
        
        return new RespuestaDto(false);
    }
    
    @RequestMapping(value="/{idAlbum}", method=RequestMethod.DELETE)
    @ApiOperation(value = "Eliminar álbum musical", 
                  notes = "Elimina el objeto álbum musical selecionado por el usuario")
    public RespuestaDto eliminarAlbum(@PathVariable Integer idAlbum) throws Exception{
        
        albumService.eliminarAlbum(idAlbum);
        
        return new RespuestaDto(false);
            
    } 
   
}
