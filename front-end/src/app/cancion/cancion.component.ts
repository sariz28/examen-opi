import { Component, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {CancionService} from '../services/cancion.service';
import {Cancion} from '../model/cancion.model';
import {Album} from '../model/album.model';
import {InfoError} from '../model/infoError.model';

@Component({
  selector: 'app-cancion',
  templateUrl: './cancion.component.html',
  styleUrls: ['./cancion.component.css'],
  providers:[CancionService]
})
export class CancionComponent implements OnInit {
  album: Album;
  idAlbum: number;
  error: boolean;
  infoError: InfoError;
  cancion: Cancion;
  mensajeExito: string;
  mensajeInfo: string;
  agregaCancion: boolean;

  constructor(private _cancionService: CancionService,
              private route: ActivatedRoute) {}

  ngOnInit() {
      this.infoError  = new InfoError();
      this.album = new Album();
      this.route.params.subscribe(params => {
          this.idAlbum = +params['idAlbum'];
          this.obtenerCanciones();
       });
  }

  private obtenerCanciones() {
          this._cancionService.obtieneListaCanciones(this.idAlbum).subscribe(data=>{
                  this.album = data.json();
          },err=>{
                   this.infoError = err.json();
                   if(!this.infoError.mensajeError)
                      this.infoError.mensajeError = "Ocurrio un error al obtener canciones del álbum"
                   this.error = true;
          });
  }

  public agregarCancion(cancion) {
        this.limpiaMensaje();
        cancion.idAlbum = this.idAlbum;
        this._cancionService.agregarCancion(cancion).subscribe(data=>{
                let respuesta = data.json();
                if(!respuesta.error){
                    this.quitarFormCancion();
                    this.mensajeExito='La canción ' + cancion.nombre + ' se ha guardado con éxito.';
                    this.obtenerCanciones();
                }else{
                      this.mensajeInfo = respuesta.mensajeError;
                }
        },err=>{
                 this.infoError = err.json();
                 if(!this.infoError.mensajeError)
                    this.infoError.mensajeError = "Ocurrio un error al agregar canción"
                 this.error = true;
        });
  }

  public eliminarCancion(cancion) {
        this.limpiaMensaje();
        this._cancionService.eliminarCancion(this.idAlbum, cancion.id).subscribe(data=>{
                let respuesta = data.json();
                if(!respuesta.error){
                    this.mensajeExito='La canción ' + cancion.nombre + ' se ha eliminado con éxito.';
                    this.obtenerCanciones();
                }else{
                      this.mensajeInfo = respuesta.mensajeError;
                }
        },err=>{
                 this.infoError = err.json();
                 if(!this.infoError.mensajeError)
                    this.infoError.mensajeError = "Ocurrio un error al eliminar canción"
                 this.error = true;
        });
  }

  public muestrarFormCancion(){
     this.cancion = new Cancion();
     this.agregaCancion = true;
  }

  public quitarFormCancion(){
     this.limpiaMensaje();
     this.agregaCancion = false;
  }

  public limpiaMensaje(){
    this.mensajeExito="";
    this.mensajeInfo = "";
    this.error = false;
  }

}
