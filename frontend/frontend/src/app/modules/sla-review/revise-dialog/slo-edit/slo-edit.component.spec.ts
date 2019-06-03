import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SloEditComponent } from './slo-edit.component';

describe('SloEditComponent', () => {
  let component: SloEditComponent;
  let fixture: ComponentFixture<SloEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SloEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SloEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
