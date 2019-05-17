import {Sla} from "./sla";
import {SlaUser} from "./slaUser";

export class SlaWithParties {
  sla: Sla;
  serviceProvider: SlaUser;
  serviceCustomer: SlaUser;
}
