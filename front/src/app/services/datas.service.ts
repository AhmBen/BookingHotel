import { Injectable } from '@angular/core';
import { Room } from '../models/room';
import { Booking } from '../models/booking';

@Injectable({
  providedIn: 'root'
})
export class DatasService {

  public checkRoomForm: any;
  public bookingParam = {dateIn: '', dateOut: '', nbrAdults: 0, nbrChildrens: 0};
  public choosingRoom: Room;
  public valideBooking: Booking;
  public displayBookingParam = {numBooking: '', name: '' };

  constructor() {
  }
}
