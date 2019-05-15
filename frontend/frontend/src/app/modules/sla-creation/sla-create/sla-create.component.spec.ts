import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SlaCreateComponent } from './sla-create.component';

describe('SlaCreateComponent', () => {
  let component: SlaCreateComponent;
  let fixture: ComponentFixture<SlaCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SlaCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SlaCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
