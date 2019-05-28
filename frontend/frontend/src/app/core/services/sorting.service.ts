import { Injectable } from '@angular/core';
import {Sort} from "@angular/material";
import {Sla} from "../../shared/models/sla";

@Injectable({
  providedIn: 'root'
})
export class SortingService {

  constructor() { }

  sortData(slas: Sla[], sort: Sort) {
    console.log("Sorting");
    const data = slas.slice();
    if (!sort.active || sort.direction === '') {
      return data;
    }

    return data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'id': return compare(a.id, b.id, isAsc);
        case 'title': return compare(a.title, b.title, isAsc);
        case 'status': return compare(a.status, b.status, isAsc);
        case 'start': return compare(a.validFrom, b.validFrom, isAsc);
        case 'end': return compare(a.validTo, b.validTo, isAsc);
        case 'sp': return compare(a.serviceProviderId, b.serviceProviderId, isAsc);
        case 'customer': return compare(a.serviceCustomerId, b.serviceCustomerId, isAsc);
        case 'phase': return compare(a.lifecyclePhase, b.lifecyclePhase, isAsc);
        default: return 0;
      }
    });

    function compare(a: number | string | Date, b: number | string | Date, isAsc: boolean) {
      console.log("a: " + a + ", type: " + typeof a);
      return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
    }
  }
}
