import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookingDisplaySummaryComponent } from './booking-display-summary.component';

describe('BookingDisplaySummaryComponent', () => {
  let component: BookingDisplaySummaryComponent;
  let fixture: ComponentFixture<BookingDisplaySummaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookingDisplaySummaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookingDisplaySummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
