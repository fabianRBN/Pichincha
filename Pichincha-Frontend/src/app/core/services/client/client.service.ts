import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { BaseHttpService } from "../base-http.service";
import { Observable } from "rxjs";
import { BaseResponse } from "../../interfaces/baseresponse.inteface";
import { Client } from "../../interfaces/client.interface";


@Injectable()
export class ClientService extends BaseHttpService{

    getClients(page:Number, size:Number, name:string):Observable<BaseResponse>{
        return this.http.get<BaseResponse>(`${this.apiUrl}/client-crud/client/search?page=${page}&size=${size}&name=${name}`)
    }

    createClient(client:Client):Observable<BaseResponse>{
        return this.http.post<any>(`${this.apiUrl}/client-crud/client`, client)
    }

    updateClient(client:Client):Observable<BaseResponse>{
        return this.http.put<any>(`${this.apiUrl}/client-crud/client/${client.id}`, client)
    }

    deleteClient(client:Client):Observable<BaseResponse>{
        return this.http.delete<any>(`${this.apiUrl}/client-crud/client/${client.id}`)
    }

    findClientByIdentification( identification:string):Observable<BaseResponse>{
        return this.http.get<BaseResponse>(`${this.apiUrl}/client-crud/client/findByPersonIdentification?identification=${identification}`)
    }

}