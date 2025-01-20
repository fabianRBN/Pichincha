import { Component, inject, signal,ViewChild  } from '@angular/core';
import { ClientService } from '../../core/services/client/client.service';
import { Client } from '../../core/interfaces/client.interface';
import { FormsModule ,FormControl , FormGroup, ReactiveFormsModule, Validators} from '@angular/forms'; 
import { AccountService } from '../../core/services/account/account.service';
import { Account } from '../../core/interfaces/account.interface';

@Component({
  selector: 'app-account',
  imports: [FormsModule,ReactiveFormsModule],
  templateUrl: './account.component.html',
  styleUrl: './account.component.css',
  providers:[ClientService,AccountService]
})
export class AccountComponent {
  @ViewChild('modal') modal: any;

  clientName:string ="";
  accountList: Account[]=[];
  client: Client= {
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

  private clientService = inject(ClientService)
  private accounService = inject(AccountService)

  form = signal<FormGroup>(
    new FormGroup({
      accountNumber:new FormControl('',[Validators.required,Validators.minLength(20)]),
      accountType:new FormControl('',[Validators.required,Validators.minLength(20)]),
      initialBalance:new FormControl('',[Validators.required,Validators.minLength(20)])
    })
  )
  findClientByIdentification(){
    this.clientService.findClientByIdentification(this.clientName).subscribe( response=> {
      if(response.success){
        this.client = response.data;
        this.getAccountsClient();
      }else{
        this.client = {
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
  getAccountsClient(){
    this.accounService.getAccounts(this.client.id).subscribe((response)=>{
      console.log(response);
      this.accountList = response.data;
    })
  }
  closeModal(modal: any) {
    modal.close();
  }
  openModal() {
    this.modal.nativeElement.showModal();
  }
  createAccount(){
    const account: Account = {
      id: 0,
      accountNumber: '',
      accountType: '',
      initialBalance: 0,
      status: true,
      clientId: this.client.id
    }

    account.accountNumber = this.form().controls['accountNumber'].value;
    account.accountType = this.form().controls['accountType'].value;
    account.initialBalance = this.form().controls['initialBalance'].value;

    this.accounService.createAccount(account).subscribe(response=>{
      console.log(response);
      this.getAccountsClient();
    })

  }

  updateAccount(account: Account){
    this.accounService.updateAccount(account).subscribe(response=>{
      console.log(response);
      this.getAccountsClient();
    })
  }

}
