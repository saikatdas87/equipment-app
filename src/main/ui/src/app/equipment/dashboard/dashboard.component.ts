import {Component, OnInit} from '@angular/core';
import {TableColumn} from "../../shared/table/table.component";
import {Address, Equipment} from "../model/equipment.model";
import {EquipmentService} from "../service/equipment.service";

export const equipmentColumns: TableColumn[] = [
  new TableColumn('id', 'ID', (element: Equipment) => `${element.id}`),
  new TableColumn('contractStartDate', 'START DATE', (element: Equipment) => `${formatDate(element.contractStartDate)}`),
  new TableColumn('contractEndDate', 'END DATE', (element: Equipment) => `${formatDate(element.contractEndDate)}`),
  new TableColumn('address', 'Address', (element: Equipment) => `${addressFormatter(element.address)}`),
  new TableColumn('status', 'STATUS', (element: Equipment) => `${element.status}`)
];

export function addressFormatter(address : Address) : String{
  return Object.values(address).filter(_ => _ !== null).join(', ');
}

export function formatDate(date : Date): String {
  return new Date(date).toDateString();
}
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.less']
})
export class DashboardComponent implements OnInit {
  equipmentTableData ?: Equipment[];
  equipmentColumns?: TableColumn[] = equipmentColumns;
  constructor(private equipmentService: EquipmentService) {
  }

  ngOnInit(): void {
     this.equipmentService.retrieveAllCountries().subscribe(e => {
       this.equipmentTableData = e;
    });
  }



}
