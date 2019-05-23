import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ThroughputFormComponent } from './throughput-form.component';

describe('ThroughputFormComponent', () => {
  let component: ThroughputFormComponent;
  let fixture: ComponentFixture<ThroughputFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ThroughputFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ThroughputFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
