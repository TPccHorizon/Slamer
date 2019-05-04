import { TestBed } from '@angular/core/testing';

import { SlaUserService } from './sla-user.service';

describe('SlaUserService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SlaUserService = TestBed.get(SlaUserService);
    expect(service).toBeTruthy();
  });
});
