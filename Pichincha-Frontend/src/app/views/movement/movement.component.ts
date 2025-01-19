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
  selector: 'app-movement',
  imports: [FormsModule,ReactiveFormsModule],
  templateUrl: './movement.component.html',
  styleUrl: './movement.component.css',
  providers:[ClientService,AccountService,MovementService]
})
export class MovementComponent {

  private clientService = inject(ClientService)
  private accounService = inject(AccountService)
  private movementService = inject(MovementService)

  idTitular:string="";
  idBeneficiary:string=""
  accountsTitular:Account[]=[];
  
  accountsBenficiary:Account[]=[];
  movementsList: AccountMovement[] =[];
  operationType:string="D"

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

  clientBeneficiary: Client= {
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


  form = signal<FormGroup>(
    new FormGroup({
      accountOrg: new FormControl('',[Validators.required,Validators.minLength(20)]),
     
      valueDep: new FormControl('',[Validators.required,Validators.minLength(20)])
    }));

    formBen = signal<FormGroup>(
      new FormGroup({
        accountBen: new FormControl('',[Validators.required,Validators.minLength(20)]),
      }));

  findClientByIdentification(){
    this.clientService.findClientByIdentification(this.idTitular).subscribe( response=> {
      if(response.success){
        this.clientTitular = response.data;
        this.getAccountsClientTutular(this.clientTitular.id);
      }else{
        this.clientTitular = {
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
    })
  }

  getAccountsClientTutular(id:number){
    this.accounService.getAccounts(id).subscribe((response)=>{
      console.log(response);
       this.accountsTitular = response.data;
       this.getMovement();
    })
  }

  findClientByIdentificationBen(){
    this.clientService.findClientByIdentification(this.idBeneficiary).subscribe( response=> {
      if(response.success){
        this.clientBeneficiary = response.data;
        this.getAccountsClientBen(this.clientBeneficiary.id);
        
      }else{
        this.clientBeneficiary = {
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
    })
  }

  getAccountsClientBen(id:number){
    this.accounService.getAccounts(id).subscribe((response)=>{
      console.log(response);
       this.accountsBenficiary = response.data;
    })
  }

  createMovement(){

    const transaction: Movement = {
      id: 0,
      accountId: 0,
      date: '',
      transactionType: '',
      value: 0,
      balance: ''
    }

    transaction.accountId = this.form().controls['accountOrg'].value;
    transaction.value = this.form().controls['valueDep'].value;
    this.movementService.createMovement(transaction).subscribe((response)=>{
      console.log(response)
      if(this.operationType=='T'){
        
        const transactionBen: Movement = {
          id: 0,
          accountId: 0,
          date: '',
          transactionType: '',
          value: 0.0,
          balance: ''
        }
    
        transactionBen.accountId = this.formBen().controls['accountBen'].value;
        transactionBen.value = this.form().controls['valueDep'].value ;
        let valor = Number(transactionBen.value)  * -1
        transactionBen.value = valor
        this.movementService.createMovement(transactionBen).subscribe((response)=>{
          console.log(response)
        })

      }
    });

    
  }

  getMovement(){
    this.movementService.getMovement(this.clientTitular.id).subscribe((response)=>{
      this.movementsList = response.data
    });

  }

}
