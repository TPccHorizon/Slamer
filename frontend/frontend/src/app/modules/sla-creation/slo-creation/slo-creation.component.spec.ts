import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SloCreationComponent } from './slo-creation.component';

describe('SloCreationComponent', () => {
  let component: SloCreationComponent;
  let fixture: ComponentFixture<SloCreationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SloCreationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SloCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
