import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UptimeFormComponent } from './uptime-form.component';

describe('UptimeFormComponent', () => {
  let component: UptimeFormComponent;
  let fixture: ComponentFixture<UptimeFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UptimeFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UptimeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
