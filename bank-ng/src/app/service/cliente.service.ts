import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Cliente } from '../domain/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  public url:string;

  constructor(public httpClient:HttpClient) { 
    this.url = environment.apiUrl+'/cliente/';
  }

  public findAll():Observable<any>{
    return this.httpClient.get(this.url+'findAll');
  }

  public save(cliente:Cliente):Observable<any>{
    return this.httpClient.post(this.url+'save', cliente);
  }
}
