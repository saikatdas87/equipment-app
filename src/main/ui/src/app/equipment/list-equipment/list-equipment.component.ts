import { Component, OnInit } from '@angular/core';
import {Equipment, equipmentColumns} from "../model/equipment.model";
import {TableColumn} from "../../shared/table/table.component";
import {EquipmentService} from "../service/equipment.service";

@Component({
  selector: 'app-list-equipment',
  templateUrl: './list-equipment.component.html',
  styleUrls: ['./list-equipment.component.less']
})
export class ListEquipmentComponent implements OnInit {

  equipmentTableData ?: Equipment[];
  equipmentColumns?: TableColumn[] = equipmentColumns;
  constructor(private equipmentService: EquipmentService) {
  }

  ngOnInit(): void {
    this.equipmentService.retrieveAllEquipments().subscribe(equipments => {
      this.equipmentTableData = equipments;
    });
  }

}
