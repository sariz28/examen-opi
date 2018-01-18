package com.sara.examenopi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@ApiModel(value = "CancionDto", description = "Objeto cancion")
public class CancionDto {
    
    private Integer id;
    @ApiModelProperty(required = true)
    @NotNull(message = "cancion_req_error")
    @NotEmpty(message = "cancion_req_error")
    private String nombre;
    private Integer idAlbum;

    public CancionDto() {
    }

    public CancionDto(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }
    
}
