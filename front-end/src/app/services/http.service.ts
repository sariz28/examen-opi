import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import {Http, XHRBackend, RequestOptions, Request, RequestOptionsArgs, Response, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
/**
 * Extensión personalizada de la clase HTTP
 * Permite la configuración de todas las peticiones
 * Captura los envíos y respuestas
 * */
export class HttpService extends Http {
  /** Las direcciones base deberían venir de la configuración del environment*/
   public apiProxyUrl = environment.ulBackEnd;

  constructor(
    backend: XHRBackend,
    defaultOptions: RequestOptions
  ) {
    super(backend, defaultOptions);
  }
  /**
   * Reescribe el método de la clase base, ejecutando acciones para cada petición
   * La peticiíón en curso puede llegar como una ruta o una clase request
   * Si viene sólo la cadena, debería traer las opciones aparte
   * */
  request(request: string | Request, options: RequestOptionsArgs = { headers: new Headers() }): Observable<Response> {
    this.configureRequest(request, options);
    return this.interceptResponse(request, options);
  }

  private configureRequest(request: string | Request, options: RequestOptionsArgs) {
    // Adapta la ruta y asigna cabeceras
    if (typeof request === 'string') {
      request = this.getProxyUrl(request);
      this.setHeaders(options);
    } else {
      request['url'] = this.getProxyUrl(request['url']);
      this.setHeaders(request);
    }
  }
  private interceptResponse(request: string | Request, options: RequestOptionsArgs) : Observable<Response> {
    const observableRequest = super
      .request(request, options)
      .catch(this.onCatch());
    return observableRequest;
  }
  /**
   * Transforma la url para llamar a trave´s de un proxy
   * Útil en caso de problemas con el CORS
   */
  private getProxyUrl(currentUrl) {
    if (!currentUrl.includes('/assets/')) {
      return this.apiProxyUrl + currentUrl;
    } else {
      return currentUrl;
    }
  }
  /**
   * Interceptor para componer las cabeceras en cada petición
   * */
  private setHeaders(objectToSetHeadersTo: Request | RequestOptionsArgs) {
    const headers = objectToSetHeadersTo.headers;
    headers.set('Content-Type', 'application/json');
  }
  /**
   * Interceptor para captura genérica de errores http
   * */
  private onCatch() {
    return (res: Response) => {

      /* Security errors
      if (res.status === 401 || res.status === 403) {
        // redirigir al usuario para pedir credenciales
        this.router.navigate(['user/login']);
      }*/

      // To Do: Gestión común de otros errores...
      return Observable.throw(res);
    };
  }
}
