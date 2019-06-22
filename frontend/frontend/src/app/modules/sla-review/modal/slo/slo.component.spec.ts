import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SloComponent } from './slo.component';

describe('SloComponent', () => {
  let component: SloComponent;
  let fixture: ComponentFixture<SloComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SloComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
