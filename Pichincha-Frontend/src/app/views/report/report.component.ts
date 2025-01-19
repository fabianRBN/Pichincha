import { Component, inject, signal,ViewChild  } from '@angular/core';

import { ClientService } from '../../core/services/client/client.service';
import { Client } from '../../core/interfaces/client.interface';
import { AccountService } from '../../core/services/account/account.service';
import { Account } from '../../core/interfaces/account.interface';
import { FormsModule ,FormControl , FormGroup, ReactiveFormsModule, Validators} from '@angular/forms'; 
import { MovementService } from '../../core/services/movement/movement.service';
import { Movement } from '../../core/interfaces/movement.interface';
import { AccountMovement } from '../../core/interfaces/accountMovement.interface';
@Component({
  selector: 'app-report',
  imports: [FormsModule,ReactiveFormsModule],
  templateUrl: './report.component.html',
  styleUrl: './report.component.css',
  providers:[ClientService,AccountService,MovementService]
})
export class ReportComponent {
  private clientService = inject(ClientService)
  private accounService = inject(AccountService)
  private movementService = inject(MovementService)

  idTitular:string="";
  clientTitular: Client= {
    id: 0,
    name: '',
    address: '',
    gender: '',
    age: 0,
    phoneNumber: '',
    status: false,
    password: '',
    identification: ''
  }
}
