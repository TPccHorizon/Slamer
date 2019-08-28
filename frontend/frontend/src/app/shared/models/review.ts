import {SlaReview} from "./SlaReview";
import {ServiceLevelObjective} from "./serviceLevelObjective";

export class Review {
  slaId: number;
  validFrom: SlaReview;
  validTo: SlaReview;
  servicePrice: SlaReview;
  slos: ServiceLevelObjective[];
}
