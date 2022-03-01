import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ListEquipmentComponent} from './list-equipment.component';
import {TableComponent} from "../../shared/table/table.component";
import {EquipmentService} from "../service/equipment.service";
import {of} from "rxjs";
import {Equipment, Status} from "../model/equipment.model";
import {SharedModule} from "../../shared/shared.module";

describe('ListEquipmentComponent', () => {
  let component: ListEquipmentComponent;
  let fixture: ComponentFixture<ListEquipmentComponent>;
  let equipmentService: jasmine.SpyObj<EquipmentService>;

  const equipments = [{id: '1111', status: Status.STOPPED} as any as Equipment]

  beforeEach(() => {

    equipmentService = jasmine.createSpyObj('equipmentService', ['retrieveAllEquipments']);

    equipmentService.retrieveAllEquipments.and.returnValue(of(equipments));

  });

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListEquipmentComponent, TableComponent],
      imports: [SharedModule],
      providers: [
        {
          provide: EquipmentService,
          useValue: equipmentService,
        }
      ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListEquipmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should call equipmentService', () => {
    expect(equipmentService.retrieveAllEquipments).toHaveBeenCalled();
  });

  it('should have value set in equipmentTableData', () => {
    expect(component.equipmentTableData).toBe(equipments);
  });
});
