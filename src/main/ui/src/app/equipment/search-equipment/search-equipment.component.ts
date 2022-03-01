import {Component, OnInit} from '@angular/core';
import {Equipment} from "../model/equipment.model";
import {TableColumn} from "../../shared/table/table.component";
import {equipmentColumns} from "../dashboard/dashboard.component";
import {EquipmentService} from "../service/equipment.service";

@Component({
  selector: 'app-search-equipment',
  templateUrl: './search-equipment.component.html',
  styleUrls: ['./search-equipment.component.less']
})
export class SearchEquipmentComponent implements OnInit {
  equipmentTableData: Equipment[] = [];
  equipmentColumns?: TableColumn[] = equipmentColumns;

  constructor(private equipmentService: EquipmentService) {
  }

  ngOnInit(): void {
  }

  findEquipment(target: any) {
    if (target.value && target.value !== '') {
      this.equipmentService.searchById(target.value).subscribe(equipment => {
        if (null !== equipment) {
          this.equipmentTableData = Array(equipment);
        }
        else {
          this.equipmentTableData = [];
        }
      }, error => {
        //TODO - handle
      })
    }
  }
}
