import {TestBed} from '@angular/core/testing';

import {EquipmentService} from './equipment.service';
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {Equipment} from "../model/equipment.model";

describe('EquipmentService', () => {
  let service: EquipmentService;
  let httpClient: HttpTestingController;


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [],
    });
    service = TestBed.inject(EquipmentService);
    httpClient = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('retrieveAllEquipments', () => {
    it('Should success', () => {
      const expected = [{id: 'xxx'} as any as Equipment];

      service.retrieveAllEquipments().subscribe(
        (res) => {
          expect(res).toBe(expected);
        },
        () => {
          throw new Error('should not be reached');
        }
      );
      const req = httpClient.expectOne('http://localhost:8080/equipment-service/v1/equipment/search/20');
      req.flush(expected);
      expect(req.request.method).toBe('GET');
      httpClient.verify();
    });

    it('Should return error if req fails', () => {
      const errorText = 'Server error';
      const errorCode = 500;
      service.retrieveAllEquipments().subscribe(
        () => {
          throw Error('should not be reached');
        },
        (error) => {
          expect(error.status).toBe(errorCode);
          expect(error.statusText).toBe(errorText);
        }
      );
      const req = httpClient.expectOne('http://localhost:8080/equipment-service/v1/equipment/search/20');
      req.error(new ErrorEvent(''), {status: errorCode, statusText: errorText});
      expect(req.request.method).toBe('GET');
      httpClient.verify();
    });
  });

  describe('searchById', () => {
    it('Should success', () => {
      const expected = {id: 'xxx'} as any as Equipment;

      service.searchById('xxx').subscribe(
        (res) => {
          expect(res).toBe(expected);
        },
        () => {
          throw new Error('should not be reached');
        }
      );
      const req = httpClient.expectOne('http://localhost:8080/equipment-service/v1/equipment/xxx');
      req.flush(expected);
      expect(req.request.method).toBe('GET');
      httpClient.verify();
    });

    it('Should return error if req fails', () => {
      const errorText = 'Server error';
      const errorCode = 500;
      service.searchById('xxx').subscribe(
        () => {
          throw Error('should not be reached');
        },
        (error) => {
          expect(error.status).toBe(errorCode);
          expect(error.statusText).toBe(errorText);
        }
      );
      const req = httpClient.expectOne('http://localhost:8080/equipment-service/v1/equipment/xxx');
      req.error(new ErrorEvent(''), {status: errorCode, statusText: errorText});
      expect(req.request.method).toBe('GET');
      httpClient.verify();
    });
  });

})
;
