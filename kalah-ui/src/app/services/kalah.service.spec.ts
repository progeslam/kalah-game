import { TestBed } from '@angular/core/testing';

import { KalahService } from './kalah.service';

describe('KalahService', () => {
  let service: KalahService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(KalahService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
