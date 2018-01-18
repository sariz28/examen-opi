import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { AlbumComponent } from './album/album.component';
import { CancionComponent } from './cancion/cancion.component';

const routes: Routes =[
    { path: 'biblioteca-musical',  component: AlbumComponent},
    { path: 'album/:idAlbum',  component: CancionComponent },
    { path: '',  redirectTo: 'biblioteca-musical', pathMatch: 'full' }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
