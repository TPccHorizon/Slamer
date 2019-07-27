import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivateDialogComponent } from './activate-dialog.component';

describe('ActivateDialogComponent', () => {
  let component: ActivateDialogComponent;
  let fixture: ComponentFixture<ActivateDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivateDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivateDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
