import {Component, Input, OnInit, SimpleChanges} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

export class TableColumn {
  columnDef: string;
  header: string;
  cell: (_: any) => any;
  cellButton?: (_: any) => string;

  constructor(columnDef: string,
              header: string,
              cell: (_: any) => any,
              cellButton?: (_: any) => string) {
    this.columnDef = columnDef;
    this.header = header;
    this.cell = cell;
    this.cellButton = cellButton;
  }
}
@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.less'],
})
export class TableComponent implements OnInit {
  @Input() tableColumn?: TableColumn[];
  @Input() tableData?: any[];

  columns?: TableColumn[];
  displayedColumns?: string[];

  dataSource?: MatTableDataSource<any>;


  ngOnInit() {
    this.dataSource = new MatTableDataSource<any>(this.tableData);
    this.columns = this.tableColumn;
    this.displayedColumns = this.columns!.map(c => c.columnDef);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['tableData']) {
      this.dataSource = new MatTableDataSource<any>(this.tableData);
    }
  }
}
