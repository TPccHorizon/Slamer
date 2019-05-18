import {Sla} from "./sla";
import {SlaUser} from "./slaUser";

export class SlaAndParties extends Sla{
  serviceProvider: SlaUser;
  serviceCustomer: SlaUser;
}
