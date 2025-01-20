import { Component, inject, signal,ViewChild  } from '@angular/core';
import { ClientService } from '../../core/services/client/client.service';
import { Client } from '../../core/interfaces/client.interface';
import { FormsModule ,FormControl , FormGroup, ReactiveFormsModule, Validators} from '@angular/forms'; 


@Component({
  selector: 'app-client',
  imports: [FormsModule,ReactiveFormsModule],
  templateUrl: './client.component.html',
  styleUrl: './client.component.css',
  providers:[ClientService]
})
export class ClientComponent {
  @ViewChild('modal') modal: any;

  clients: Client[] = [];
  page: number = 0;
  size: number = 2;
  totalElements: number = 10;
  pages: number[] = []; 
  clientName:string ="";
  updateClient: boolean = false;
  idClientUpdata: number = 0;

  private clientService = inject(ClientService)

  form = signal<FormGroup>(
    new FormGroup({
      name: new FormControl('',[Validators.required]),
      address: new FormControl('',[Validators.required]),
      gender:new FormControl('',[Validators.required]),
      age:new FormControl('',[Validators.required]),
      phoneNumber:new FormControl('',[Validators.required]),
      status:new FormControl('',[]),
      password:new FormControl('',[Validators.required]),
      rpassword:new FormControl('',[Validators.required]),
      identification:new FormControl('',[Validators.required])
    })
  )




  constructor(){
  }

  ngOnInit(): void {
    this.getClients();
  }

  getClientsSelect(){
    this.page = 0;
    this.getClients();
  }
  getClients(): void {
    this.clientService.getClients(this.page, this.size, (this.clientName)?this.clientName:"").subscribe((response) => {
      this.clients = response.data;
      this.totalElements = response.totalElements;
      this.pages = Array.from(
        { length: Math.ceil(this.totalElements / this.size) },
        (_, i) => i
      );
    });
  }

  onPageChange(event: any): void {
    this.page = event.pageIndex;
    this.size = event.pageSize;
    this.getClients();
  }

  goToPage(page: number): void {
    this.page = page;
    this.getClients();
  }

  nextPage(): void {
    if (this.page < this.pages.length - 1) {
      this.page++;
      this.getClients();
    }
  }

  previousPage(): void {
    if (this.page > 0) {
      this.page--;
      this.getClients();
    }
  }

  createOrUpdateClient(){

    if(!this.form().valid){
      alert("Formulario no es valido o esta incompleto");
      return;
    }

    if(this.form().controls['password'].value != this.form().controls['rpassword'].value){
      alert("Contraseñas no son iguales");
      return;
    }

    const clientForm: Client = {
      id: 0,
      name: '',
      address: '',
      gender: '',
      age: 0,
      phoneNumber: '',
      status: true,
      password: '',
      identification: ''
    };

    clientForm.address = this.form().controls['address'].value;
    clientForm.name = this.form().controls['name'].value;
    clientForm.gender = this.form().controls['gender'].value;
    clientForm.age = this.form().controls['age'].value;
    clientForm.phoneNumber = this.form().controls['phoneNumber'].value;
    clientForm.password = this.form().controls['password'].value;
    clientForm.identification = this.form().controls['identification'].value;
    clientForm.id = this.idClientUpdata;

    if(this.updateClient){
      console.log(clientForm)
      this.clientService.updateClient(clientForm).subscribe((response)=>{
        alert("Cliente Actualizado");
        
      })
    }else{
      this.clientService.createClient(clientForm).subscribe((response)=>{
        alert("Cliente Registrado");
      })
    }

    this.modal.nativeElement.close();
    this.form().reset();
    
  }

  

  openModal(client?:Client) {
    if(client){

     this.updateClient = true;
     this.idClientUpdata = client.id;
     this.form().controls['address'].setValue(client.address);
     this.form().controls['name'].setValue(client.name);
     this.form().controls['gender'].setValue(client.gender);
     this.form().controls['age'].setValue(client.age);
     this.form().controls['phoneNumber'].setValue(client.phoneNumber);
     this.form().controls['password'].setValue(client.password);
     this.form().controls['identification'].setValue(client.identification);
    }else{
      this.idClientUpdata = 0;
      this.updateClient = false;
      this.form().reset();
    }
    this.modal.nativeElement.showModal();
  }

  closeModal(modal: any) {
    modal.close();
  }

  deleteClient(client:Client){
    this.clientService.deleteClient(client).subscribe(respose=>{
      console.log(respose);
      this.getClients();
    })
  }
}
