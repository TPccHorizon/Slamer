import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SlaDetailsComponent } from './sla-details.component';

describe('SlaDetailsComponent', () => {
  let component: SlaDetailsComponent;
  let fixture: ComponentFixture<SlaDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SlaDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SlaDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
