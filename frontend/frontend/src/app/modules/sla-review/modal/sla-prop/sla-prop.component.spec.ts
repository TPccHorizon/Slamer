import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SlaPropComponent } from './sla-prop.component';

describe('SlaPropComponent', () => {
  let component: SlaPropComponent;
  let fixture: ComponentFixture<SlaPropComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SlaPropComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SlaPropComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
