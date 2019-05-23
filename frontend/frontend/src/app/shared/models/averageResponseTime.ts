import {ServiceLevelObjective} from "./serviceLevelObjective";

export class AverageResponseTime extends ServiceLevelObjective{
  averageResponseTime: number;
  timeUnit: string;
}
