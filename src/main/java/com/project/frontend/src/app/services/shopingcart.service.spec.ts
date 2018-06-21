import { TestBed, inject } from '@angular/core/testing';

import { ShopingcartService } from './shopingcart.service';

describe('ShopingcartService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ShopingcartService]
    });
  });

  it('should be created', inject([ShopingcartService], (service: ShopingcartService) => {
    expect(service).toBeTruthy();
  }));
});
