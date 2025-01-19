import { inject, Injectable } from "@angular/core";
import { BaseHttpService } from "../base-http.service";
import { Observable } from "rxjs";
import { BaseResponse } from "../../interfaces/baseresponse.inteface";
import { Account } from "../../interfaces/account.interface";
@Injectable()
export class AccountService extends BaseHttpService{

    getAccounts(clientId: number):Observable<BaseResponse>{
        return this.http.get<BaseResponse>(`${this.apiUrl}/client-crud/account/client/${clientId}`)
    }

    createAccount(account: Account):Observable<BaseResponse>{
        return this.http.post<BaseResponse>(`${this.apiUrl}/client-crud/account`,account)
    }

    updateAccount(account: Account):Observable<BaseResponse>{
        return this.http.put<BaseResponse>(`${this.apiUrl}/client-crud/account/${account.id}`,account)
    }
}