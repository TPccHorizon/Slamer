import { TestBed } from '@angular/core/testing';

import { StepperFormManagementService } from './stepper-form-management.service';

describe('StepperFormManagementService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: StepperFormManagementService = TestBed.get(StepperFormManagementService);
    expect(service).toBeTruthy();
  });
});
