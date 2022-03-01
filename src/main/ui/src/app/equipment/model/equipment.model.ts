import {TableColumn} from "../../shared/table/table.component";

export interface Equipment {
  id: String;
  countryCode: String;
  address : Address;
  status: Status;
  contractStartDate: Date;
  contractEndDate: Date
}

export interface Address {
  addressLine1 : String;
  addressLine2 : String;
  city: String;
  country: String;
  postalCode: String;
}

export enum Status {
  RUNNING = 'RUNNING', STOPPED = 'STOPPED'
}


export const equipmentColumns: TableColumn[] = [
  new TableColumn('id', 'ID', (element: Equipment) => `${element.id}`),
  new TableColumn('contractStartDate', 'START DATE', (element: Equipment) => `${formatDate(element.contractStartDate)}`),
  new TableColumn('contractEndDate', 'END DATE', (element: Equipment) => `${formatDate(element.contractEndDate)}`),
  new TableColumn('address', 'Address', (element: Equipment) => `${addressFormatter(element.address)}`),
  new TableColumn('status', 'STATUS', (element: Equipment) => `${element.status}`)
];

export function addressFormatter(address : Address) : String{
  return (address !== null && address !== undefined) ? Object.values(address).filter(_ => _ !== null).join(', '): '';
}

export function formatDate(date : Date): String {
  return date !== null ? new Date(date).toDateString(): '';
}
