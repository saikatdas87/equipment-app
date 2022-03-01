import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import {SharedModule} from "../shared/shared.module";
import {MatCardModule} from "@angular/material/card";
import {MatTabsModule} from "@angular/material/tabs";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import { SearchEquipmentComponent } from './search-equipment/search-equipment.component';
import {ListEquipmentComponent} from "./list-equipment/list-equipment.component";

@NgModule({
  declarations: [
    DashboardComponent,
    SearchEquipmentComponent,
    ListEquipmentComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    MatCardModule,
    MatTabsModule,
    MatFormFieldModule,
    MatInputModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA]
})
export class EquipmentModule { }
