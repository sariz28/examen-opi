import { Injectable } from '@angular/core';
import { Http, Response, RequestOptionsArgs, RequestOptions } from '@angular/http';
import {Cancion} from '../model/cancion.model';

@Injectable()
export class CancionService {

  constructor(private http:Http) { }

    obtieneListaCanciones(idAlbum:number) {
      return this.http.get('/cancion/lista/' + idAlbum );
    }

    agregarCancion(cancion:Cancion) {
         return this.http.post('/cancion/', cancion);
    }

    eliminarCancion(idAlbum:number, idCancion:number) {
         return this.http.delete('/cancion/' + idAlbum + "/" + idCancion);
    }

}
