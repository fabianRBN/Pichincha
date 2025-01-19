import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountComponent } from './account.component';
import { AccountService } from '../../core/services/account/account.service';
import { HttpClientModule  } from '@angular/common/http';
import { of } from 'rxjs';
const accountServiceMock ={
  getAccounts: jest.fn(),
  createAccount: jest.fn(),
  updateAccount: jest.fn()
}
describe('AccountComponent', () => {
  let component: AccountComponent;
  let fixture: ComponentFixture<AccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports:[AccountComponent,HttpClientModule ],
      providers:[{provide:AccountService, useValue: accountServiceMock}]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccountComponent);
    component = fixture.componentInstance;
    component.client.id = 1;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

});
