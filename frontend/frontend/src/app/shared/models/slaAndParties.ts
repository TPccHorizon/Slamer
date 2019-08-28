import {Sla} from "./sla";
import {SlaUser} from "./slaUser";
import {ServiceLevelObjective} from "./serviceLevelObjective";

export interface SlaAndParties extends Sla{
  serviceProvider: SlaUser;
  serviceCustomer: SlaUser;
  monitoringService: SlaUser;
  slos: ServiceLevelObjective[];
}
