import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SearchEquipmentComponent} from './search-equipment.component';
import {ListEquipmentComponent} from "../list-equipment/list-equipment.component";
import {EquipmentService} from "../service/equipment.service";
import {Equipment, Status} from "../model/equipment.model";
import {of} from "rxjs";
import {SharedModule} from "../../shared/shared.module";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {CommonModule} from "@angular/common";

describe('SearchEquipmentComponent', () => {
  let component: SearchEquipmentComponent;
  let fixture: ComponentFixture<SearchEquipmentComponent>;
  let equipmentService: jasmine.SpyObj<EquipmentService>;

  const equipment = {id: '1111', status: Status.STOPPED} as any as Equipment

  beforeEach(() => {

    equipmentService = jasmine.createSpyObj('equipmentService', ['searchById']);

    equipmentService.searchById.and.returnValue(of(equipment));

  });
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SearchEquipmentComponent, ListEquipmentComponent],
      imports: [SharedModule, HttpClientTestingModule, MatInputModule,
        FormsModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        BrowserAnimationsModule,
        MatAutocompleteModule,
        CommonModule],
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
    fixture = TestBed.createComponent(SearchEquipmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should display search box', () => {
    const searchBox = fixture.nativeElement.querySelectorAll('.equipment-search-box')[0];

    expect(searchBox).toBeTruthy();
  });

  it('should search equipment when asked', () => {
    component.findEquipment({value: '1111'});
    fixture.detectChanges();

    expect(equipmentService.searchById).toHaveBeenCalledWith('1111');
  });
});
