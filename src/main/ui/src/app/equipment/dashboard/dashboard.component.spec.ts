import {ComponentFixture, TestBed} from '@angular/core/testing';

import {DashboardComponent} from './dashboard.component';
import {ListEquipmentComponent} from "../list-equipment/list-equipment.component";
import {SearchEquipmentComponent} from "../search-equipment/search-equipment.component";
import {MatCardModule} from "@angular/material/card";
import {MatTabGroup, MatTabsModule} from "@angular/material/tabs";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {MatFormFieldModule} from "@angular/material/form-field";
import {TableComponent} from "../../shared/table/table.component";
import {SharedModule} from "../../shared/shared.module";
import {BrowserAnimationsModule, NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MatInputModule} from "@angular/material/input";
import {By} from "@angular/platform-browser";

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [
        DashboardComponent,
        ListEquipmentComponent,
        SearchEquipmentComponent,
        TableComponent],
      imports: [
        MatCardModule,
        MatTabsModule,
        HttpClientTestingModule,
        MatFormFieldModule,
        SharedModule,
        BrowserAnimationsModule,
        MatInputModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
    component.appTitle = 'Test Title';
    fixture.detectChanges();
  });

  it('should contain title', () => {
    const title = fixture.nativeElement.querySelectorAll('#equipment-app-title')[0];
    expect(title.textContent).toBe('Test Title');
  });

  it('should include list as initial displayed tab', () => {
    const list = fixture.nativeElement.querySelectorAll('app-list-equipment')[0];
    const search = fixture.nativeElement.querySelectorAll('app-search-equipment')[0];
    expect(list).toBeTruthy();
    expect(search).toBeUndefined();
  });

});
