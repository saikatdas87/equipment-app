import {Component, ElementRef, Input, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {Equipment, equipmentColumns} from "../model/equipment.model";
import {TableColumn} from "../../shared/table/table.component";
import {EquipmentService} from "../service/equipment.service";

@Component({
  selector: 'app-search-equipment',
  templateUrl: './search-equipment.component.html',
  styleUrls: ['./search-equipment.component.less']
})
export class SearchEquipmentComponent implements OnInit {

  @Input() tabIndex = 0;
  equipmentTableData: Equipment[] = [];
  equipmentColumns?: TableColumn[] = equipmentColumns;
  @ViewChild('searchEquipment') equipmentIdInput: ElementRef | undefined;

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
      });
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['tabIndex']) {
      if (changes['tabIndex'].currentValue === 0) {
        this.equipmentTableData = [];
        // @ts-ignore
        this.equipmentIdInput?.nativeElement.value = '';
      }
    }
  }

}
