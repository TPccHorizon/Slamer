import { TestBed } from '@angular/core/testing';

import { SlaService } from './sla.service';

describe('SlaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SlaService = TestBed.get(SlaService);
    expect(service).toBeTruthy();
  });
});
