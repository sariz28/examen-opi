import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app.routing';
import { NavbarModule } from './base-layout/shared/navbar/navbar.module';
import { FooterModule } from './base-layout/shared/footer/footer.module';
import { SidebarModule } from './base-layout/sidebar/sidebar.module';
import { HttpService } from './services/http.service';
import {Http, XHRBackend, RequestOptions, Request, RequestOptionsArgs, Response, Headers} from '@angular/http';

import { AppComponent } from './app.component';
import { AlbumComponent } from './album/album.component';
import { CancionComponent } from './cancion/cancion.component';

@NgModule({
  declarations: [
    AppComponent,
    AlbumComponent,
    CancionComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    NavbarModule,
    FooterModule,
    SidebarModule,
    RouterModule,
    AppRoutingModule,
  ],
  providers: [
   {
     provide: Http,
     useClass: HttpService,
     deps: [XHRBackend, RequestOptions]
   }
 ],
  bootstrap: [AppComponent]
})
export class AppModule { }
