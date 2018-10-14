import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAllRoomsComponent } from './list-all-rooms.component';

describe('ListAllRoomsComponent', () => {
  let component: ListAllRoomsComponent;
  let fixture: ComponentFixture<ListAllRoomsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListAllRoomsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListAllRoomsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
