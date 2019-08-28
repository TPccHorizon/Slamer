import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AverageResponseTimeComponent } from './average-response-time.component';

describe('AverageResponseTimeComponent', () => {
  let component: AverageResponseTimeComponent;
  let fixture: ComponentFixture<AverageResponseTimeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AverageResponseTimeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AverageResponseTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
