import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {AlbumService} from '../services/album.service';
import {Album} from '../model/album.model';
import {InfoError} from '../model/infoError.model';

@Component({
  selector: 'app-home',
  templateUrl: './album.component.html',
  styleUrls: ['./album.component.css'],
  providers:[AlbumService]
})
export class AlbumComponent implements OnInit {
  albumes: Album[];
  error: boolean;
  infoError: InfoError;
  mensajeExito: string;
  mensajeInfo: string;
  album: Album;
  agregaAlbum: boolean;

  constructor(private _albumService: AlbumService, private router: Router) {}

  ngOnInit() {
        this.infoError  = new InfoError();
        this.obtenerALbumes();
  }

  private obtenerALbumes() {
          this._albumService.obtieneListaAlbumes().subscribe(data=>{
                  this.albumes = data.json();
          },err=>{
                   this.infoError = err.json();
                   if(!this.infoError.mensajeError)
                      this.infoError.mensajeError = "Ocurrio un error al obtener álbumes"
                   this.error = true;
          });
  }

  public agregarALbum(album) {
        this.limpiaMensaje();
        this._albumService.agregarAlbum(album).subscribe(data=>{
                let respuesta = data.json();
                if(!respuesta.error){
                    this.quitarFormAlbum();
                    this.mensajeExito='El álbum ' + album.nombre + ' se ha guardado con éxito.';
                    this.obtenerALbumes();
                }else{
                      this.mensajeInfo = respuesta.mensajeError;
                }
        },err=>{
                 this.infoError = err.json();
                 if(!this.infoError.mensajeError)
                    this.infoError.mensajeError = "Ocurrio un error al agregar álbum"
                 this.error = true;
        });
  }

  public eliminarALbum(album) {
        this.limpiaMensaje();
        this._albumService.eliminarAlbum(album.id).subscribe(data=>{
                let respuesta = data.json();
                if(!respuesta.error){
                    this.mensajeExito='El álbum ' + album.nombre + ' se ha eliminado con éxito.';
                    this.obtenerALbumes();
                }else{
                      this.mensajeInfo = respuesta.mensajeError;
                }
        },err=>{
                 this.infoError = err.json();
                 if(!this.infoError.mensajeError)
                    this.infoError.mensajeError = "Ocurrio un error al eliminar álbum"
                 this.error = true;
        });
  }

  public mostrarCancionesAlbum(album){
      this.router.navigate(['album', album.id]);
  }

  public limpiaMensaje(){
    this.mensajeExito="";
    this.mensajeInfo = "";
    this.error = false;
  }

  public muestrarFormAlbum(){
     this.album = new Album();
     this.agregaAlbum = true;
  }

  public quitarFormAlbum(){
     this.limpiaMensaje();
     this.agregaAlbum = false;
  }

}
