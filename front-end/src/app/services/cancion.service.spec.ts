import { TestBed, inject } from '@angular/core/testing';

import { CancionService } from './cancion.service';

describe('CancionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CancionService]
    });
  });

  it('should be created', inject([CancionService], (service: CancionService) => {
    expect(service).toBeTruthy();
  }));
});
