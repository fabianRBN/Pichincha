import { TestBed } from "@angular/core/testing";
import { HttpClient } from "@angular/common/http";
import { AccountService } from "./account.service";
import { of } from "rxjs";
import { Account } from "../../interfaces/account.interface";

const accountListMock =[
    {
      "data": [
          {
              "id": 1,
              "accountNumber": "1234567890",
              "accountType": "Ahorros",
              "initialBalance": 1000.5,
              "status": true
          }
      ],
      "success": true,
      "message": null,
      "error": null,
      "totalElements": null
  }
]

const accountMock: Account = {

    "id": 1,
    "accountNumber": "123456789314",
    "accountType": "Ahorros",
    "initialBalance": 1000.5,
    "status": true,
    "clientId": 1
 
};

const httpClientMock = {
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
};

describe('AccountComponent', () => {
  let service: AccountService;
  beforeEach(()=>{
    TestBed.configureTestingModule({
      providers:[
        AccountService,
        {provide:HttpClient, useValue:httpClientMock}]
    });
    service = TestBed.inject(AccountService);
    httpClientMock.get.mockReturnValue(accountListMock);
  })

  it('getAccounts',()=>{
    service.getAccounts(1);
    expect(httpClientMock.get).toHaveBeenCalled();
  });

  it("should handle getAccounts success response", (done) => {
    httpClientMock.get.mockReturnValue(of(accountListMock));
    service.getAccounts(1).subscribe((response) => {
      expect(response).toEqual(accountListMock);
      done();
    });
  });

  it("should handle createAccount success response", (done) => {
    httpClientMock.post.mockReturnValue(of({ success: true }));
    service.createAccount(accountMock).subscribe((response) => {
      expect(response.success).toBe(true);
      done();
    });
  });
  


});
