import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AverageResponseFormComponent } from './average-response-form.component';

describe('AverageResponseFormComponent', () => {
  let component: AverageResponseFormComponent;
  let fixture: ComponentFixture<AverageResponseFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AverageResponseFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AverageResponseFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
