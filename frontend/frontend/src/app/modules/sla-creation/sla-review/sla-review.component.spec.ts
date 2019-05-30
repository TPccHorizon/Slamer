import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SlaReviewComponent } from './sla-review.component';

describe('SlaReviewComponent', () => {
  let component: SlaReviewComponent;
  let fixture: ComponentFixture<SlaReviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SlaReviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SlaReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
