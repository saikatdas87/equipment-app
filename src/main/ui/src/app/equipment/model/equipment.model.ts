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
