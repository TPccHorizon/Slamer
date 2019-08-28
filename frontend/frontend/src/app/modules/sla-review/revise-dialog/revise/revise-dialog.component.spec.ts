import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviseDialogComponent } from './revise-dialog.component';

describe('ReviseDialogComponent', () => {
  let component: ReviseDialogComponent;
  let fixture: ComponentFixture<ReviseDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviseDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviseDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
