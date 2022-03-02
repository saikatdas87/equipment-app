import {Component} from '@angular/core';
import {MatTabChangeEvent} from "@angular/material/tabs";


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.less']
})
export class DashboardComponent {
  appTitle = 'Equipments';
  tabIndex: number = 0;

  tabChanged = (tabChangeEvent: MatTabChangeEvent): void => {
    this.tabIndex = tabChangeEvent.index;
  }
}
