import { TestBed } from '@angular/core/testing';

import { GanacheService } from './ganache.service';

describe('GanacheService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GanacheService = TestBed.get(GanacheService);
    expect(service).toBeTruthy();
  });
});
