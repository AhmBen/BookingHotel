import { Component, OnInit } from '@angular/core';
import { DatasService } from './../../services/datas.service';
import { Title} from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Booking } from '../../models/booking';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';

@Component({
  selector: 'app-booking-summary',
  templateUrl: './booking-summary.component.html',
  styleUrls: ['./booking-summary.component.css']
})
export class BookingSummaryComponent implements OnInit {

  private myBooking: Booking;

  constructor(private titleService: Title,
              private router: Router,
              private datas: DatasService) {

                this.myBooking = this.datas.valideBooking;
                registerLocaleData(localeFr, 'FR-fr');
              }

  ngOnInit() {
    if (this.myBooking === undefined) {
      this.router.navigateByUrl('/');
    }

    this.titleService.setTitle('Récapitulatif réservation');
    // console.log(this.myBooking);
  }

  public isMyBooking() {
    return this.myBooking === undefined ? false : true;
  }

  public getNumBooking() {
    return this.myBooking.numBooking
  }

  public getName() {
    return this.myBooking.customer.name;
  }

  public getDateIn() {
    return this.myBooking.dateIn;
  }

  public getDateOut() {
    return this.myBooking.dateOut;
  }

  public getNbrAdults() {
    return this.myBooking.nbrAdults;
  }

  public getNbrChildren() {
    return this.myBooking.nbrChildrens;
  }

  public getTypeOfRoom() {
    return this.myBooking.room.roomType.type;
  }

  public getRoomPpn() {
    return this.myBooking.room.roomType.ppn;
  }

  public getNbrOfNights() {
    return this.myBooking.nbrNights;
  }

  public getTotalPrice() {
    return this.getRoomPpn()  * this.myBooking.nbrNights;
  }

  public getCivility() {
    return this.myBooking.customer.civility;
  }

}
