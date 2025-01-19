import { Component, inject, signal,ViewChild  } from '@angular/core';

import { ClientService } from '../../core/services/client/client.service';
import { Client } from '../../core/interfaces/client.interface';
import { AccountService } from '../../core/services/account/account.service';
import { Account } from '../../core/interfaces/account.interface';
import { FormsModule ,FormControl , FormGroup, ReactiveFormsModule, Validators} from '@angular/forms'; 
import { MovementService } from '../../core/services/movement/movement.service';
import { Movement } from '../../core/interfaces/movement.interface';
import { AccountMovement } from '../../core/interfaces/accountMovement.interface';
import generatePDF from '../../lib/pdfmake';
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
  movementList: AccountMovement[] =[];
  movementListReporte: AccountMovement[] =[];
  startDate: Date = new Date();
  endDate: Date = new Date();
  page: number = 0;
  size: number = 2;
  totalElements: number = 10;
  pages: number[] = []; 
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



  getMovementfindByDateBetween(){
    this.movementService.getMovementfindByDateBetween(this.startDate,this.endDate,this.size,this.page).subscribe(response=>{
      console.log(response);
      this.movementList = response.data;
      this.totalElements = response.totalElements;
      this.pages = Array.from(
        { length: Math.ceil(this.totalElements / this.size) },
        (_, i) => i
      );
    })
  }

  onPageChange(event: any): void {
    this.page = event.pageIndex;
    this.size = event.pageSize;
    this.getMovementfindByDateBetween();
  }

  goToPage(page: number): void {
    this.page = page;
    this.getMovementfindByDateBetween();
  }

  nextPage(): void {
    if (this.page < this.pages.length - 1) {
      this.page++;
      this.getMovementfindByDateBetween();
    }
  }

  previousPage(): void {
    if (this.page > 0) {
      this.page--;
      this.getMovementfindByDateBetween();
    }
  }

  onGeneratePDF(){

    const reciboNo = '123456789'

    const fecha = '07 de Marzo de 2024'

    this.movementService.getMovementfindByDateBetween(this.startDate,this.endDate,this.totalElements,0).subscribe(response=>{
      this.movementListReporte= response.data;
      generatePDF(this.movementListReporte, reciboNo, fecha);
   
    })

 
  }
}
