import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Equipment} from "../model/equipment.model";

@Injectable({
  providedIn: 'root'
})
export class EquipmentService {
  private host = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  public retrieveAllEquipments(limit: number = 20): Observable<Equipment[]> {
    return this.http.get<Equipment[]>(`${this.host}/equipment-service/v1/equipment/search/${limit}`);
  }

  public searchById(id : String) : Observable<Equipment>{
    return this.http.get<Equipment>(`${this.host}/equipment-service/v1/equipment/${id}`);
  }

}
