import { Injectable } from '@angular/core';
import { Http, Response, RequestOptionsArgs, RequestOptions } from '@angular/http';
import {Album} from '../model/album.model';

@Injectable()
export class AlbumService {

  constructor(private http:Http) { }

   obtieneListaAlbumes() {
     return this.http.get('/album-musical');
   }

   eliminarAlbum(idAlbum:number) {
        return this.http.delete('/album-musical/' + idAlbum);
   }

   agregarAlbum(album:Album) {
        return this.http.post('/album-musical/', album);
   }

}
