import { Routes } from '@angular/router';
import { ClientComponent } from './views/client/client.component';
import { MovementComponent } from './views/movement/movement.component';
import { AccountComponent } from './views/account/account.component';
import { ReportComponent } from './views/report/report.component';

export const routes: Routes = [
    { path: '', redirectTo: 'client', pathMatch: 'full' },
    { path: 'client', component: ClientComponent },
    { path: 'movement', component: MovementComponent },
    { path: 'account', component: AccountComponent },
    { path: 'report', component: ReportComponent },

  ];