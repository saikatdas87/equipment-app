import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {DashboardComponent} from "./equipment/dashboard/dashboard.component";
import {RouterModule, Routes} from "@angular/router";
import {SharedModule} from "./shared/shared.module";
import {EquipmentModule} from "./equipment/equipment.module";
import {HttpClientModule} from "@angular/common/http";
import {SearchEquipmentComponent} from "./equipment/search-equipment/search-equipment.component";


const routes: Routes = [  {
  path: 'dashboard',
  component: DashboardComponent,
},
  {
    path: '**',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  }];

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    SharedModule,
    EquipmentModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AppModule { }
