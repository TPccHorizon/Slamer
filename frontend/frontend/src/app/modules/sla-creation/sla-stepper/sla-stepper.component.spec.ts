import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SlaStepperComponent } from './sla-stepper.component';

describe('SlaStepperComponent', () => {
  let component: SlaStepperComponent;
  let fixture: ComponentFixture<SlaStepperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SlaStepperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SlaStepperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
