import {ServiceLevelObjective} from "./serviceLevelObjective";

export class Throughput extends ServiceLevelObjective{
  dataSize: number;
  dataUnit: string;
  operator: string;
  thresholdValue: number;
  thresholdUnit: string;
}
