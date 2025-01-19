import { inject, Injectable } from "@angular/core";
import { BaseHttpService } from "../base-http.service";
import { Observable } from "rxjs";
import { BaseResponse } from "../../interfaces/baseresponse.inteface";
import { Movement } from "../../interfaces/movement.interface";
@Injectable()
export class MovementService extends BaseHttpService{
    createMovement(account: Movement):Observable<BaseResponse>{
        return this.http.post<BaseResponse>(`${this.apiUrl}/client-crud/transaction`,account)
    }

    getMovement(idCliente: Number):Observable<BaseResponse>{
        return this.http.get<BaseResponse>(`${this.apiUrl}/client-crud/transaction/client/${idCliente}`)
    }

    getMovementfindByDateBetween(startDate: Date, endDate :Date, size:number, page:number):Observable<BaseResponse>{
        return this.http.get<BaseResponse>(`${this.apiUrl}/client-crud/transaction/findByDateBetween?page=${page}&size=${size}&start=${startDate}&end=${endDate}`)
    }

}