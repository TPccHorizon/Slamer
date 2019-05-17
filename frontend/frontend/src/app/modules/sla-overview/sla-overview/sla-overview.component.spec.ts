import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SlaOverviewComponent } from './sla-overview.component';

describe('SlaOverviewComponent', () => {
  let component: SlaOverviewComponent;
  let fixture: ComponentFixture<SlaOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SlaOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SlaOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
