import { CommonModule } from '@angular/common';
import { Component, input, output  } from '@angular/core';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-navbar',
  imports: [RouterModule, CommonModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  isLeftSidebarCollapsed = input.required<boolean>();
  changeIsLeftSidebarCollapsed = output<boolean>();
  items = [
    {
      routeLink: 'client',
      icon: 'fal fa-home',
      label: 'Clientes',
    },
    {
      routeLink: 'account',
      icon: 'fal fa-box-open',
      label: 'Cuentas',
    },
    {
      routeLink: 'movement',
      icon: 'fal fa-file',
      label: 'Movimientos',
    },
    {
      routeLink: 'report',
      icon: 'fal fa-cog',
      label: 'Reportes',
    },
  ];

  toggleCollapse(): void {
    this.changeIsLeftSidebarCollapsed.emit(!this.isLeftSidebarCollapsed());
  }

  closeSidenav(): void {
    this.changeIsLeftSidebarCollapsed.emit(true);
  }
}
