import { ComponentFixture, TestBed } from '@angular/core/testing';

import {TableColumn, TableComponent} from './table.component';
import {CommonModule} from "@angular/common";
import {MatTableModule} from "@angular/material/table";
import {CdkTableModule} from "@angular/cdk/table";
import {Equipment, Status} from "../../equipment/model/equipment.model";

describe('TableComponent', () => {
  let component: TableComponent;
  let fixture: ComponentFixture<TableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TableComponent ],
      imports: [
        CommonModule,
        MatTableModule,
        CdkTableModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TableComponent);
    component = fixture.componentInstance;
    component.tableColumn = [
      new TableColumn('id', 'ID', (element: Equipment) => `${element.id}`),
      new TableColumn('status', 'STATUS', (element: Equipment) => `${element.status}`)
    ]
    component.tableData = [{id: 'test-22', status: Status.STOPPED} as any as Equipment];
    fixture.detectChanges();
  });

  it('should display tabular data', () => {
    const id = fixture.nativeElement.querySelectorAll('#uit-column-id')[0];
    const status = fixture.nativeElement.querySelectorAll('#uit-column-status')[0];
    expect(id).toBeDefined();
    expect(id.textContent).toBe('test-22');
    expect(status.textContent).toBe('STOPPED');
  });
});
