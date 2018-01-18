package com.sara.examenopi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@ApiModel(value = "AlbumDto", description = "Objeto Album")
public class AlbumDto {
    
    private Integer id;
    @ApiModelProperty(required = true)
    @NotNull(message ="album_req_error")
    @NotEmpty(message = "album_req_error")
    private String nombre;
    private Integer numeroCanciones;
    private List<CancionDto> canciones;

    public AlbumDto() {
    }

    public AlbumDto(String nombre, Integer id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CancionDto> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<CancionDto> canciones) {
        this.canciones = canciones;
    }

    public Integer getNumeroCanciones() {
        return numeroCanciones;
    }

    public void setNumeroCanciones(Integer numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }
}
