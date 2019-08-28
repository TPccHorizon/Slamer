import { TestBed } from '@angular/core/testing';

import { SloService } from './slo.service';

describe('SloService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SloService = TestBed.get(SloService);
    expect(service).toBeTruthy();
  });
});
