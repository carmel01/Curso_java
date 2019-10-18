import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/domain/cliente';
import { ClienteService } from 'src/app/service/cliente.service';
import { VirtualTimeScheduler } from 'rxjs';

@Component({
  selector: 'app-cliente-list',
  templateUrl: './cliente-list.component.html',
  styleUrls: ['./cliente-list.component.css']
})
export class ClienteListComponent implements OnInit {

  public listaCliente:Cliente[];
  
  constructor(public clienteService:ClienteService) { }

  ngOnInit() {
    this.findAll();
  }

  public findAll(){
    this.clienteService.findAll().subscribe(resultado=>{
      this.listaCliente=resultado;
    });
  }

}
