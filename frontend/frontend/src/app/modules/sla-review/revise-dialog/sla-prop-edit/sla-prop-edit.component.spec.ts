import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SlaPropEditComponent } from './sla-prop-edit.component';

describe('SlaPropEditComponent', () => {
  let component: SlaPropEditComponent;
  let fixture: ComponentFixture<SlaPropEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SlaPropEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SlaPropEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
