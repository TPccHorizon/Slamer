import {Component, OnInit, ViewChild} from '@angular/core';
import {SlaService} from "../../../core/services/sla.service";
import {Sla} from "../../../shared/models/sla";
import {first} from "rxjs/operators";
import {MatSort, MatTableDataSource, Sort} from "@angular/material";
import {SortingService} from "../../../core/services/sorting.service";
import {SlaAndParties} from "../../../shared/models/slaAndParties";

@Component({
  selector: 'app-sla-overview',
  templateUrl: './sla-overview.component.html',
  styleUrls: ['./sla-overview.component.css']
})
export class SlaOverviewComponent implements OnInit {

  loading = false;
  slas: SlaAndParties[] = null;
  dataSource : MatTableDataSource<Sla>;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private slaService: SlaService, private sorter: SortingService) {
    this.loading = true;
    this.slaService.getMySlas().pipe(first())
      .subscribe(data => {
        this.slas = data;
        this.dataSource = new MatTableDataSource<Sla>(this.slas);
        this.dataSource.sort = this.sort;
        this.loading = false;
      }, error => {
        console.log(error);
        this.loading = false;
      })
  }

  sortSlas(sort: Sort) {
    console.log("SortSlas");
    this.slas = this.sorter.sortData(this.slas, sort);
  }



  ngOnInit() {
  }

}
