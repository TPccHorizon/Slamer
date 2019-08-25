import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MonitoringRegistrationComponent } from './monitoring-registration.component';

describe('MonitoringRegistrationComponent', () => {
  let component: MonitoringRegistrationComponent;
  let fixture: ComponentFixture<MonitoringRegistrationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonitoringRegistrationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonitoringRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
