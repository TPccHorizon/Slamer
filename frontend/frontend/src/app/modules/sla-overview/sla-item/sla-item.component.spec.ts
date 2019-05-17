import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SlaItemComponent } from './sla-item.component';

describe('SlaItemComponent', () => {
  let component: SlaItemComponent;
  let fixture: ComponentFixture<SlaItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SlaItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SlaItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
