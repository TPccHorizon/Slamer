import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SloOverviewComponent } from './slo-overview.component';

describe('SloOverviewComponent', () => {
  let component: SloOverviewComponent;
  let fixture: ComponentFixture<SloOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SloOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SloOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
